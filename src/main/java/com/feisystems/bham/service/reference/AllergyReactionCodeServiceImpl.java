package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.AllergyReactionCode;
import com.feisystems.bham.domain.reference.AllergyReactionCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyReactionCodeServiceImpl implements AllergyReactionCodeService {

	@Autowired
    AllergyReactionCodeRepository allergyReactionCodeRepository;

	public long countAllAllergyReactionCodes() {
        return allergyReactionCodeRepository.count();
    }

	public void deleteAllergyReactionCode(AllergyReactionCode allergyReactionCode) {
        allergyReactionCodeRepository.delete(allergyReactionCode);
    }

	public AllergyReactionCode findAllergyReactionCode(Long id) {
        return allergyReactionCodeRepository.findOne(id);
    }

	public List<AllergyReactionCode> findAllAllergyReactionCodes() {
        return allergyReactionCodeRepository.findAll();
    }

	public List<AllergyReactionCode> findAllergyReactionCodeEntries(int firstResult, int maxResults) {
        return allergyReactionCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAllergyReactionCode(AllergyReactionCode allergyReactionCode) {
        allergyReactionCodeRepository.save(allergyReactionCode);
    }

	public AllergyReactionCode updateAllergyReactionCode(AllergyReactionCode allergyReactionCode) {
        return allergyReactionCodeRepository.save(allergyReactionCode);
    }
}
