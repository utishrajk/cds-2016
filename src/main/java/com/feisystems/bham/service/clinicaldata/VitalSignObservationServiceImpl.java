package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.VitalObservationRepository;
import com.feisystems.bham.domain.clinicaldata.VitalSignObservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VitalSignObservationServiceImpl implements VitalSignObservationService {

	@Autowired
    VitalObservationRepository vitalObservationRepository;

	public long countAllVitalSignObservations() {
        return vitalObservationRepository.count();
    }

	public void deleteVitalSignObservation(VitalSignObservation vitalSignObservation) {
        vitalObservationRepository.delete(vitalSignObservation);
    }

	public VitalSignObservation findVitalSignObservation(Long id) {
        return vitalObservationRepository.findOne(id);
    }

	public List<VitalSignObservation> findAllVitalSignObservations() {
        return vitalObservationRepository.findAll();
    }

	public List<VitalSignObservation> findVitalSignObservationEntries(int firstResult, int maxResults) {
        return vitalObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveVitalSignObservation(VitalSignObservation vitalSignObservation) {
        vitalObservationRepository.save(vitalSignObservation);
    }

	public VitalSignObservation updateVitalSignObservation(VitalSignObservation vitalSignObservation) {
        return vitalObservationRepository.save(vitalSignObservation);
    }
}
