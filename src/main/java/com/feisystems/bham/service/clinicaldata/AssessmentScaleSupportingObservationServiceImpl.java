package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.AssessmentScaleSupportingObservation;
import com.feisystems.bham.domain.clinicaldata.AssessmentScaleSupportingObservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssessmentScaleSupportingObservationServiceImpl implements AssessmentScaleSupportingObservationService {

	@Autowired
    AssessmentScaleSupportingObservationRepository assessmentScaleSupportingObservationRepository;

	public long countAllAssessmentScaleSupportingObservations() {
        return assessmentScaleSupportingObservationRepository.count();
    }

	public void deleteAssessmentScaleSupportingObservation(AssessmentScaleSupportingObservation assessmentScaleSupportingObservation) {
        assessmentScaleSupportingObservationRepository.delete(assessmentScaleSupportingObservation);
    }

	public AssessmentScaleSupportingObservation findAssessmentScaleSupportingObservation(Long id) {
        return assessmentScaleSupportingObservationRepository.findOne(id);
    }

	public List<AssessmentScaleSupportingObservation> findAllAssessmentScaleSupportingObservations() {
        return assessmentScaleSupportingObservationRepository.findAll();
    }

	public List<AssessmentScaleSupportingObservation> findAssessmentScaleSupportingObservationEntries(int firstResult, int maxResults) {
        return assessmentScaleSupportingObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAssessmentScaleSupportingObservation(AssessmentScaleSupportingObservation assessmentScaleSupportingObservation) {
        assessmentScaleSupportingObservationRepository.save(assessmentScaleSupportingObservation);
    }

	public AssessmentScaleSupportingObservation updateAssessmentScaleSupportingObservation(AssessmentScaleSupportingObservation assessmentScaleSupportingObservation) {
        return assessmentScaleSupportingObservationRepository.save(assessmentScaleSupportingObservation);
    }
}
