package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.AssessmentScaleObservation;
import java.util.List;

public interface AssessmentScaleObservationService {

	public abstract long countAllAssessmentScaleObservations();


	public abstract void deleteAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation);


	public abstract AssessmentScaleObservation findAssessmentScaleObservation(Long id);


	public abstract List<AssessmentScaleObservation> findAllAssessmentScaleObservations();


	public abstract List<AssessmentScaleObservation> findAssessmentScaleObservationEntries(int firstResult, int maxResults);


	public abstract void saveAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation);


	public abstract AssessmentScaleObservation updateAssessmentScaleObservation(AssessmentScaleObservation assessmentScaleObservation);

}
