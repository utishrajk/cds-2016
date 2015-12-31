package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.ResultOrganizer;
import java.util.List;

public interface ResultOrganizerService {

	public abstract long countAllResultOrganizers();


	public abstract void deleteResultOrganizer(ResultOrganizer resultOrganizer);


	public abstract ResultOrganizer findResultOrganizer(Long id);


	public abstract List<ResultOrganizer> findAllResultOrganizers();


	public abstract List<ResultOrganizer> findResultOrganizerEntries(int firstResult, int maxResults);


	public abstract void saveResultOrganizer(ResultOrganizer resultOrganizer);


	public abstract ResultOrganizer updateResultOrganizer(ResultOrganizer resultOrganizer);

}
