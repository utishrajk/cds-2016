package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleSupportingObservationService;
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
public class AssessmentScaleSupportingObservationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<AssessmentScaleSupportingObservation> data;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    AssessmentScaleSupportingObservationService assessmentScaleSupportingObservationService;

	@Autowired
    AssessmentScaleSupportingObservationRepository assessmentScaleSupportingObservationRepository;

	public AssessmentScaleSupportingObservation getNewTransientAssessmentScaleSupportingObservation(int index) {
        AssessmentScaleSupportingObservation obj = new AssessmentScaleSupportingObservation();
        setAssessmentScaleSupportingObservationCode(obj, index);
        setAssessmentScaleSupportingObservationValue(obj, index);
        setAssessmentScaleSupportingObservationDateTime(obj, index);
        return obj;
    }

	public void setAssessmentScaleSupportingObservationCode(AssessmentScaleSupportingObservation obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setAssessmentScaleSupportingObservationCodeCode(embeddedClass, index);
        setAssessmentScaleSupportingObservationCodeCodeSystem(embeddedClass, index);
        setAssessmentScaleSupportingObservationCodeDisplayName(embeddedClass, index);
        setAssessmentScaleSupportingObservationCodeCodeSystemName(embeddedClass, index);
        setAssessmentScaleSupportingObservationCodeOriginalText(embeddedClass, index);
        setAssessmentScaleSupportingObservationCodeCodeSystemVersion(embeddedClass, index);
        obj.setAssessmentScaleSupportingObservationCode(embeddedClass);
    }

	public void setAssessmentScaleSupportingObservationCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setAssessmentScaleSupportingObservationCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setAssessmentScaleSupportingObservationCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setAssessmentScaleSupportingObservationCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setAssessmentScaleSupportingObservationCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setAssessmentScaleSupportingObservationCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setAssessmentScaleSupportingObservationValue(AssessmentScaleSupportingObservation obj, int index) {
        QuantityType embeddedClass = new QuantityType();
        setAssessmentScaleSupportingObservationValueMeasuredValue(embeddedClass, index);
        setAssessmentScaleSupportingObservationValueUnitOfMeasureCode(embeddedClass, index);
        obj.setAssessmentScaleSupportingObservationValue(embeddedClass);
    }

	public void setAssessmentScaleSupportingObservationValueMeasuredValue(QuantityType obj, int index) {
        Double measuredValue = new Integer(index).doubleValue();
        obj.setMeasuredValue(measuredValue);
    }

	public void setAssessmentScaleSupportingObservationValueUnitOfMeasureCode(QuantityType obj, int index) {
        UnitOfMeasureCode unitOfMeasureCode = null;
        obj.setUnitOfMeasureCode(unitOfMeasureCode);
    }

	public void setAssessmentScaleSupportingObservationDateTime(AssessmentScaleSupportingObservation obj, int index) {
        Date assessmentScaleSupportingObservationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setAssessmentScaleSupportingObservationDateTime(assessmentScaleSupportingObservationDateTime);
    }

	public AssessmentScaleSupportingObservation getSpecificAssessmentScaleSupportingObservation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        AssessmentScaleSupportingObservation obj = data.get(index);
        Long id = obj.getId();
        return assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
    }

	public AssessmentScaleSupportingObservation getRandomAssessmentScaleSupportingObservation() {
        init();
        AssessmentScaleSupportingObservation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
    }

	public boolean modifyAssessmentScaleSupportingObservation(AssessmentScaleSupportingObservation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'AssessmentScaleSupportingObservation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<AssessmentScaleSupportingObservation>();
        for (int i = 0; i < 10; i++) {
            AssessmentScaleSupportingObservation obj = getNewTransientAssessmentScaleSupportingObservation(i);
            try {
                assessmentScaleSupportingObservationService.saveAssessmentScaleSupportingObservation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            assessmentScaleSupportingObservationRepository.flush();
            data.add(obj);
        }
    }
}
