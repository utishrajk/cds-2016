package com.feisystems.bham.service.carerecommendations;

import com.feisystems.bham.domain.carerecommendations.UspstfSpecificRecommendation;
import com.feisystems.bham.domain.carerecommendations.UspstfSpecificRecommendationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UspstfSpecificRecommendationServiceImpl implements UspstfSpecificRecommendationService {

	@Autowired
    UspstfSpecificRecommendationRepository uspstfSpecificRecommendationRepository;

	public long countAllUspstfSpecificRecommendations() {
        return uspstfSpecificRecommendationRepository.count();
    }

	public void deleteUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation) {
        uspstfSpecificRecommendationRepository.delete(uspstfSpecificRecommendation);
    }

	public UspstfSpecificRecommendation findUspstfSpecificRecommendation(Long id) {
        return uspstfSpecificRecommendationRepository.findOne(id);
    }

	public List<UspstfSpecificRecommendation> findAllUspstfSpecificRecommendations() {
        return uspstfSpecificRecommendationRepository.findAll();
    }

	public List<UspstfSpecificRecommendation> findUspstfSpecificRecommendationEntries(int firstResult, int maxResults) {
        return uspstfSpecificRecommendationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation) {
        uspstfSpecificRecommendationRepository.save(uspstfSpecificRecommendation);
    }

	public UspstfSpecificRecommendation updateUspstfSpecificRecommendation(UspstfSpecificRecommendation uspstfSpecificRecommendation) {
        return uspstfSpecificRecommendationRepository.save(uspstfSpecificRecommendation);
    }
}
