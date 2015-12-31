package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.AdverseEventTypeCode;
import java.util.List;

public interface AdverseEventTypeCodeService {

	public abstract long countAllAdverseEventTypeCodes();


	public abstract void deleteAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode);


	public abstract AdverseEventTypeCode findAdverseEventTypeCode(Long id);


	public abstract List<AdverseEventTypeCode> findAllAdverseEventTypeCodes();


	public abstract List<AdverseEventTypeCode> findAdverseEventTypeCodeEntries(int firstResult, int maxResults);


	public abstract void saveAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode);


	public abstract AdverseEventTypeCode updateAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode);

}
