package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultOrganizer;
import java.util.List;

public interface FunctionalStatusResultOrganizerService {

	public abstract long countAllFunctionalStatusResultOrganizers();


	public abstract void deleteFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer);


	public abstract FunctionalStatusResultOrganizer findFunctionalStatusResultOrganizer(Long id);


	public abstract List<FunctionalStatusResultOrganizer> findAllFunctionalStatusResultOrganizers();


	public abstract List<FunctionalStatusResultOrganizer> findFunctionalStatusResultOrganizerEntries(int firstResult, int maxResults);


	public abstract void saveFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer);


	public abstract FunctionalStatusResultOrganizer updateFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer);

}
