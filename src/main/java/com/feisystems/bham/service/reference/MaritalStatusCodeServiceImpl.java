package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.MaritalStatusCode;
import com.feisystems.bham.domain.reference.MaritalStatusCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaritalStatusCodeServiceImpl implements MaritalStatusCodeService {

	@Autowired
    MaritalStatusCodeRepository maritalStatusCodeRepository;

	public long countAllMaritalStatusCodes() {
        return maritalStatusCodeRepository.count();
    }

	public void deleteMaritalStatusCode(MaritalStatusCode maritalStatusCode) {
        maritalStatusCodeRepository.delete(maritalStatusCode);
    }

	public MaritalStatusCode findMaritalStatusCode(Long id) {
        return maritalStatusCodeRepository.findOne(id);
    }

	public List<MaritalStatusCode> findAllMaritalStatusCodes() {
        return maritalStatusCodeRepository.findAll();
    }

	public List<MaritalStatusCode> findMaritalStatusCodeEntries(int firstResult, int maxResults) {
        return maritalStatusCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveMaritalStatusCode(MaritalStatusCode maritalStatusCode) {
        maritalStatusCodeRepository.save(maritalStatusCode);
    }

	public MaritalStatusCode updateMaritalStatusCode(MaritalStatusCode maritalStatusCode) {
        return maritalStatusCodeRepository.save(maritalStatusCode);
    }
}
