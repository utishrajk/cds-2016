package com.feisystems.bham.service.carerecommendations;
import com.feisystems.bham.domain.carerecommendations.UspstfSpecificRecommendation;
import java.util.List;

public interface UspstfSpecificRecommendationService {

	public abstract long countAllUspstfSpecificRecommendations();


	public abstract void deleteUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation);


	public abstract UspstfSpecificRecommendation findUspstfSpecificRecommendation(Long id);


	public abstract List<UspstfSpecificRecommendation> findAllUspstfSpecificRecommendations();


	public abstract List<UspstfSpecificRecommendation> findUspstfSpecificRecommendationEntries(int firstResult, int maxResults);


	public abstract void saveUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation);


	public abstract UspstfSpecificRecommendation updateUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation);

}
