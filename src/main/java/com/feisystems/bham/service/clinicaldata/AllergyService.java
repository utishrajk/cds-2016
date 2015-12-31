package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.Allergy;
import java.util.List;

public interface AllergyService {

	public abstract long countAllAllergys();


	public abstract void deleteAllergy(Allergy allergy);


	public abstract Allergy findAllergy(Long id);


	public abstract List<Allergy> findAllAllergys();


	public abstract List<Allergy> findAllergyEntries(int firstResult, int maxResults);


	public abstract void saveAllergy(Allergy allergy);


	public abstract Allergy updateAllergy(Allergy allergy);

}
