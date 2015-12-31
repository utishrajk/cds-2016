package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.LanguageCode;
import com.feisystems.bham.domain.reference.LanguageCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageCodeServiceImpl implements LanguageCodeService {

	@Autowired
    LanguageCodeRepository languageCodeRepository;

	public long countAllLanguageCodes() {
        return languageCodeRepository.count();
    }

	public void deleteLanguageCode(LanguageCode languageCode) {
        languageCodeRepository.delete(languageCode);
    }

	public LanguageCode findLanguageCode(Long id) {
        return languageCodeRepository.findOne(id);
    }

	public List<LanguageCode> findAllLanguageCodes() {
        return languageCodeRepository.findAll();
    }

	public List<LanguageCode> findLanguageCodeEntries(int firstResult, int maxResults) {
        return languageCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveLanguageCode(LanguageCode languageCode) {
        languageCodeRepository.save(languageCode);
    }

	public LanguageCode updateLanguageCode(LanguageCode languageCode) {
        return languageCodeRepository.save(languageCode);
    }
}
