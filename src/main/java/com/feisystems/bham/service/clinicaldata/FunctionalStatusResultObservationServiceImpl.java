package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultObservation;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultObservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FunctionalStatusResultObservationServiceImpl implements FunctionalStatusResultObservationService {

	@Autowired
    FunctionalStatusResultObservationRepository functionalStatusResultObservationRepository;

	public long countAllFunctionalStatusResultObservations() {
        return functionalStatusResultObservationRepository.count();
    }

	public void deleteFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation) {
        functionalStatusResultObservationRepository.delete(functionalStatusResultObservation);
    }

	public FunctionalStatusResultObservation findFunctionalStatusResultObservation(Long id) {
        return functionalStatusResultObservationRepository.findOne(id);
    }

	public List<FunctionalStatusResultObservation> findAllFunctionalStatusResultObservations() {
        return functionalStatusResultObservationRepository.findAll();
    }

	public List<FunctionalStatusResultObservation> findFunctionalStatusResultObservationEntries(int firstResult, int maxResults) {
        return functionalStatusResultObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation) {
        functionalStatusResultObservationRepository.save(functionalStatusResultObservation);
    }

	public FunctionalStatusResultObservation updateFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation) {
        return functionalStatusResultObservationRepository.save(functionalStatusResultObservation);
    }
}
