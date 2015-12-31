package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.Allergy;
import com.feisystems.bham.domain.clinicaldata.AllergyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyServiceImpl implements AllergyService {

	@Autowired
    AllergyRepository allergyRepository;

	public long countAllAllergys() {
        return allergyRepository.count();
    }

	public void deleteAllergy(Allergy allergy) {
        allergyRepository.delete(allergy);
    }

	public Allergy findAllergy(Long id) {
        return allergyRepository.findOne(id);
    }

	public List<Allergy> findAllAllergys() {
        return allergyRepository.findAll();
    }

	public List<Allergy> findAllergyEntries(int firstResult, int maxResults) {
        return allergyRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAllergy(Allergy allergy) {
        allergyRepository.save(allergy);
    }

	public Allergy updateAllergy(Allergy allergy) {
        return allergyRepository.save(allergy);
    }
}
