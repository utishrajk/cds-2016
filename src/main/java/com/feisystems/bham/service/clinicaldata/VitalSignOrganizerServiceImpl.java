package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.VitalSignOrganizer;
import com.feisystems.bham.domain.clinicaldata.VitalSignOrganizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VitalSignOrganizerServiceImpl implements VitalSignOrganizerService {

	@Autowired
    VitalSignOrganizerRepository vitalSignOrganizerRepository;

	public long countAllVitalSignOrganizers() {
        return vitalSignOrganizerRepository.count();
    }

	public void deleteVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer) {
        vitalSignOrganizerRepository.delete(vitalSignOrganizer);
    }

	public VitalSignOrganizer findVitalSignOrganizer(Long id) {
        return vitalSignOrganizerRepository.findOne(id);
    }

	public List<VitalSignOrganizer> findAllVitalSignOrganizers() {
        return vitalSignOrganizerRepository.findAll();
    }

	public List<VitalSignOrganizer> findVitalSignOrganizerEntries(int firstResult, int maxResults) {
        return vitalSignOrganizerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer) {
        vitalSignOrganizerRepository.save(vitalSignOrganizer);
    }

	public VitalSignOrganizer updateVitalSignOrganizer(VitalSignOrganizer vitalSignOrganizer) {
        return vitalSignOrganizerRepository.save(vitalSignOrganizer);
    }
}
