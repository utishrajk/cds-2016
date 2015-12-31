package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.FacilityTypeCode;
import com.feisystems.bham.domain.reference.FacilityTypeCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacilityTypeCodeServiceImpl implements FacilityTypeCodeService {

	@Autowired
    FacilityTypeCodeRepository facilityTypeCodeRepository;

	public long countAllFacilityTypeCodes() {
        return facilityTypeCodeRepository.count();
    }

	public void deleteFacilityTypeCode(FacilityTypeCode facilityTypeCode) {
        facilityTypeCodeRepository.delete(facilityTypeCode);
    }

	public FacilityTypeCode findFacilityTypeCode(Long id) {
        return facilityTypeCodeRepository.findOne(id);
    }

	public List<FacilityTypeCode> findAllFacilityTypeCodes() {
        return facilityTypeCodeRepository.findAll();
    }

	public List<FacilityTypeCode> findFacilityTypeCodeEntries(int firstResult, int maxResults) {
        return facilityTypeCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveFacilityTypeCode(FacilityTypeCode facilityTypeCode) {
        facilityTypeCodeRepository.save(facilityTypeCode);
    }

	public FacilityTypeCode updateFacilityTypeCode(FacilityTypeCode facilityTypeCode) {
        return facilityTypeCodeRepository.save(facilityTypeCode);
    }
}
