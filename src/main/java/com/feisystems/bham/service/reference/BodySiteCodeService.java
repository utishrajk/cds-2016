package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.BodySiteCode;
import java.util.List;

public interface BodySiteCodeService {

	public abstract long countAllBodySiteCodes();


	public abstract void deleteBodySiteCode(BodySiteCode bodySiteCode);


	public abstract BodySiteCode findBodySiteCode(Long id);


	public abstract List<BodySiteCode> findAllBodySiteCodes();


	public abstract List<BodySiteCode> findBodySiteCodeEntries(int firstResult, int maxResults);


	public abstract void saveBodySiteCode(BodySiteCode bodySiteCode);


	public abstract BodySiteCode updateBodySiteCode(BodySiteCode bodySiteCode);

}
