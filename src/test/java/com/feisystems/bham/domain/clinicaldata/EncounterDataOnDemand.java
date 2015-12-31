package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.EncounterService;
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
public class EncounterDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Encounter> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    EncounterService encounterService;

	@Autowired
    EncounterRepository encounterRepository;

	public Encounter getNewTransientEncounter(int index) {
        Encounter obj = new Encounter();
        setEncounterType(obj, index);
        setEncounterEventTime(obj, index);
        setEncounterFreeText(obj, index);
        return obj;
    }

	public void setEncounterType(Encounter obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setEncounterTypeCode(embeddedClass, index);
        setEncounterTypeCodeSystem(embeddedClass, index);
        setEncounterTypeDisplayName(embeddedClass, index);
        setEncounterTypeCodeSystemName(embeddedClass, index);
        setEncounterTypeOriginalText(embeddedClass, index);
        setEncounterTypeCodeSystemVersion(embeddedClass, index);
        obj.setEncounterType(embeddedClass);
    }

	public void setEncounterTypeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setEncounterTypeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setEncounterTypeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setEncounterTypeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setEncounterTypeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setEncounterTypeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setEncounterEventTime(Encounter obj, int index) {
        Date encounterEventTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setEncounterEventTime(encounterEventTime);
    }

	public void setEncounterFreeText(Encounter obj, int index) {
        String encounterFreeText = "encounterFreeText_" + index;
        obj.setEncounterFreeText(encounterFreeText);
    }

	public Encounter getSpecificEncounter(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Encounter obj = data.get(index);
        Long id = obj.getId();
        return encounterService.findEncounter(id);
    }

	public Encounter getRandomEncounter() {
        init();
        Encounter obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return encounterService.findEncounter(id);
    }

	public boolean modifyEncounter(Encounter obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = encounterService.findEncounterEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Encounter' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Encounter>();
        for (int i = 0; i < 10; i++) {
            Encounter obj = getNewTransientEncounter(i);
            try {
                encounterService.saveEncounter(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            encounterRepository.flush();
            data.add(obj);
        }
    }
}
