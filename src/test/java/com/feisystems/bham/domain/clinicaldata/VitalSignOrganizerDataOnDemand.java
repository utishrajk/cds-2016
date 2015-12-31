package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.service.clinicaldata.VitalSignOrganizerService;
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
public class VitalSignOrganizerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<VitalSignOrganizer> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    VitalSignOrganizerService vitalSignOrganizerService;

	@Autowired
    VitalSignOrganizerRepository vitalSignOrganizerRepository;

	public VitalSignOrganizer getNewTransientVitalSignOrganizer(int index) {
        VitalSignOrganizer obj = new VitalSignOrganizer();
        setVitalSignOrganizerCode(obj, index);
        setVitalSignOrganizerDateTime(obj, index);
        return obj;
    }

	public void setVitalSignOrganizerCode(VitalSignOrganizer obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setVitalSignOrganizerCodeCode(embeddedClass, index);
        setVitalSignOrganizerCodeCodeSystem(embeddedClass, index);
        setVitalSignOrganizerCodeDisplayName(embeddedClass, index);
        setVitalSignOrganizerCodeCodeSystemName(embeddedClass, index);
        setVitalSignOrganizerCodeOriginalText(embeddedClass, index);
        setVitalSignOrganizerCodeCodeSystemVersion(embeddedClass, index);
        obj.setVitalSignOrganizerCode(embeddedClass);
    }

	public void setVitalSignOrganizerCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setVitalSignOrganizerCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setVitalSignOrganizerCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setVitalSignOrganizerCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setVitalSignOrganizerCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setVitalSignOrganizerCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setVitalSignOrganizerDateTime(VitalSignOrganizer obj, int index) {
        Date vitalSignOrganizerDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setVitalSignOrganizerDateTime(vitalSignOrganizerDateTime);
    }

	public VitalSignOrganizer getSpecificVitalSignOrganizer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        VitalSignOrganizer obj = data.get(index);
        Long id = obj.getId();
        return vitalSignOrganizerService.findVitalSignOrganizer(id);
    }

	public VitalSignOrganizer getRandomVitalSignOrganizer() {
        init();
        VitalSignOrganizer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return vitalSignOrganizerService.findVitalSignOrganizer(id);
    }

	public boolean modifyVitalSignOrganizer(VitalSignOrganizer obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = vitalSignOrganizerService.findVitalSignOrganizerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'VitalSignOrganizer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<VitalSignOrganizer>();
        for (int i = 0; i < 10; i++) {
            VitalSignOrganizer obj = getNewTransientVitalSignOrganizer(i);
            try {
                vitalSignOrganizerService.saveVitalSignOrganizer(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            vitalSignOrganizerRepository.flush();
            data.add(obj);
        }
    }
}
