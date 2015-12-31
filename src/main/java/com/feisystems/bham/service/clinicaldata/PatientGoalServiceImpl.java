package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.PatientGoal;
import com.feisystems.bham.domain.clinicaldata.PatientGoalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientGoalServiceImpl implements PatientGoalService {

	@Autowired
    PatientGoalRepository patientGoalRepository;

	public long countAllPatientGoals() {
        return patientGoalRepository.count();
    }

	public void deletePatientGoal(PatientGoal patientGoal) {
        patientGoalRepository.delete(patientGoal);
    }

	public PatientGoal findPatientGoal(Long id) {
        return patientGoalRepository.findOne(id);
    }

	public List<PatientGoal> findAllPatientGoals() {
        return patientGoalRepository.findAll();
    }

	public List<PatientGoal> findPatientGoalEntries(int firstResult, int maxResults) {
        return patientGoalRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePatientGoal(PatientGoal patientGoal) {
        patientGoalRepository.save(patientGoal);
    }

	public PatientGoal updatePatientGoal(PatientGoal patientGoal) {
        return patientGoalRepository.save(patientGoal);
    }
}
