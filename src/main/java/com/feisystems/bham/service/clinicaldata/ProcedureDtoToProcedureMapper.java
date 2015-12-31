package com.feisystems.bham.service.clinicaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ProcedureObservationRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.reference.BodySiteCodeRepository;
import com.feisystems.bham.domain.reference.ProcedureTypeCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCodeRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

@Component
public class ProcedureDtoToProcedureMapper implements DtoToDomainEntityMapper<ProcedureObservationDto, ProcedureObservation> {
	
	private ProcedureObservationRepository procedureObservationRepository;
	
	private ProcedureTypeCodeRepository procedureTypeCodeRepository;
	
	private PatientRepository patientRepository;
	
	private ActStatusCodeRepository actStatusCodeRepository;
	
	private BodySiteCodeRepository bodySiteCodeRepository;
	
	@Autowired
	public ProcedureDtoToProcedureMapper(ProcedureObservationRepository procedureObservationRepository,
			ProcedureTypeCodeRepository procedureTypeCodeRepository, PatientRepository patientRepository, ActStatusCodeRepository actStatusCodeRepository,
			BodySiteCodeRepository bodySiteCodeRepository) {
		super();
		this.procedureObservationRepository = procedureObservationRepository;
		this.procedureTypeCodeRepository = procedureTypeCodeRepository;
		this.patientRepository = patientRepository;
		this.actStatusCodeRepository = actStatusCodeRepository;
		this.bodySiteCodeRepository = bodySiteCodeRepository;
	}

	@Override
	public ProcedureObservation map(ProcedureObservationDto dto) {
		
		ProcedureObservation procedure = null;
		
		if(dto.getId() != null) {
			procedure = procedureObservationRepository.findOne(dto.getId());
		} else {
			procedure = new ProcedureObservation();
		}
		
		if(procedure == null) {
			return null;
		}
		
		procedure.setProcedureStartDate(dto.getProcedureStartDate());
		procedure.setProcedureEndDate(dto.getProcedureEndDate());
		
		if(StringUtils.hasText(dto.getProcedureStatusCode())) {
			ProcedureTypeCode procedureTypeCode = procedureTypeCodeRepository.findByCode(dto.getProcedureTypeCode());
			
			procedure.setProcedureType(procedureTypeCode);
		}
		
		if(StringUtils.hasText(dto.getProcedureStatusCode())) {
			ActStatusCode actStatusCode = actStatusCodeRepository.findByCode(dto.getProcedureStatusCode());
			
			procedure.setProcedureStatusCode(actStatusCode);
		}
		
		if(StringUtils.hasText(dto.getBodySiteCode())) {
			BodySiteCode bodySiteCode = bodySiteCodeRepository.findByCode(dto.getBodySiteCode());
			
			procedure.setBodySiteCode(bodySiteCode);
		}
		
		Patient patient = patientRepository.findOne(dto.getPatientId());
		procedure.setPatient(patient);
		
		return procedure;
	}

}
