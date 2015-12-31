package com.feisystems.bham.service.clinicaldata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProblemRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.ProblemCode;
import com.feisystems.bham.domain.reference.ProblemCodeRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.ProblemDto;

@Component
public class ProblemDtoToProblemMapper implements
		DtoToDomainEntityMapper<ProblemDto, Problem> {

	private ProblemRepository problemRepository;
	
	private PatientRepository patientRepository;
	
	private ProblemCodeRepository problemCodeRepository;
	
	private ActStatusCodeRepository actStatusCodeRepository;


	@Autowired
	public ProblemDtoToProblemMapper(
			ProblemRepository problemRepository,
			ProblemCodeRepository problemCodeRepository,
			ActStatusCodeRepository actStatusCodeRepository,
			PatientRepository patientRepository
			) {
		
		super();
		
		this.problemRepository = problemRepository;
		this.problemCodeRepository = problemCodeRepository;
		this.actStatusCodeRepository = actStatusCodeRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public Problem map(ProblemDto problemDto) {
		Problem problem = null; 
		
		if(problemDto.getId() != null) {
			problem = problemRepository.findOne(problemDto.getId());
		} else {
			problem = new Problem();
		}
		
		if(problem == null) {
			return null;
		}
		
		problem.setProblemStartDate(problemDto.getStartDate());
		problem.setProblemEndDate(problemDto.getEndDate());
		
		if(StringUtils.hasText(problemDto.getProblemCode())) {
			ProblemCode problemCode = problemCodeRepository.findByCode(problemDto.getProblemCode());
			
			problem.setProblemCode(problemCode);
		}
		
		if(StringUtils.hasText(problemDto.getProblemStatusCode())) {
			ActStatusCode actStatusCode = actStatusCodeRepository.findByCode(problemDto.getProblemStatusCode());
			
			problem.setProblemStatusCode(actStatusCode);
		}
		
		//just saving in the same patient for now
		//Patient patient = patientRepository.findOne(new Long(213));
		Patient patient = patientRepository.findOne(problemDto.getPatientId());
		problem.setPatient(patient);

		return problem;
	}

}
