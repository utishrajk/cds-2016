package com.feisystems.bham.service.carerecommendations;

import com.feisystems.bham.domain.carerecommendations.UspstfRecommendationTool;
import com.feisystems.bham.domain.carerecommendations.UspstfRecommendationToolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UspstfRecommendationToolServiceImpl implements UspstfRecommendationToolService {

	@Autowired
    UspstfRecommendationToolRepository uspstfRecommendationToolRepository;

	public long countAllUspstfRecommendationTools() {
        return uspstfRecommendationToolRepository.count();
    }

	public void deleteUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool) {
        uspstfRecommendationToolRepository.delete(uspstfRecommendationTool);
    }

	public UspstfRecommendationTool findUspstfRecommendationTool(Long id) {
        return uspstfRecommendationToolRepository.findOne(id);
    }

	public List<UspstfRecommendationTool> findAllUspstfRecommendationTools() {
        return uspstfRecommendationToolRepository.findAll();
    }

	public List<UspstfRecommendationTool> findUspstfRecommendationToolEntries(int firstResult, int maxResults) {
        return uspstfRecommendationToolRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool) {
        uspstfRecommendationToolRepository.save(uspstfRecommendationTool);
    }

	public UspstfRecommendationTool updateUspstfRecommendationTool(UspstfRecommendationTool uspstfRecommendationTool) {
        return uspstfRecommendationToolRepository.save(uspstfRecommendationTool);
    }
}
