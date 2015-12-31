package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultOrganizerService;
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
public class FunctionalStatusResultOrganizerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<FunctionalStatusResultOrganizer> data;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    FunctionalStatusResultOrganizerService functionalStatusResultOrganizerService;

	@Autowired
    FunctionalStatusResultOrganizerRepository functionalStatusResultOrganizerRepository;

	public FunctionalStatusResultOrganizer getNewTransientFunctionalStatusResultOrganizer(int index) {
        FunctionalStatusResultOrganizer obj = new FunctionalStatusResultOrganizer();
        setFunctionalStatusResultOrganizerCode(obj, index);
        setFunctionalStatusResultOrganizerDateTime(obj, index);
        return obj;
    }

	public void setFunctionalStatusResultOrganizerCode(FunctionalStatusResultOrganizer obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setFunctionalStatusResultOrganizerCodeCode(embeddedClass, index);
        setFunctionalStatusResultOrganizerCodeCodeSystem(embeddedClass, index);
        setFunctionalStatusResultOrganizerCodeDisplayName(embeddedClass, index);
        setFunctionalStatusResultOrganizerCodeCodeSystemName(embeddedClass, index);
        setFunctionalStatusResultOrganizerCodeOriginalText(embeddedClass, index);
        setFunctionalStatusResultOrganizerCodeCodeSystemVersion(embeddedClass, index);
        obj.setFunctionalStatusResultOrganizerCode(embeddedClass);
    }

	public void setFunctionalStatusResultOrganizerCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setFunctionalStatusResultOrganizerCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setFunctionalStatusResultOrganizerCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setFunctionalStatusResultOrganizerCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setFunctionalStatusResultOrganizerCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setFunctionalStatusResultOrganizerCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setFunctionalStatusResultOrganizerDateTime(FunctionalStatusResultOrganizer obj, int index) {
        Date functionalStatusResultOrganizerDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFunctionalStatusResultOrganizerDateTime(functionalStatusResultOrganizerDateTime);
    }

	public FunctionalStatusResultOrganizer getSpecificFunctionalStatusResultOrganizer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        FunctionalStatusResultOrganizer obj = data.get(index);
        Long id = obj.getId();
        return functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
    }

	public FunctionalStatusResultOrganizer getRandomFunctionalStatusResultOrganizer() {
        init();
        FunctionalStatusResultOrganizer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
    }

	public boolean modifyFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'FunctionalStatusResultOrganizer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<FunctionalStatusResultOrganizer>();
        for (int i = 0; i < 10; i++) {
            FunctionalStatusResultOrganizer obj = getNewTransientFunctionalStatusResultOrganizer(i);
            try {
                functionalStatusResultOrganizerService.saveFunctionalStatusResultOrganizer(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            functionalStatusResultOrganizerRepository.flush();
            data.add(obj);
        }
    }
}
