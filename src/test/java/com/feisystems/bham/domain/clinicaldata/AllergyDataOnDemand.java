package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.AdverseEventTypeCodeDataOnDemand;
import com.feisystems.bham.domain.reference.AllergyReactionCodeDataOnDemand;
import com.feisystems.bham.domain.reference.AllergySeverityCodeDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.AllergyService;
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
public class AllergyDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Allergy> data;

	@Autowired
    AdverseEventTypeCodeDataOnDemand adverseEventTypeCodeDataOnDemand;

	@Autowired
    AllergyReactionCodeDataOnDemand allergyReactionCodeDataOnDemand;

	@Autowired
    AllergySeverityCodeDataOnDemand allergySeverityCodeDataOnDemand;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    AllergyService allergyService;

	@Autowired
    AllergyRepository allergyRepository;

	public Allergy getNewTransientAllergy(int index) {
        Allergy obj = new Allergy();
        setAllergen(obj, index);
        setAllergyEndDate(obj, index);
        setAllergyStartDate(obj, index);
        return obj;
    }

	public void setAllergen(Allergy obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setAllergenCode(embeddedClass, index);
        setAllergenCodeSystem(embeddedClass, index);
        setAllergenDisplayName(embeddedClass, index);
        setAllergenCodeSystemName(embeddedClass, index);
        setAllergenOriginalText(embeddedClass, index);
        setAllergenCodeSystemVersion(embeddedClass, index);
        obj.setAllergen(embeddedClass);
    }

	public void setAllergenCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setAllergenCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setAllergenDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setAllergenCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setAllergenOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setAllergenCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setAllergyEndDate(Allergy obj, int index) {
        Date allergyEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setAllergyEndDate(allergyEndDate);
    }

	public void setAllergyStartDate(Allergy obj, int index) {
        Date allergyStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setAllergyStartDate(allergyStartDate);
    }

	public Allergy getSpecificAllergy(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Allergy obj = data.get(index);
        Long id = obj.getId();
        return allergyService.findAllergy(id);
    }

	public Allergy getRandomAllergy() {
        init();
        Allergy obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return allergyService.findAllergy(id);
    }

	public boolean modifyAllergy(Allergy obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = allergyService.findAllergyEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Allergy' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Allergy>();
        for (int i = 0; i < 10; i++) {
            Allergy obj = getNewTransientAllergy(i);
            try {
                allergyService.saveAllergy(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            allergyRepository.flush();
            data.add(obj);
        }
    }
}
