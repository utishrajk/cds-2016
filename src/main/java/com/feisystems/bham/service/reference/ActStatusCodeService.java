package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.ActStatusCode;
import java.util.List;

public interface ActStatusCodeService {

	public abstract long countAllActStatusCodes();


	public abstract void deleteActStatusCode(ActStatusCode actStatusCode);


	public abstract ActStatusCode findActStatusCode(Long id);


	public abstract List<ActStatusCode> findAllActStatusCodes();


	public abstract List<ActStatusCode> findActStatusCodeEntries(int firstResult, int maxResults);


	public abstract void saveActStatusCode(ActStatusCode actStatusCode);


	public abstract ActStatusCode updateActStatusCode(ActStatusCode actStatusCode);

}
