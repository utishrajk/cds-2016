package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ResultInterpretationCodeService;
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
public class ResultInterpretationCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ResultInterpretationCode> data;

	@Autowired
    ResultInterpretationCodeService resultInterpretationCodeService;

	@Autowired
    ResultInterpretationCodeRepository resultInterpretationCodeRepository;

	public ResultInterpretationCode getNewTransientResultInterpretationCode(int index) {
        ResultInterpretationCode obj = new ResultInterpretationCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(ResultInterpretationCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(ResultInterpretationCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(ResultInterpretationCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(ResultInterpretationCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(ResultInterpretationCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(ResultInterpretationCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public ResultInterpretationCode getSpecificResultInterpretationCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ResultInterpretationCode obj = data.get(index);
        Long id = obj.getId();
        return resultInterpretationCodeService.findResultInterpretationCode(id);
    }

	public ResultInterpretationCode getRandomResultInterpretationCode() {
        init();
        ResultInterpretationCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return resultInterpretationCodeService.findResultInterpretationCode(id);
    }

	public boolean modifyResultInterpretationCode(ResultInterpretationCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = resultInterpretationCodeService.findResultInterpretationCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ResultInterpretationCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ResultInterpretationCode>();
        for (int i = 0; i < 10; i++) {
            ResultInterpretationCode obj = getNewTransientResultInterpretationCode(i);
            try {
                resultInterpretationCodeService.saveResultInterpretationCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            resultInterpretationCodeRepository.flush();
            data.add(obj);
        }
    }
}
