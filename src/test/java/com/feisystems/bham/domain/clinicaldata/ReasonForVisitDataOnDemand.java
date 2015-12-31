package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.service.clinicaldata.ReasonForVisitService;
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

@Component
@Configurable
public class ReasonForVisitDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ReasonForVisit> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    ReasonForVisitService reasonForVisitService;

	@Autowired
    ReasonForVisitRepository reasonForVisitRepository;

	public ReasonForVisit getNewTransientReasonForVisit(int index) {
        ReasonForVisit obj = new ReasonForVisit();
        setReasonForVisitDateTime(obj, index);
        setReasonForVisitText(obj, index);
        return obj;
    }

	public void setReasonForVisitDateTime(ReasonForVisit obj, int index) {
        Calendar reasonForVisitDateTime = Calendar.getInstance();
        obj.setReasonForVisitDateTime(reasonForVisitDateTime);
    }

	public void setReasonForVisitText(ReasonForVisit obj, int index) {
        String reasonForVisitText = "reasonForVisitText_" + index;
        obj.setReasonForVisitText(reasonForVisitText);
    }

	public ReasonForVisit getSpecificReasonForVisit(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ReasonForVisit obj = data.get(index);
        Long id = obj.getId();
        return reasonForVisitService.findReasonForVisit(id);
    }

	public ReasonForVisit getRandomReasonForVisit() {
        init();
        ReasonForVisit obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return reasonForVisitService.findReasonForVisit(id);
    }

	public boolean modifyReasonForVisit(ReasonForVisit obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = reasonForVisitService.findReasonForVisitEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ReasonForVisit' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ReasonForVisit>();
        for (int i = 0; i < 10; i++) {
            ReasonForVisit obj = getNewTransientReasonForVisit(i);
            try {
                reasonForVisitService.saveReasonForVisit(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            reasonForVisitRepository.flush();
            data.add(obj);
        }
    }
}
