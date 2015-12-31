package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.service.clinicaldata.PlanOfCareService;
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
public class PlanOfCareDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<PlanOfCare> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    PlanOfCareService planOfCareService;

	@Autowired
    PlanOfCareRepository planOfCareRepository;

	public PlanOfCare getNewTransientPlanOfCare(int index) {
        PlanOfCare obj = new PlanOfCare();
        setPlanOfCareDateTime(obj, index);
        return obj;
    }

	public void setPlanOfCareDateTime(PlanOfCare obj, int index) {
        Date planOfCareDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setPlanOfCareDateTime(planOfCareDateTime);
    }

	public PlanOfCare getSpecificPlanOfCare(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PlanOfCare obj = data.get(index);
        Long id = obj.getId();
        return planOfCareService.findPlanOfCare(id);
    }

	public PlanOfCare getRandomPlanOfCare() {
        init();
        PlanOfCare obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return planOfCareService.findPlanOfCare(id);
    }

	public boolean modifyPlanOfCare(PlanOfCare obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = planOfCareService.findPlanOfCareEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PlanOfCare' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PlanOfCare>();
        for (int i = 0; i < 10; i++) {
            PlanOfCare obj = getNewTransientPlanOfCare(i);
            try {
                planOfCareService.savePlanOfCare(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            planOfCareRepository.flush();
            data.add(obj);
        }
    }
}
