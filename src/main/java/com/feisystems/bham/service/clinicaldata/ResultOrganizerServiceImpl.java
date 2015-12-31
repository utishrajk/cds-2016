package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.ResultOrganizer;
import com.feisystems.bham.domain.clinicaldata.ResultOrganizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResultOrganizerServiceImpl implements ResultOrganizerService {

	@Autowired
    ResultOrganizerRepository resultOrganizerRepository;

	public long countAllResultOrganizers() {
        return resultOrganizerRepository.count();
    }

	public void deleteResultOrganizer(ResultOrganizer resultOrganizer) {
        resultOrganizerRepository.delete(resultOrganizer);
    }

	public ResultOrganizer findResultOrganizer(Long id) {
        return resultOrganizerRepository.findOne(id);
    }

	public List<ResultOrganizer> findAllResultOrganizers() {
        return resultOrganizerRepository.findAll();
    }

	public List<ResultOrganizer> findResultOrganizerEntries(int firstResult, int maxResults) {
        return resultOrganizerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveResultOrganizer(ResultOrganizer resultOrganizer) {
        resultOrganizerRepository.save(resultOrganizer);
    }

	public ResultOrganizer updateResultOrganizer(ResultOrganizer resultOrganizer) {
        return resultOrganizerRepository.save(resultOrganizer);
    }
}
