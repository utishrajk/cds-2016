package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.LanguageProficiencyCode;
import java.util.List;

public interface LanguageProficiencyCodeService {

	public abstract long countAllLanguageProficiencyCodes();


	public abstract void deleteLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode);


	public abstract LanguageProficiencyCode findLanguageProficiencyCode(Long id);


	public abstract List<LanguageProficiencyCode> findAllLanguageProficiencyCodes();


	public abstract List<LanguageProficiencyCode> findLanguageProficiencyCodeEntries(int firstResult, int maxResults);


	public abstract void saveLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode);


	public abstract LanguageProficiencyCode updateLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode);

}
