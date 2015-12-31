package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.BodySiteCodeDataOnDemand;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.service.clinicaldata.PatientGoalService;
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
public class PatientGoalDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<PatientGoal> data;

	@Autowired
    BodySiteCodeDataOnDemand bodySiteCodeDataOnDemand;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    PatientGoalService patientGoalService;

	@Autowired
    PatientGoalRepository patientGoalRepository;

	public PatientGoal getNewTransientPatientGoal(int index) {
        PatientGoal obj = new PatientGoal();
        setGoalFocus(obj, index);
        setTargetGoalValue(obj, index);
        setGoalAchievementTargetTime(obj, index);
        setGoalPursuitEffectiveTime(obj, index);
        return obj;
    }

	public void setGoalFocus(PatientGoal obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setGoalFocusCode(embeddedClass, index);
        setGoalFocusCodeSystem(embeddedClass, index);
        setGoalFocusDisplayName(embeddedClass, index);
        setGoalFocusCodeSystemName(embeddedClass, index);
        setGoalFocusOriginalText(embeddedClass, index);
        setGoalFocusCodeSystemVersion(embeddedClass, index);
        obj.setGoalFocus(embeddedClass);
    }

	public void setGoalFocusCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setGoalFocusCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setGoalFocusDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setGoalFocusCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setGoalFocusOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setGoalFocusCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setTargetGoalValue(PatientGoal obj, int index) {
        QuantityType embeddedClass = new QuantityType();
        setTargetGoalValueMeasuredValue(embeddedClass, index);
        setTargetGoalValueUnitOfMeasureCode(embeddedClass, index);
        obj.setTargetGoalValue(embeddedClass);
    }

	public void setTargetGoalValueMeasuredValue(QuantityType obj, int index) {
        Double measuredValue = new Integer(index).doubleValue();
        obj.setMeasuredValue(measuredValue);
    }

	public void setTargetGoalValueUnitOfMeasureCode(QuantityType obj, int index) {
        UnitOfMeasureCode unitOfMeasureCode = null;
        obj.setUnitOfMeasureCode(unitOfMeasureCode);
    }

	public void setGoalAchievementTargetTime(PatientGoal obj, int index) {
        Date goalAchievementTargetTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setGoalAchievementTargetTime(goalAchievementTargetTime);
    }

	public void setGoalPursuitEffectiveTime(PatientGoal obj, int index) {
        Date goalPursuitEffectiveTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setGoalPursuitEffectiveTime(goalPursuitEffectiveTime);
    }

	public PatientGoal getSpecificPatientGoal(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PatientGoal obj = data.get(index);
        Long id = obj.getId();
        return patientGoalService.findPatientGoal(id);
    }

	public PatientGoal getRandomPatientGoal() {
        init();
        PatientGoal obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return patientGoalService.findPatientGoal(id);
    }

	public boolean modifyPatientGoal(PatientGoal obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = patientGoalService.findPatientGoalEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PatientGoal' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PatientGoal>();
        for (int i = 0; i < 10; i++) {
            PatientGoal obj = getNewTransientPatientGoal(i);
            try {
                patientGoalService.savePatientGoal(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            patientGoalRepository.flush();
            data.add(obj);
        }
    }
}
