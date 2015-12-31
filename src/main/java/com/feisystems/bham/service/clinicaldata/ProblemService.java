package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.service.dto.ProblemDto;

import java.util.List;

public interface ProblemService {

	public abstract void deleteProblem(ProblemDto problem);


	public abstract ProblemDto findProblem(Long id);


	public abstract List<ProblemDto> findAllProblems();


	public abstract void saveProblem(ProblemDto problem);


	public abstract Problem updateProblem(ProblemDto problem);
	
	
	public abstract List<ProblemDto> findAllProblemsByPatientId(Long patientId);

}
