package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.service.clinicaldata.ResultObservationService;
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
public class ResultObservationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ResultObservation> data;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    ResultObservationService resultObservationService;

	@Autowired
    ResultObservationRepository resultObservationRepository;

	public ResultObservation getNewTransientResultObservation(int index) {
        ResultObservation obj = new ResultObservation();
        setResultObservationType(obj, index);
        setResultObservationValue(obj, index);
        setResultObservationDateTime(obj, index);
        setResultReferenceRange(obj, index);
        return obj;
    }

	public void setResultObservationType(ResultObservation obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setResultObservationTypeCode(embeddedClass, index);
        setResultObservationTypeCodeSystem(embeddedClass, index);
        setResultObservationTypeDisplayName(embeddedClass, index);
        setResultObservationTypeCodeSystemName(embeddedClass, index);
        setResultObservationTypeOriginalText(embeddedClass, index);
        setResultObservationTypeCodeSystemVersion(embeddedClass, index);
        obj.setResultObservationType(embeddedClass);
    }

	public void setResultObservationTypeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setResultObservationTypeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setResultObservationTypeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setResultObservationTypeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setResultObservationTypeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setResultObservationTypeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setResultObservationValue(ResultObservation obj, int index) {
        QuantityType embeddedClass = new QuantityType();
        setResultObservationValueMeasuredValue(embeddedClass, index);
        setResultObservationValueUnitOfMeasureCode(embeddedClass, index);
        obj.setResultObservationValue(embeddedClass);
    }

	public void setResultObservationValueMeasuredValue(QuantityType obj, int index) {
        Double measuredValue = new Integer(index).doubleValue();
        obj.setMeasuredValue(measuredValue);
    }

	public void setResultObservationValueUnitOfMeasureCode(QuantityType obj, int index) {
        UnitOfMeasureCode unitOfMeasureCode = null;
        obj.setUnitOfMeasureCode(unitOfMeasureCode);
    }

	public void setResultObservationDateTime(ResultObservation obj, int index) {
        Date resultObservationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setResultObservationDateTime(resultObservationDateTime);
    }

	public void setResultReferenceRange(ResultObservation obj, int index) {
        String resultReferenceRange = "resultReferenceRange_" + index;
        obj.setResultReferenceRange(resultReferenceRange);
    }

	public ResultObservation getSpecificResultObservation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ResultObservation obj = data.get(index);
        Long id = obj.getId();
        return resultObservationService.findResultObservation(id);
    }

	public ResultObservation getRandomResultObservation() {
        init();
        ResultObservation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return resultObservationService.findResultObservation(id);
    }

	public boolean modifyResultObservation(ResultObservation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = resultObservationService.findResultObservationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ResultObservation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ResultObservation>();
        for (int i = 0; i < 10; i++) {
            ResultObservation obj = getNewTransientResultObservation(i);
            try {
                resultObservationService.saveResultObservation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            resultObservationRepository.flush();
            data.add(obj);
        }
    }
}
