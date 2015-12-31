package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.ReasonForVisit;
import java.util.List;

public interface ReasonForVisitService {

	public abstract long countAllReasonForVisits();


	public abstract void deleteReasonForVisit(ReasonForVisit reasonForVisit);


	public abstract ReasonForVisit findReasonForVisit(Long id);


	public abstract List<ReasonForVisit> findAllReasonForVisits();


	public abstract List<ReasonForVisit> findReasonForVisitEntries(int firstResult, int maxResults);


	public abstract void saveReasonForVisit(ReasonForVisit reasonForVisit);


	public abstract ReasonForVisit updateReasonForVisit(ReasonForVisit reasonForVisit);

}
