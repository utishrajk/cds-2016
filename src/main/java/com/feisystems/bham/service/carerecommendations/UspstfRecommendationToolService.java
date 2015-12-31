package com.feisystems.bham.service.carerecommendations;
import com.feisystems.bham.domain.carerecommendations.UspstfRecommendationTool;
import java.util.List;

public interface UspstfRecommendationToolService {

	public abstract long countAllUspstfRecommendationTools();


	public abstract void deleteUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool);


	public abstract UspstfRecommendationTool findUspstfRecommendationTool(Long id);


	public abstract List<UspstfRecommendationTool> findAllUspstfRecommendationTools();


	public abstract List<UspstfRecommendationTool> findUspstfRecommendationToolEntries(int firstResult, int maxResults);


	public abstract void saveUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool);


	public abstract UspstfRecommendationTool updateUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool);

}
