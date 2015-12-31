package com.feisystems.bham.service.patient;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.service.dto.PatientProfileDto;

import java.util.List;

public interface PatientService {

	/**
	 * Delete Patient
	 * 
	 * @param patientProfileDto the patient profile dto
	 */
	public void deletePatient(PatientProfileDto patientProfileDto );

	/**
	 * Find Patient by Id
	 * 
	 * @param id patient id
	 * @return the patient profile dto
	 */
	public PatientProfileDto findPatient(Long id);

	/**
	 * Find all patients
	 * @return list of all patient profile dto
	 */
	public List<PatientProfileDto> findAllPatients();

	/**
	 * Save Patient
	 * 
	 * @param patientProfileDto the patient profile dto
	 */
	public void savePatient(PatientProfileDto patientProfileDto);

	/**
	 * Update Patient
	 * 
	 * @param patientProfileDto the patient profile dto
	 * @return the patient profile dto
	 */
	public Patient updatePatient(PatientProfileDto patientProfileDto);

	
}
