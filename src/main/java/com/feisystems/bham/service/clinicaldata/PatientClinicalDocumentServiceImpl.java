package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.PatientClinicalDocument;
import com.feisystems.bham.domain.clinicaldata.PatientClinicalDocumentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientClinicalDocumentServiceImpl implements PatientClinicalDocumentService {

	@Autowired
    PatientClinicalDocumentRepository patientClinicalDocumentRepository;

	public long countAllPatientClinicalDocuments() {
        return patientClinicalDocumentRepository.count();
    }

	public void deletePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument) {
        patientClinicalDocumentRepository.delete(patientClinicalDocument);
    }

	public PatientClinicalDocument findPatientClinicalDocument(Long id) {
        return patientClinicalDocumentRepository.findOne(id);
    }

	public List<PatientClinicalDocument> findAllPatientClinicalDocuments() {
        return patientClinicalDocumentRepository.findAll();
    }

	public List<PatientClinicalDocument> findPatientClinicalDocumentEntries(int firstResult, int maxResults) {
        return patientClinicalDocumentRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument) {
        patientClinicalDocumentRepository.save(patientClinicalDocument);
    }

	public PatientClinicalDocument updatePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument) {
        return patientClinicalDocumentRepository.save(patientClinicalDocument);
    }
}
