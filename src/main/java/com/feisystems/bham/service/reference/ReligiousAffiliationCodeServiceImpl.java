package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.ReligiousAffiliationCode;
import com.feisystems.bham.domain.reference.ReligiousAffiliationCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReligiousAffiliationCodeServiceImpl implements ReligiousAffiliationCodeService {

	@Autowired
    ReligiousAffiliationCodeRepository religiousAffiliationCodeRepository;

	public long countAllReligiousAffiliationCodes() {
        return religiousAffiliationCodeRepository.count();
    }

	public void deleteReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode) {
        religiousAffiliationCodeRepository.delete(religiousAffiliationCode);
    }

	public ReligiousAffiliationCode findReligiousAffiliationCode(Long id) {
        return religiousAffiliationCodeRepository.findOne(id);
    }

	public List<ReligiousAffiliationCode> findAllReligiousAffiliationCodes() {
        return religiousAffiliationCodeRepository.findAll();
    }

	public List<ReligiousAffiliationCode> findReligiousAffiliationCodeEntries(int firstResult, int maxResults) {
        return religiousAffiliationCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode) {
        religiousAffiliationCodeRepository.save(religiousAffiliationCode);
    }

	public ReligiousAffiliationCode updateReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode) {
        return religiousAffiliationCodeRepository.save(religiousAffiliationCode);
    }
}
