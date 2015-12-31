package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultObservation;
import java.util.List;

public interface FunctionalStatusResultObservationService {

	public abstract long countAllFunctionalStatusResultObservations();


	public abstract void deleteFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation);


	public abstract FunctionalStatusResultObservation findFunctionalStatusResultObservation(Long id);


	public abstract List<FunctionalStatusResultObservation> findAllFunctionalStatusResultObservations();


	public abstract List<FunctionalStatusResultObservation> findFunctionalStatusResultObservationEntries(int firstResult, int maxResults);


	public abstract void saveFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation);


	public abstract FunctionalStatusResultObservation updateFunctionalStatusResultObservation(FunctionalStatusResultObservation functionalStatusResultObservation);

}
