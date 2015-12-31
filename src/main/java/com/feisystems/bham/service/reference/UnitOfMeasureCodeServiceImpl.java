package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.reference.UnitOfMeasureCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitOfMeasureCodeServiceImpl implements UnitOfMeasureCodeService {

	@Autowired
    UnitOfMeasureCodeRepository unitOfMeasureCodeRepository;

	public long countAllUnitOfMeasureCodes() {
        return unitOfMeasureCodeRepository.count();
    }

	public void deleteUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
        unitOfMeasureCodeRepository.delete(unitOfMeasureCode);
    }

	public UnitOfMeasureCode findUnitOfMeasureCode(Long id) {
        return unitOfMeasureCodeRepository.findOne(id);
    }

	public List<UnitOfMeasureCode> findAllUnitOfMeasureCodes() {
        return unitOfMeasureCodeRepository.findAll();
    }

	public List<UnitOfMeasureCode> findUnitOfMeasureCodeEntries(int firstResult, int maxResults) {
        return unitOfMeasureCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
        unitOfMeasureCodeRepository.save(unitOfMeasureCode);
    }

	public UnitOfMeasureCode updateUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
        return unitOfMeasureCodeRepository.save(unitOfMeasureCode);
    }
}
