package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.AllergyReactionCode;
import java.util.List;

public interface AllergyReactionCodeService {

	public abstract long countAllAllergyReactionCodes();


	public abstract void deleteAllergyReactionCode(AllergyReactionCode allergyReactionCode);


	public abstract AllergyReactionCode findAllergyReactionCode(Long id);


	public abstract List<AllergyReactionCode> findAllAllergyReactionCodes();


	public abstract List<AllergyReactionCode> findAllergyReactionCodeEntries(int firstResult, int maxResults);


	public abstract void saveAllergyReactionCode(AllergyReactionCode allergyReactionCode);


	public abstract AllergyReactionCode updateAllergyReactionCode(AllergyReactionCode allergyReactionCode);

}
