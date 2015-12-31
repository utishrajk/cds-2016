package com.feisystems.bham.domain.clinicaldata;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.ProblemCodeDataOnDemand;
import com.feisystems.bham.service.clinicaldata.ProblemService;

@Configurable
@Component
public class ProblemDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Problem> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;
	
	@Autowired
	ProblemCodeDataOnDemand problemCodeDataOnDemand;

	@Autowired
    ProblemService problemService;

	@Autowired
    ProblemRepository problemRepository;

	public Problem getNewTransientProblem(int index) {
        Problem obj = new Problem();
        obj.setProblemCode(problemCodeDataOnDemand.getRandomProblemCode());
        obj.setProblemStatusCode(actStatusCodeDataOnDemand.getRandomActStatusCode());
        obj.setPatient(patientDataOnDemand.getRandomPatient());
        setAgeAtOnSet(obj, index);
        setProblemEndDate(obj, index);
        setProblemStartDate(obj, index);
        return obj;
    }
	
	public void setAgeAtOnSet(Problem obj, int index) {
        Integer ageAtOnSet = new Integer(index);
        obj.setAgeAtOnSet(ageAtOnSet);
    }

	public void setProblemEndDate(Problem obj, int index) {
        Date problemEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setProblemEndDate(problemEndDate);
    }

	public void setProblemStartDate(Problem obj, int index) {
        Date problemStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setProblemStartDate(problemStartDate);
    }

	public Problem getSpecificProblem(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Problem obj = data.get(index);
        Long id = obj.getId();
        return problemRepository.findOne(id);
    }

	public Problem getRandomProblem() {
        init();
        Problem obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return problemRepository.findOne(id);
    }

	public boolean modifyProblem(Problem obj) {
        return false;
    }

	public void init() {
        int pageNumber = 0;
        int pageSize = 10;
        data =  problemRepository.findAll(
				new PageRequest(pageNumber, pageSize)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Problem' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Problem>();
        for (int i = 0; i < 10; i++) {
            Problem obj = getNewTransientProblem(i);
            try {
            	problemRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            problemRepository.flush();
            data.add(obj);
        }
    }
}
