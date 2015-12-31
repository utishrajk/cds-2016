package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.Medication;
import com.feisystems.bham.domain.clinicaldata.MedicationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationServiceImpl implements MedicationService {

	@Autowired
    MedicationRepository medicationRepository;

	public long countAllMedications() {
        return medicationRepository.count();
    }

	public void deleteMedication(Medication medication) {
        medicationRepository.delete(medication);
    }

	public Medication findMedication(Long id) {
        return medicationRepository.findOne(id);
    }

	public List<Medication> findAllMedications() {
        return medicationRepository.findAll();
    }

	public List<Medication> findMedicationEntries(int firstResult, int maxResults) {
        return medicationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveMedication(Medication medication) {
        medicationRepository.save(medication);
    }

	public Medication updateMedication(Medication medication) {
        return medicationRepository.save(medication);
    }
}
