package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.LanguageAbilityCode;
import com.feisystems.bham.domain.reference.LanguageAbilityCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageAbilityCodeServiceImpl implements LanguageAbilityCodeService {

	@Autowired
    LanguageAbilityCodeRepository languageAbilityCodeRepository;

	public long countAllLanguageAbilityCodes() {
        return languageAbilityCodeRepository.count();
    }

	public void deleteLanguageAbilityCode(LanguageAbilityCode languageAbilityCode) {
        languageAbilityCodeRepository.delete(languageAbilityCode);
    }

	public LanguageAbilityCode findLanguageAbilityCode(Long id) {
        return languageAbilityCodeRepository.findOne(id);
    }

	public List<LanguageAbilityCode> findAllLanguageAbilityCodes() {
        return languageAbilityCodeRepository.findAll();
    }

	public List<LanguageAbilityCode> findLanguageAbilityCodeEntries(int firstResult, int maxResults) {
        return languageAbilityCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveLanguageAbilityCode(LanguageAbilityCode languageAbilityCode) {
        languageAbilityCodeRepository.save(languageAbilityCode);
    }

	public LanguageAbilityCode updateLanguageAbilityCode(LanguageAbilityCode languageAbilityCode) {
        return languageAbilityCodeRepository.save(languageAbilityCode);
    }
}
