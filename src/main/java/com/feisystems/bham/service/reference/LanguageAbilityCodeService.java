package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.LanguageAbilityCode;
import java.util.List;

public interface LanguageAbilityCodeService {

	public abstract long countAllLanguageAbilityCodes();


	public abstract void deleteLanguageAbilityCode(LanguageAbilityCode languageAbilityCode);


	public abstract LanguageAbilityCode findLanguageAbilityCode(Long id);


	public abstract List<LanguageAbilityCode> findAllLanguageAbilityCodes();


	public abstract List<LanguageAbilityCode> findLanguageAbilityCodeEntries(int firstResult, int maxResults);


	public abstract void saveLanguageAbilityCode(LanguageAbilityCode languageAbilityCode);


	public abstract LanguageAbilityCode updateLanguageAbilityCode(LanguageAbilityCode languageAbilityCode);

}
