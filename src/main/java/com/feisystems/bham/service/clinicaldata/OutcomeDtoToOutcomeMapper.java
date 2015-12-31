package com.feisystems.bham.service.clinicaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.feisystems.bham.domain.clinicaldata.Outcome;
import com.feisystems.bham.domain.clinicaldata.OutcomeRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.CgiICode;
import com.feisystems.bham.domain.reference.CgiICodeRepository;
import com.feisystems.bham.domain.reference.CgiSCode;
import com.feisystems.bham.domain.reference.CgiSCodeRepository;
import com.feisystems.bham.domain.reference.ProcedureTypeCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCodeRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.OutcomeDto;

@Component
public class OutcomeDtoToOutcomeMapper implements DtoToDomainEntityMapper<OutcomeDto, Outcome> {
	private OutcomeRepository outcomeRepository;

	private ProcedureTypeCodeRepository outcomeTypeCodeRepository;

	private PatientRepository patientRepository;

	private CgiICodeRepository cgiICodeRepository;

	private CgiSCodeRepository cgiSCodeRepository;

	@Autowired
	public OutcomeDtoToOutcomeMapper(OutcomeRepository outcomeRepository, ProcedureTypeCodeRepository outcomeTypeCodeRepository,
			PatientRepository patientRepository, CgiICodeRepository cgiICodeRepository, CgiSCodeRepository cgiSCodeRepository) {
		super();
		this.outcomeRepository = outcomeRepository;
		this.outcomeTypeCodeRepository = outcomeTypeCodeRepository;
		this.patientRepository = patientRepository;
		this.cgiICodeRepository = cgiICodeRepository;
		this.cgiSCodeRepository = cgiSCodeRepository;
	}

	@Override
	public Outcome map(OutcomeDto dto) {

		Outcome outcome = null;

		if (dto.getId() != null) {
			outcome = outcomeRepository.findOne(dto.getId());
		} else {
			outcome = new Outcome();
		}

		if (outcome == null) {
			return null;
		}

		outcome.setStartDate(dto.getStartDate());
		outcome.setEndDate(dto.getEndDate());
		outcome.setSymptoms(dto.getSymptoms());
		outcome.setTolerability(dto.getTolerability());

		if (StringUtils.hasText(dto.getProcedureTypeCode())) {
			ProcedureTypeCode outcomeTypeCode = outcomeTypeCodeRepository.findByCode(dto.getProcedureTypeCode());
			outcome.setProcedureType(outcomeTypeCode);
		}

		if (StringUtils.hasText(dto.getCgiICode())) {
			CgiICode cgiICode = cgiICodeRepository.findByCode(dto.getCgiICode());
			outcome.setCgiICode(cgiICode);
		}

		if (StringUtils.hasText(dto.getCgiSCode())) {
			CgiSCode cgiSCode = cgiSCodeRepository.findByCode(dto.getCgiSCode());
			outcome.setCgiSCode(cgiSCode);
		}

		Patient patient = patientRepository.findOne(dto.getPatientId());
		outcome.setPatient(patient);

		return outcome;
	}

}
