package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.PatientClinicalDocument;
import java.util.List;

public interface PatientClinicalDocumentService {

	public abstract long countAllPatientClinicalDocuments();


	public abstract void deletePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument);


	public abstract PatientClinicalDocument findPatientClinicalDocument(Long id);


	public abstract List<PatientClinicalDocument> findAllPatientClinicalDocuments();


	public abstract List<PatientClinicalDocument> findPatientClinicalDocumentEntries(int firstResult, int maxResults);


	public abstract void savePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument);


	public abstract PatientClinicalDocument updatePatientClinicalDocument(PatientClinicalDocument patientClinicalDocument);

}
