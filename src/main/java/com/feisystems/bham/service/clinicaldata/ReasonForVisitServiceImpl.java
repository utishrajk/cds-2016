package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.ReasonForVisit;
import com.feisystems.bham.domain.clinicaldata.ReasonForVisitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReasonForVisitServiceImpl implements ReasonForVisitService {

	@Autowired
    ReasonForVisitRepository reasonForVisitRepository;

	public long countAllReasonForVisits() {
        return reasonForVisitRepository.count();
    }

	public void deleteReasonForVisit(ReasonForVisit reasonForVisit) {
        reasonForVisitRepository.delete(reasonForVisit);
    }

	public ReasonForVisit findReasonForVisit(Long id) {
        return reasonForVisitRepository.findOne(id);
    }

	public List<ReasonForVisit> findAllReasonForVisits() {
        return reasonForVisitRepository.findAll();
    }

	public List<ReasonForVisit> findReasonForVisitEntries(int firstResult, int maxResults) {
        return reasonForVisitRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveReasonForVisit(ReasonForVisit reasonForVisit) {
        reasonForVisitRepository.save(reasonForVisit);
    }

	public ReasonForVisit updateReasonForVisit(ReasonForVisit reasonForVisit) {
        return reasonForVisitRepository.save(reasonForVisit);
    }
}
