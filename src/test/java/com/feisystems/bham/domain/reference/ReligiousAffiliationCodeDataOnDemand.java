package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ReligiousAffiliationCodeService;
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

@Configurable
@Component
public class ReligiousAffiliationCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ReligiousAffiliationCode> data;

	@Autowired
    ReligiousAffiliationCodeService religiousAffiliationCodeService;

	@Autowired
    ReligiousAffiliationCodeRepository religiousAffiliationCodeRepository;

	public ReligiousAffiliationCode getNewTransientReligiousAffiliationCode(int index) {
        ReligiousAffiliationCode obj = new ReligiousAffiliationCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(ReligiousAffiliationCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(ReligiousAffiliationCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(ReligiousAffiliationCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(ReligiousAffiliationCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(ReligiousAffiliationCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(ReligiousAffiliationCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public ReligiousAffiliationCode getSpecificReligiousAffiliationCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ReligiousAffiliationCode obj = data.get(index);
        Long id = obj.getId();
        return religiousAffiliationCodeService.findReligiousAffiliationCode(id);
    }

	public ReligiousAffiliationCode getRandomReligiousAffiliationCode() {
        init();
        ReligiousAffiliationCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return religiousAffiliationCodeService.findReligiousAffiliationCode(id);
    }

	public boolean modifyReligiousAffiliationCode(ReligiousAffiliationCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = religiousAffiliationCodeService.findReligiousAffiliationCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ReligiousAffiliationCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ReligiousAffiliationCode>();
        for (int i = 0; i < 10; i++) {
            ReligiousAffiliationCode obj = getNewTransientReligiousAffiliationCode(i);
            try {
                religiousAffiliationCodeService.saveReligiousAffiliationCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            religiousAffiliationCodeRepository.flush();
            data.add(obj);
        }
    }
}
