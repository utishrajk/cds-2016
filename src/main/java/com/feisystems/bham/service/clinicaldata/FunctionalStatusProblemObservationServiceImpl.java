package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusProblemObservation;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusProblemObservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FunctionalStatusProblemObservationServiceImpl implements FunctionalStatusProblemObservationService {

	@Autowired
    FunctionalStatusProblemObservationRepository functionalStatusProblemObservationRepository;

	public long countAllFunctionalStatusProblemObservations() {
        return functionalStatusProblemObservationRepository.count();
    }

	public void deleteFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation) {
        functionalStatusProblemObservationRepository.delete(functionalStatusProblemObservation);
    }

	public FunctionalStatusProblemObservation findFunctionalStatusProblemObservation(Long id) {
        return functionalStatusProblemObservationRepository.findOne(id);
    }

	public List<FunctionalStatusProblemObservation> findAllFunctionalStatusProblemObservations() {
        return functionalStatusProblemObservationRepository.findAll();
    }

	public List<FunctionalStatusProblemObservation> findFunctionalStatusProblemObservationEntries(int firstResult, int maxResults) {
        return functionalStatusProblemObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation) {
        functionalStatusProblemObservationRepository.save(functionalStatusProblemObservation);
    }

	public FunctionalStatusProblemObservation updateFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation) {
        return functionalStatusProblemObservationRepository.save(functionalStatusProblemObservation);
    }
}
