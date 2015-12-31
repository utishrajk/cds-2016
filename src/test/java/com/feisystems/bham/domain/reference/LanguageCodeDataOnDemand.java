package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.LanguageCodeService;
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
public class LanguageCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<LanguageCode> data;

	@Autowired
    LanguageCodeService languageCodeService;

	@Autowired
    LanguageCodeRepository languageCodeRepository;

	public LanguageCode getNewTransientLanguageCode(int index) {
        LanguageCode obj = new LanguageCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(LanguageCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(LanguageCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(LanguageCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(LanguageCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(LanguageCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(LanguageCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public LanguageCode getSpecificLanguageCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        LanguageCode obj = data.get(index);
        Long id = obj.getId();
        return languageCodeService.findLanguageCode(id);
    }

	public LanguageCode getRandomLanguageCode() {
        init();
        LanguageCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return languageCodeService.findLanguageCode(id);
    }

	public boolean modifyLanguageCode(LanguageCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = languageCodeService.findLanguageCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'LanguageCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<LanguageCode>();
        for (int i = 0; i < 10; i++) {
            LanguageCode obj = getNewTransientLanguageCode(i);
            try {
                languageCodeService.saveLanguageCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            languageCodeRepository.flush();
            data.add(obj);
        }
    }
}
