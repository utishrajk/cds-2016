package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.VitalSignOrganizer;
import java.util.List;

public interface VitalSignOrganizerService {

	public abstract long countAllVitalSignOrganizers();


	public abstract void deleteVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer);


	public abstract VitalSignOrganizer findVitalSignOrganizer(Long id);


	public abstract List<VitalSignOrganizer> findAllVitalSignOrganizers();


	public abstract List<VitalSignOrganizer> findVitalSignOrganizerEntries(int firstResult, int maxResults);


	public abstract void saveVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer);


	public abstract VitalSignOrganizer updateVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer);

}
