package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.reference.BodySiteCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BodySiteCodeServiceImpl implements BodySiteCodeService {

	@Autowired
    BodySiteCodeRepository bodySiteCodeRepository;

	public long countAllBodySiteCodes() {
        return bodySiteCodeRepository.count();
    }

	public void deleteBodySiteCode(BodySiteCode bodySiteCode) {
        bodySiteCodeRepository.delete(bodySiteCode);
    }

	public BodySiteCode findBodySiteCode(Long id) {
        return bodySiteCodeRepository.findOne(id);
    }

	public List<BodySiteCode> findAllBodySiteCodes() {
        return bodySiteCodeRepository.findAll();
    }

	public List<BodySiteCode> findBodySiteCodeEntries(int firstResult, int maxResults) {
        return bodySiteCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveBodySiteCode(BodySiteCode bodySiteCode) {
        bodySiteCodeRepository.save(bodySiteCode);
    }

	public BodySiteCode updateBodySiteCode(BodySiteCode bodySiteCode) {
        return bodySiteCodeRepository.save(bodySiteCode);
    }
}
