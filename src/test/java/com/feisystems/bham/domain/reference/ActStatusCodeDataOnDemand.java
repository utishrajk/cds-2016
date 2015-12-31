package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ActStatusCodeService;
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
public class ActStatusCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ActStatusCode> data;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    ActStatusCodeRepository actStatusCodeRepository;

	public ActStatusCode getNewTransientActStatusCode(int index) {
        ActStatusCode obj = new ActStatusCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(ActStatusCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(ActStatusCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(ActStatusCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(ActStatusCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(ActStatusCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(ActStatusCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public ActStatusCode getSpecificActStatusCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ActStatusCode obj = data.get(index);
        Long id = obj.getId();
        return actStatusCodeService.findActStatusCode(id);
    }

	public ActStatusCode getRandomActStatusCode() {
        init();
        ActStatusCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return actStatusCodeService.findActStatusCode(id);
    }

	public boolean modifyActStatusCode(ActStatusCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = actStatusCodeService.findActStatusCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ActStatusCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ActStatusCode>();
        for (int i = 0; i < 10; i++) {
            ActStatusCode obj = getNewTransientActStatusCode(i);
            try {
                actStatusCodeService.saveActStatusCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            actStatusCodeRepository.flush();
            data.add(obj);
        }
    }
}
