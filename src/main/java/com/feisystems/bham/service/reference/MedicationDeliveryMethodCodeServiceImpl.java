package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCode;
import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationDeliveryMethodCodeServiceImpl implements MedicationDeliveryMethodCodeService {

	@Autowired
    MedicationDeliveryMethodCodeRepository medicationDeliveryMethodCodeRepository;

	public long countAllMedicationDeliveryMethodCodes() {
        return medicationDeliveryMethodCodeRepository.count();
    }

	public void deleteMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode) {
        medicationDeliveryMethodCodeRepository.delete(medicationDeliveryMethodCode);
    }

	public MedicationDeliveryMethodCode findMedicationDeliveryMethodCode(Long id) {
        return medicationDeliveryMethodCodeRepository.findOne(id);
    }

	public List<MedicationDeliveryMethodCode> findAllMedicationDeliveryMethodCodes() {
        return medicationDeliveryMethodCodeRepository.findAll();
    }

	public List<MedicationDeliveryMethodCode> findMedicationDeliveryMethodCodeEntries(int firstResult, int maxResults) {
        return medicationDeliveryMethodCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode) {
        medicationDeliveryMethodCodeRepository.save(medicationDeliveryMethodCode);
    }

	public MedicationDeliveryMethodCode updateMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode) {
        return medicationDeliveryMethodCodeRepository.save(medicationDeliveryMethodCode);
    }
}
