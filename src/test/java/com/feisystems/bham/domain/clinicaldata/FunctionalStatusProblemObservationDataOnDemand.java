package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusProblemObservationService;
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

@Configurable
@Component
public class FunctionalStatusProblemObservationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<FunctionalStatusProblemObservation> data;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    FunctionalStatusProblemObservationService functionalStatusProblemObservationService;

	@Autowired
    FunctionalStatusProblemObservationRepository functionalStatusProblemObservationRepository;

	public FunctionalStatusProblemObservation getNewTransientFunctionalStatusProblemObservation(int index) {
        FunctionalStatusProblemObservation obj = new FunctionalStatusProblemObservation();
        setFunctionalStatusProblemObservationCode(obj, index);
        setFunctionalStatusProblemObservationDateTime(obj, index);
        return obj;
    }

	public void setFunctionalStatusProblemObservationCode(FunctionalStatusProblemObservation obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setFunctionalStatusProblemObservationCodeCode(embeddedClass, index);
        setFunctionalStatusProblemObservationCodeCodeSystem(embeddedClass, index);
        setFunctionalStatusProblemObservationCodeDisplayName(embeddedClass, index);
        setFunctionalStatusProblemObservationCodeCodeSystemName(embeddedClass, index);
        setFunctionalStatusProblemObservationCodeOriginalText(embeddedClass, index);
        setFunctionalStatusProblemObservationCodeCodeSystemVersion(embeddedClass, index);
        obj.setFunctionalStatusProblemObservationCode(embeddedClass);
    }

	public void setFunctionalStatusProblemObservationCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setFunctionalStatusProblemObservationCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setFunctionalStatusProblemObservationCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setFunctionalStatusProblemObservationCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setFunctionalStatusProblemObservationCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setFunctionalStatusProblemObservationCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setFunctionalStatusProblemObservationDateTime(FunctionalStatusProblemObservation obj, int index) {
        Date functionalStatusProblemObservationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFunctionalStatusProblemObservationDateTime(functionalStatusProblemObservationDateTime);
    }

	public FunctionalStatusProblemObservation getSpecificFunctionalStatusProblemObservation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        FunctionalStatusProblemObservation obj = data.get(index);
        Long id = obj.getId();
        return functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
    }

	public FunctionalStatusProblemObservation getRandomFunctionalStatusProblemObservation() {
        init();
        FunctionalStatusProblemObservation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
    }

	public boolean modifyFunctionalStatusProblemObservation(FunctionalStatusProblemObservation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = functionalStatusProblemObservationService.findFunctionalStatusProblemObservationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'FunctionalStatusProblemObservation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<FunctionalStatusProblemObservation>();
        for (int i = 0; i < 10; i++) {
            FunctionalStatusProblemObservation obj = getNewTransientFunctionalStatusProblemObservation(i);
            try {
                functionalStatusProblemObservationService.saveFunctionalStatusProblemObservation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            functionalStatusProblemObservationRepository.flush();
            data.add(obj);
        }
    }
}
