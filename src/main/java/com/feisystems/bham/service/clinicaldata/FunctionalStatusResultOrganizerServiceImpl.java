package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultOrganizer;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultOrganizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FunctionalStatusResultOrganizerServiceImpl implements FunctionalStatusResultOrganizerService {

	@Autowired
    FunctionalStatusResultOrganizerRepository functionalStatusResultOrganizerRepository;

	public long countAllFunctionalStatusResultOrganizers() {
        return functionalStatusResultOrganizerRepository.count();
    }

	public void deleteFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer) {
        functionalStatusResultOrganizerRepository.delete(functionalStatusResultOrganizer);
    }

	public FunctionalStatusResultOrganizer findFunctionalStatusResultOrganizer(Long id) {
        return functionalStatusResultOrganizerRepository.findOne(id);
    }

	public List<FunctionalStatusResultOrganizer> findAllFunctionalStatusResultOrganizers() {
        return functionalStatusResultOrganizerRepository.findAll();
    }

	public List<FunctionalStatusResultOrganizer> findFunctionalStatusResultOrganizerEntries(int firstResult, int maxResults) {
        return functionalStatusResultOrganizerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer) {
        functionalStatusResultOrganizerRepository.save(functionalStatusResultOrganizer);
    }

	public FunctionalStatusResultOrganizer updateFunctionalStatusResultOrganizer(FunctionalStatusResultOrganizer functionalStatusResultOrganizer) {
        return functionalStatusResultOrganizerRepository.save(functionalStatusResultOrganizer);
    }
}
