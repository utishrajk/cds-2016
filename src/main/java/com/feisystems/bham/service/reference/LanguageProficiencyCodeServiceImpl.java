package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.LanguageProficiencyCode;
import com.feisystems.bham.domain.reference.LanguageProficiencyCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageProficiencyCodeServiceImpl implements LanguageProficiencyCodeService {

	@Autowired
    LanguageProficiencyCodeRepository languageProficiencyCodeRepository;

	public long countAllLanguageProficiencyCodes() {
        return languageProficiencyCodeRepository.count();
    }

	public void deleteLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode) {
        languageProficiencyCodeRepository.delete(languageProficiencyCode);
    }

	public LanguageProficiencyCode findLanguageProficiencyCode(Long id) {
        return languageProficiencyCodeRepository.findOne(id);
    }

	public List<LanguageProficiencyCode> findAllLanguageProficiencyCodes() {
        return languageProficiencyCodeRepository.findAll();
    }

	public List<LanguageProficiencyCode> findLanguageProficiencyCodeEntries(int firstResult, int maxResults) {
        return languageProficiencyCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode) {
        languageProficiencyCodeRepository.save(languageProficiencyCode);
    }

	public LanguageProficiencyCode updateLanguageProficiencyCode(LanguageProficiencyCode languageProficiencyCode) {
        return languageProficiencyCodeRepository.save(languageProficiencyCode);
    }
}
