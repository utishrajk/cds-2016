package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.MaritalStatusCode;
import java.util.List;

public interface MaritalStatusCodeService {

	public abstract long countAllMaritalStatusCodes();


	public abstract void deleteMaritalStatusCode(MaritalStatusCode maritalStatusCode);


	public abstract MaritalStatusCode findMaritalStatusCode(Long id);


	public abstract List<MaritalStatusCode> findAllMaritalStatusCodes();


	public abstract List<MaritalStatusCode> findMaritalStatusCodeEntries(int firstResult, int maxResults);


	public abstract void saveMaritalStatusCode(MaritalStatusCode maritalStatusCode);


	public abstract MaritalStatusCode updateMaritalStatusCode(MaritalStatusCode maritalStatusCode);

}
