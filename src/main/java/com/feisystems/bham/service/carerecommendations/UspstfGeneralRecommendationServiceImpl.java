package com.feisystems.bham.service.carerecommendations;

import com.feisystems.bham.domain.carerecommendations.UspstfGeneralRecommendation;
import com.feisystems.bham.domain.carerecommendations.UspstfGeneralRecommendationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UspstfGeneralRecommendationServiceImpl implements UspstfGeneralRecommendationService {

	@Autowired
    UspstfGeneralRecommendationRepository uspstfGeneralRecommendationRepository;

	public long countAllUspstfGeneralRecommendations() {
        return uspstfGeneralRecommendationRepository.count();
    }

	public void deleteUspstfGeneralRecommendation(UspstfGeneralRecommendation uspstfGeneralRecommendation) {
        uspstfGeneralRecommendationRepository.delete(uspstfGeneralRecommendation);
    }

	public UspstfGeneralRecommendation findUspstfGeneralRecommendation(Long id) {
        return uspstfGeneralRecommendationRepository.findOne(id);
    }

	public List<UspstfGeneralRecommendation> findAllUspstfGeneralRecommendations() {
        return uspstfGeneralRecommendationRepository.findAll();
    }

	public List<UspstfGeneralRecommendation> findUspstfGeneralRecommendationEntries(int firstResult, int maxResults) {
        return uspstfGeneralRecommendationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveUspstfGeneralRecommendation(UspstfGeneralRecommendation uspstfGeneralRecommendation) {
        uspstfGeneralRecommendationRepository.save(uspstfGeneralRecommendation);
    }

	public UspstfGeneralRecommendation updateUspstfGeneralRecommendation(UspstfGeneralRecommendation uspstfGeneralRecommendation) {
        return uspstfGeneralRecommendationRepository.save(uspstfGeneralRecommendation);
    }
}
