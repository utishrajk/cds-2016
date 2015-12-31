package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.EthnicGroupCodeService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class EthnicGroupCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<EthnicGroupCode> data;

	@Autowired
    EthnicGroupCodeService ethnicGroupCodeService;

	@Autowired
    EthnicGroupCodeRepository ethnicGroupCodeRepository;

	public EthnicGroupCode getNewTransientEthnicGroupCode(int index) {
        EthnicGroupCode obj = new EthnicGroupCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(EthnicGroupCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(EthnicGroupCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(EthnicGroupCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(EthnicGroupCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(EthnicGroupCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(EthnicGroupCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public EthnicGroupCode getSpecificEthnicGroupCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        EthnicGroupCode obj = data.get(index);
        Long id = obj.getId();
        return ethnicGroupCodeService.findEthnicGroupCode(id);
    }

	public EthnicGroupCode getRandomEthnicGroupCode() {
        init();
        EthnicGroupCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return ethnicGroupCodeService.findEthnicGroupCode(id);
    }

	public boolean modifyEthnicGroupCode(EthnicGroupCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = ethnicGroupCodeService.findEthnicGroupCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'EthnicGroupCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<EthnicGroupCode>();
        for (int i = 0; i < 10; i++) {
            EthnicGroupCode obj = getNewTransientEthnicGroupCode(i);
            try {
                ethnicGroupCodeService.saveEthnicGroupCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            ethnicGroupCodeRepository.flush();
            data.add(obj);
        }
    }
}
