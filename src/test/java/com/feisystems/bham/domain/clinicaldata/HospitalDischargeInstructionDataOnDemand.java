package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.service.clinicaldata.HospitalDischargeInstructionService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
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
public class HospitalDischargeInstructionDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<HospitalDischargeInstruction> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    HospitalDischargeInstructionService hospitalDischargeInstructionService;

	@Autowired
    HospitalDischargeInstructionRepository hospitalDischargeInstructionRepository;

	public HospitalDischargeInstruction getNewTransientHospitalDischargeInstruction(int index) {
        HospitalDischargeInstruction obj = new HospitalDischargeInstruction();
        setHospitalDischargeInstructionDateTime(obj, index);
        setHospitalDischargeInstructionText(obj, index);
        return obj;
    }

	public void setHospitalDischargeInstructionDateTime(HospitalDischargeInstruction obj, int index) {
        Calendar hospitalDischargeInstructionDateTime = Calendar.getInstance();
        obj.setHospitalDischargeInstructionDateTime(hospitalDischargeInstructionDateTime);
    }

	public void setHospitalDischargeInstructionText(HospitalDischargeInstruction obj, int index) {
        String hospitalDischargeInstructionText = "hospitalDischargeInstructionText_" + index;
        obj.setHospitalDischargeInstructionText(hospitalDischargeInstructionText);
    }

	public HospitalDischargeInstruction getSpecificHospitalDischargeInstruction(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        HospitalDischargeInstruction obj = data.get(index);
        Long id = obj.getId();
        return hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
    }

	public HospitalDischargeInstruction getRandomHospitalDischargeInstruction() {
        init();
        HospitalDischargeInstruction obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
    }

	public boolean modifyHospitalDischargeInstruction(HospitalDischargeInstruction obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = hospitalDischargeInstructionService.findHospitalDischargeInstructionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'HospitalDischargeInstruction' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<HospitalDischargeInstruction>();
        for (int i = 0; i < 10; i++) {
            HospitalDischargeInstruction obj = getNewTransientHospitalDischargeInstruction(i);
            try {
                hospitalDischargeInstructionService.saveHospitalDischargeInstruction(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            hospitalDischargeInstructionRepository.flush();
            data.add(obj);
        }
    }
}
