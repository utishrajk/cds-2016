package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.AllergySeverityCode;
import java.util.List;

public interface AllergySeverityCodeService {

	public abstract long countAllAllergySeverityCodes();


	public abstract void deleteAllergySeverityCode(AllergySeverityCode allergySeverityCode);


	public abstract AllergySeverityCode findAllergySeverityCode(Long id);


	public abstract List<AllergySeverityCode> findAllAllergySeverityCodes();


	public abstract List<AllergySeverityCode> findAllergySeverityCodeEntries(int firstResult, int maxResults);


	public abstract void saveAllergySeverityCode(AllergySeverityCode allergySeverityCode);


	public abstract AllergySeverityCode updateAllergySeverityCode(AllergySeverityCode allergySeverityCode);

}
