package com.feisystems.bham.service.clinicaldata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProblemRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.ProblemDto;

@Service
@Transactional(rollbackFor = { ClinicalDataNotFoundException.class })
public class ProblemServiceImpl implements ProblemService {

	/** The problem repository. */
	@Autowired
    ProblemRepository problemRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/** The problem profile dto to problem mapper. */
	@Autowired
	private DtoToDomainEntityMapper<ProblemDto, Problem> problemDtoToProblemMapper;
	
	/** {@inheritDoc} */
	public ProblemDto findProblem(Long id) {
		Problem problem = problemRepository.findOne(id);
		if(problem != null) {
			return modelMapper.map(problem, ProblemDto.class);
		}
		throw new ClinicalDataNotFoundException(id);
    }
	
	/** {@inheritDoc} */
	public List<ProblemDto> findAllProblems() {
		List<ProblemDto> problemDtoList = new ArrayList<>();
        List<Problem> problemList =  problemRepository.findAll();
        
        if (problemList != null &&  problemList.size() > 0) {
            for(Problem problem: problemList) {
            	ProblemDto problemDto = modelMapper.map(problem, ProblemDto.class);
            	problemDtoList.add(problemDto);
            }
            return problemDtoList;
        }
		throw new IllegalArgumentException("No Problems Found");
	}

	/** {@inheritDoc} */
	public void saveProblem(ProblemDto problemDto) {
		Problem problem = problemDtoToProblemMapper.map(problemDto);
		problemRepository.save(problem);
	}

	/** {@inheritDoc} */
	public Problem updateProblem(ProblemDto problemDto) {
		Problem problem = problemDtoToProblemMapper.map(problemDto);
		if (problem != null) {
			return problemRepository.save(problem);	
		} 
		throw new ClinicalDataNotFoundException(problemDto.getId());
    }

	/** {@inheritDoc} */
	public void deleteProblem(ProblemDto problemDto) {
        problemRepository.delete(problemDto.getId());
    }
	
	public List<ProblemDto> findAllProblemsByPatientId(Long patientId) {
		List<ProblemDto> problemDtoList = new ArrayList<>();
		Patient patient = patientRepository.findOne(patientId);
		
		Set<Problem> problemList = patient.getProblems();
		
		for(Problem problem : problemList) {
			ProblemDto problemDto = modelMapper.map(problem, ProblemDto.class);
			problemDtoList.add(problemDto);
		}
		
		return problemDtoList;
	}
	
}
