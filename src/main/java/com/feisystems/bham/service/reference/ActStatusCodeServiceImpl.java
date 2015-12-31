package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActStatusCodeServiceImpl implements ActStatusCodeService {

	@Autowired
    ActStatusCodeRepository actStatusCodeRepository;

	public long countAllActStatusCodes() {
        return actStatusCodeRepository.count();
    }

	public void deleteActStatusCode(ActStatusCode actStatusCode) {
        actStatusCodeRepository.delete(actStatusCode);
    }

	public ActStatusCode findActStatusCode(Long id) {
        return actStatusCodeRepository.findOne(id);
    }

	public List<ActStatusCode> findAllActStatusCodes() {
        return actStatusCodeRepository.findAll();
    }

	public List<ActStatusCode> findActStatusCodeEntries(int firstResult, int maxResults) {
        return actStatusCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveActStatusCode(ActStatusCode actStatusCode) {
        actStatusCodeRepository.save(actStatusCode);
    }

	public ActStatusCode updateActStatusCode(ActStatusCode actStatusCode) {
        return actStatusCodeRepository.save(actStatusCode);
    }
}
