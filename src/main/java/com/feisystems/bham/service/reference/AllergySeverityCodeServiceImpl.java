package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.AllergySeverityCode;
import com.feisystems.bham.domain.reference.AllergySeverityCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergySeverityCodeServiceImpl implements AllergySeverityCodeService {

	@Autowired
    AllergySeverityCodeRepository allergySeverityCodeRepository;

	public long countAllAllergySeverityCodes() {
        return allergySeverityCodeRepository.count();
    }

	public void deleteAllergySeverityCode(AllergySeverityCode allergySeverityCode) {
        allergySeverityCodeRepository.delete(allergySeverityCode);
    }

	public AllergySeverityCode findAllergySeverityCode(Long id) {
        return allergySeverityCodeRepository.findOne(id);
    }

	public List<AllergySeverityCode> findAllAllergySeverityCodes() {
        return allergySeverityCodeRepository.findAll();
    }

	public List<AllergySeverityCode> findAllergySeverityCodeEntries(int firstResult, int maxResults) {
        return allergySeverityCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAllergySeverityCode(AllergySeverityCode allergySeverityCode) {
        allergySeverityCodeRepository.save(allergySeverityCode);
    }

	public AllergySeverityCode updateAllergySeverityCode(AllergySeverityCode allergySeverityCode) {
        return allergySeverityCodeRepository.save(allergySeverityCode);
    }
}
