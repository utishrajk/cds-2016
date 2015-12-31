package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.Medication;
import java.util.List;

public interface MedicationService {

	public abstract long countAllMedications();


	public abstract void deleteMedication(Medication medication);


	public abstract Medication findMedication(Long id);


	public abstract List<Medication> findAllMedications();


	public abstract List<Medication> findMedicationEntries(int firstResult, int maxResults);


	public abstract void saveMedication(Medication medication);


	public abstract Medication updateMedication(Medication medication);

}
