package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.ResultObservation;
import java.util.List;

public interface ResultObservationService {

	public abstract long countAllResultObservations();


	public abstract void deleteResultObservation(ResultObservation resultObservation);


	public abstract ResultObservation findResultObservation(Long id);


	public abstract List<ResultObservation> findAllResultObservations();


	public abstract List<ResultObservation> findResultObservationEntries(int firstResult, int maxResults);


	public abstract void saveResultObservation(ResultObservation resultObservation);


	public abstract ResultObservation updateResultObservation(ResultObservation resultObservation);

}
