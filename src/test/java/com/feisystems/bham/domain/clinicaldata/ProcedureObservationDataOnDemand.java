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
import com.feisystems.bham.domain.reference.BodySiteCodeDataOnDemand;
import com.feisystems.bham.domain.reference.ProcedureTypeCodeDataOnDemand;
import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;

@Component
@Configurable
public class ProcedureObservationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ProcedureObservation> data;

	@Autowired
	BodySiteCodeDataOnDemand bodySiteCodeDataOnDemand;

	@Autowired
	PatientDataOnDemand patientDataOnDemand;

	@Autowired
	ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
	ProcedureTypeCodeDataOnDemand procedureTypeCodeDataDemand;

	@Autowired
	ProcedureObservationService procedureObservationService;

	@Autowired
	ProcedureObservationRepository procedureObservationRepository;

	public ProcedureObservation getNewTransientProcedureObservation(int index) {
		ProcedureObservation obj = new ProcedureObservation();
		obj.setProcedureType(procedureTypeCodeDataDemand.getRandomProcedureTypeCode());
		obj.setProcedureStatusCode(actStatusCodeDataOnDemand.getRandomActStatusCode());
		setProcedureEndDate(obj, index);
		setProcedureStartDate(obj, index);
		return obj;
	}

	public void setProcedureEndDate(ProcedureObservation obj, int index) {
		Date procedureEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar
				.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE),
				Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
		obj.setProcedureEndDate(procedureEndDate);
	}

	public void setProcedureStartDate(ProcedureObservation obj, int index) {
		Date procedureStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar
				.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE),
				Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
		obj.setProcedureStartDate(procedureStartDate);
	}

	public ProcedureObservation getSpecificProcedureObservation(int index) {
		init();
		if (index < 0) {
			index = 0;
		}
		if (index > (data.size() - 1)) {
			index = data.size() - 1;
		}
		ProcedureObservation obj = data.get(index);
		Long id = obj.getId();
		return procedureObservationRepository.findOne(id);
	}

	public ProcedureObservation getRandomProcedureObservation() {
		init();
		ProcedureObservation obj = data.get(rnd.nextInt(data.size()));
		Long id = obj.getId();
		return procedureObservationRepository.findOne(id);
	}

	public boolean modifyProcedureObservation(ProcedureObservation obj) {
		return false;
	}

	public void init() {
		int pageNumber = 0;
		int pageSize = 10;
		data = procedureObservationRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
		if (data == null) {
			throw new IllegalStateException("Find entries implementation for 'Problem' illegally returned null");
		}
		if (!data.isEmpty()) {
			return;
		}

		data = new ArrayList<ProcedureObservation>();
		for (int i = 0; i < 10; i++) {
			ProcedureObservation obj = getNewTransientProcedureObservation(i);
			try {
				procedureObservationRepository.save(obj);
			} catch (final ConstraintViolationException e) {
				final StringBuilder msg = new StringBuilder();
				for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
					final ConstraintViolation<?> cv = iter.next();
					msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage())
							.append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
				}
				throw new IllegalStateException(msg.toString(), e);
			}
			procedureObservationRepository.flush();
			data.add(obj);
		}
	}
}
