package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.Encounter;
import com.feisystems.bham.domain.clinicaldata.EncounterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EncounterServiceImpl implements EncounterService {

	@Autowired
    EncounterRepository encounterRepository;

	public long countAllEncounters() {
        return encounterRepository.count();
    }

	public void deleteEncounter(Encounter encounter) {
        encounterRepository.delete(encounter);
    }

	public Encounter findEncounter(Long id) {
        return encounterRepository.findOne(id);
    }

	public List<Encounter> findAllEncounters() {
        return encounterRepository.findAll();
    }

	public List<Encounter> findEncounterEntries(int firstResult, int maxResults) {
        return encounterRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEncounter(Encounter encounter) {
        encounterRepository.save(encounter);
    }

	public Encounter updateEncounter(Encounter encounter) {
        return encounterRepository.save(encounter);
    }
}
