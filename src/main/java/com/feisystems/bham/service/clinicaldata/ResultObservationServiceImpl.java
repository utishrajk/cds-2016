package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.ResultObservation;
import com.feisystems.bham.domain.clinicaldata.ResultObservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResultObservationServiceImpl implements ResultObservationService {

	@Autowired
    ResultObservationRepository resultObservationRepository;

	public long countAllResultObservations() {
        return resultObservationRepository.count();
    }

	public void deleteResultObservation(ResultObservation resultObservation) {
        resultObservationRepository.delete(resultObservation);
    }

	public ResultObservation findResultObservation(Long id) {
        return resultObservationRepository.findOne(id);
    }

	public List<ResultObservation> findAllResultObservations() {
        return resultObservationRepository.findAll();
    }

	public List<ResultObservation> findResultObservationEntries(int firstResult, int maxResults) {
        return resultObservationRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveResultObservation(ResultObservation resultObservation) {
        resultObservationRepository.save(resultObservation);
    }

	public ResultObservation updateResultObservation(ResultObservation resultObservation) {
        return resultObservationRepository.save(resultObservation);
    }
}
