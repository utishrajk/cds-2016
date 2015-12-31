package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.AssessmentScaleObservation;
import com.feisystems.bham.domain.clinicaldata.AssessmentScaleObservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssessmentScaleObservationServiceImpl implements AssessmentScaleObservationService {

	@Autowired
    AssessmentScaleObservationRepository assessmentScaleObservationRepository;

	public long countAllAssessmentScaleObservations() {
        return assessmentScaleObservationRepository.count();
    }

	public void deleteAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation) {
        assessmentScaleObservationRepository.delete(assessmentScaleObservation);
    }

	public AssessmentScaleObservation findAssessmentScaleObservation(Long id) {
        return assessmentScaleObservationRepository.findOne(id);
    }

	public List<AssessmentScaleObservation> findAllAssessmentScaleObservations() {
        return assessmentScaleObservationRepository.findAll();
    }

	public List<AssessmentScaleObservation> findAssessmentScaleObservationEntries(int firstResult, int maxResults) {
        return assessmentScaleObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation) {
        assessmentScaleObservationRepository.save(assessmentScaleObservation);
    }

	public AssessmentScaleObservation updateAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation) {
        return assessmentScaleObservationRepository.save(assessmentScaleObservation);
    }
}
