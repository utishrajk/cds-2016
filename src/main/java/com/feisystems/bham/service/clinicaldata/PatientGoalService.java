package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.PatientGoal;
import java.util.List;

public interface PatientGoalService {

	public abstract long countAllPatientGoals();


	public abstract void deletePatientGoal(PatientGoal patientGoal);


	public abstract PatientGoal findPatientGoal(Long id);


	public abstract List<PatientGoal> findAllPatientGoals();


	public abstract List<PatientGoal> findPatientGoalEntries(int firstResult, int maxResults);


	public abstract void savePatientGoal(PatientGoal patientGoal);


	public abstract PatientGoal updatePatientGoal(PatientGoal patientGoal);

}
