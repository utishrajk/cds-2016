package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleObservationService;
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
public class AssessmentScaleObservationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<AssessmentScaleObservation> data;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    AssessmentScaleObservationService assessmentScaleObservationService;

	@Autowired
    AssessmentScaleObservationRepository assessmentScaleObservationRepository;

	public AssessmentScaleObservation getNewTransientAssessmentScaleObservation(int index) {
        AssessmentScaleObservation obj = new AssessmentScaleObservation();
        setAssessmentScaleObservationCode(obj, index);
        setAssessmentScaleObservationValue(obj, index);
        setAssessmentScaleObservationDateTime(obj, index);
        setDerivationExpr(obj, index);
        return obj;
    }

	public void setAssessmentScaleObservationCode(AssessmentScaleObservation obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setAssessmentScaleObservationCodeCode(embeddedClass, index);
        setAssessmentScaleObservationCodeCodeSystem(embeddedClass, index);
        setAssessmentScaleObservationCodeDisplayName(embeddedClass, index);
        setAssessmentScaleObservationCodeCodeSystemName(embeddedClass, index);
        setAssessmentScaleObservationCodeOriginalText(embeddedClass, index);
        setAssessmentScaleObservationCodeCodeSystemVersion(embeddedClass, index);
        obj.setAssessmentScaleObservationCode(embeddedClass);
    }

	public void setAssessmentScaleObservationCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setAssessmentScaleObservationCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setAssessmentScaleObservationCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setAssessmentScaleObservationCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setAssessmentScaleObservationCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setAssessmentScaleObservationCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setAssessmentScaleObservationValue(AssessmentScaleObservation obj, int index) {
        QuantityType embeddedClass = new QuantityType();
        setAssessmentScaleObservationValueMeasuredValue(embeddedClass, index);
        setAssessmentScaleObservationValueUnitOfMeasureCode(embeddedClass, index);
        obj.setAssessmentScaleObservationValue(embeddedClass);
    }

	public void setAssessmentScaleObservationValueMeasuredValue(QuantityType obj, int index) {
        Double measuredValue = new Integer(index).doubleValue();
        obj.setMeasuredValue(measuredValue);
    }

	public void setAssessmentScaleObservationValueUnitOfMeasureCode(QuantityType obj, int index) {
        UnitOfMeasureCode unitOfMeasureCode = null;
        obj.setUnitOfMeasureCode(unitOfMeasureCode);
    }

	public void setAssessmentScaleObservationDateTime(AssessmentScaleObservation obj, int index) {
        Date assessmentScaleObservationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setAssessmentScaleObservationDateTime(assessmentScaleObservationDateTime);
    }

	public void setDerivationExpr(AssessmentScaleObservation obj, int index) {
        String derivationExpr = "derivationExpr_" + index;
        obj.setDerivationExpr(derivationExpr);
    }

	public AssessmentScaleObservation getSpecificAssessmentScaleObservation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        AssessmentScaleObservation obj = data.get(index);
        Long id = obj.getId();
        return assessmentScaleObservationService.findAssessmentScaleObservation(id);
    }

	public AssessmentScaleObservation getRandomAssessmentScaleObservation() {
        init();
        AssessmentScaleObservation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return assessmentScaleObservationService.findAssessmentScaleObservation(id);
    }

	public boolean modifyAssessmentScaleObservation(AssessmentScaleObservation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = assessmentScaleObservationService.findAssessmentScaleObservationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'AssessmentScaleObservation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<AssessmentScaleObservation>();
        for (int i = 0; i < 10; i++) {
            AssessmentScaleObservation obj = getNewTransientAssessmentScaleObservation(i);
            try {
                assessmentScaleObservationService.saveAssessmentScaleObservation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            assessmentScaleObservationRepository.flush();
            data.add(obj);
        }
    }
}
