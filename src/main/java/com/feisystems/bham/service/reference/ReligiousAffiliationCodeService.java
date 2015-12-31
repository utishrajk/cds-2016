package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.ReligiousAffiliationCode;
import java.util.List;

public interface ReligiousAffiliationCodeService {

	public abstract long countAllReligiousAffiliationCodes();


	public abstract void deleteReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode);


	public abstract ReligiousAffiliationCode findReligiousAffiliationCode(Long id);


	public abstract List<ReligiousAffiliationCode> findAllReligiousAffiliationCodes();


	public abstract List<ReligiousAffiliationCode> findReligiousAffiliationCodeEntries(int firstResult, int maxResults);


	public abstract void saveReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode);


	public abstract ReligiousAffiliationCode updateReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode);

}
