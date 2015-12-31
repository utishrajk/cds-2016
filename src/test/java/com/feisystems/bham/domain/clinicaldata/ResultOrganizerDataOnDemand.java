package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.ResultOrganizerService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class ResultOrganizerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ResultOrganizer> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    ResultOrganizerService resultOrganizerService;

	@Autowired
    ResultOrganizerRepository resultOrganizerRepository;

	public ResultOrganizer getNewTransientResultOrganizer(int index) {
        ResultOrganizer obj = new ResultOrganizer();
        setResultOrganizerCode(obj, index);
        setResultOrganizerDateTime(obj, index);
        return obj;
    }

	public void setResultOrganizerCode(ResultOrganizer obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setResultOrganizerCodeCode(embeddedClass, index);
        setResultOrganizerCodeCodeSystem(embeddedClass, index);
        setResultOrganizerCodeDisplayName(embeddedClass, index);
        setResultOrganizerCodeCodeSystemName(embeddedClass, index);
        setResultOrganizerCodeOriginalText(embeddedClass, index);
        setResultOrganizerCodeCodeSystemVersion(embeddedClass, index);
        obj.setResultOrganizerCode(embeddedClass);
    }

	public void setResultOrganizerCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setResultOrganizerCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setResultOrganizerCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setResultOrganizerCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setResultOrganizerCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setResultOrganizerCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setResultOrganizerDateTime(ResultOrganizer obj, int index) {
        Date resultOrganizerDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setResultOrganizerDateTime(resultOrganizerDateTime);
    }

	public ResultOrganizer getSpecificResultOrganizer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ResultOrganizer obj = data.get(index);
        Long id = obj.getId();
        return resultOrganizerService.findResultOrganizer(id);
    }

	public ResultOrganizer getRandomResultOrganizer() {
        init();
        ResultOrganizer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return resultOrganizerService.findResultOrganizer(id);
    }

	public boolean modifyResultOrganizer(ResultOrganizer obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = resultOrganizerService.findResultOrganizerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ResultOrganizer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ResultOrganizer>();
        for (int i = 0; i < 10; i++) {
            ResultOrganizer obj = getNewTransientResultOrganizer(i);
            try {
                resultOrganizerService.saveResultOrganizer(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            resultOrganizerRepository.flush();
            data.add(obj);
        }
    }
}
