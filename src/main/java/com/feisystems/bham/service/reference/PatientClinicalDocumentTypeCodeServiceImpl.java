package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCode;
import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCodeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientClinicalDocumentTypeCodeServiceImpl implements PatientClinicalDocumentTypeCodeService {
	
	@Autowired
	PatientClinicalDocumentTypeCodeRepository patientClinicalDocumentTypeCodeRepository;


	public long countAllPatientClinicalDocumentTypeCodes() {
		return patientClinicalDocumentTypeCodeRepository.count();
		}

	public void deletePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode) {
        patientClinicalDocumentTypeCodeRepository.delete(patientClinicalDocumentTypeCode);
    }

	public PatientClinicalDocumentTypeCode findPatientClinicalDocumentTypeCode(Long id) {
        return patientClinicalDocumentTypeCodeRepository.findOne(id);
    }

	public List<PatientClinicalDocumentTypeCode> findAllPatientClinicalDocumentTypeCodes() {
        return patientClinicalDocumentTypeCodeRepository.findAll();
    }

	public List<PatientClinicalDocumentTypeCode> findPatientClinicalDocumentTypeCodeEntries(int firstResult, int maxResults) {
        return patientClinicalDocumentTypeCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode) {
        patientClinicalDocumentTypeCodeRepository.save(patientClinicalDocumentTypeCode);
    }

	public PatientClinicalDocumentTypeCode updatePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode) {
        return patientClinicalDocumentTypeCodeRepository.save(patientClinicalDocumentTypeCode);
    }
}
