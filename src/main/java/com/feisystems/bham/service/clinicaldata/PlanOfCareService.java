package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.PlanOfCare;
import java.util.List;

public interface PlanOfCareService {

	public abstract long countAllPlanOfCares();


	public abstract void deletePlanOfCare(PlanOfCare planOfCare);


	public abstract PlanOfCare findPlanOfCare(Long id);


	public abstract List<PlanOfCare> findAllPlanOfCares();


	public abstract List<PlanOfCare> findPlanOfCareEntries(int firstResult, int maxResults);


	public abstract void savePlanOfCare(PlanOfCare planOfCare);


	public abstract PlanOfCare updatePlanOfCare(PlanOfCare planOfCare);

}
