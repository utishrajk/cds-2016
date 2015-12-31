package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.Encounter;
import java.util.List;

public interface EncounterService {

	public abstract long countAllEncounters();


	public abstract void deleteEncounter(Encounter encounter);


	public abstract Encounter findEncounter(Long id);


	public abstract List<Encounter> findAllEncounters();


	public abstract List<Encounter> findEncounterEntries(int firstResult, int maxResults);


	public abstract void saveEncounter(Encounter encounter);


	public abstract Encounter updateEncounter(Encounter encounter);

}
