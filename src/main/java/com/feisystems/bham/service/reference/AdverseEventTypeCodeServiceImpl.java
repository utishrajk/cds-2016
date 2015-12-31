package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.AdverseEventTypeCode;
import com.feisystems.bham.domain.reference.AdverseEventTypeCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdverseEventTypeCodeServiceImpl implements AdverseEventTypeCodeService {

	@Autowired
    AdverseEventTypeCodeRepository adverseEventTypeCodeRepository;

	public long countAllAdverseEventTypeCodes() {
        return adverseEventTypeCodeRepository.count();
    }

	public void deleteAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode) {
        adverseEventTypeCodeRepository.delete(adverseEventTypeCode);
    }

	public AdverseEventTypeCode findAdverseEventTypeCode(Long id) {
        return adverseEventTypeCodeRepository.findOne(id);
    }

	public List<AdverseEventTypeCode> findAllAdverseEventTypeCodes() {
        return adverseEventTypeCodeRepository.findAll();
    }

	public List<AdverseEventTypeCode> findAdverseEventTypeCodeEntries(int firstResult, int maxResults) {
        return adverseEventTypeCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode) {
        adverseEventTypeCodeRepository.save(adverseEventTypeCode);
    }

	public AdverseEventTypeCode updateAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode) {
        return adverseEventTypeCodeRepository.save(adverseEventTypeCode);
    }
}
