package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusProblemObservation;
import java.util.List;

public interface FunctionalStatusProblemObservationService {

	public abstract long countAllFunctionalStatusProblemObservations();


	public abstract void deleteFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation);


	public abstract FunctionalStatusProblemObservation findFunctionalStatusProblemObservation(Long id);


	public abstract List<FunctionalStatusProblemObservation> findAllFunctionalStatusProblemObservations();


	public abstract List<FunctionalStatusProblemObservation> findFunctionalStatusProblemObservationEntries(int firstResult, int maxResults);


	public abstract void saveFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation);


	public abstract FunctionalStatusProblemObservation updateFunctionalStatusProblemObservation(FunctionalStatusProblemObservation functionalStatusProblemObservation);

}
