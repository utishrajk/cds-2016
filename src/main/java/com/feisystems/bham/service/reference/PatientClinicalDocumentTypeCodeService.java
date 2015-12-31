package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCode;
import java.util.List;

public interface PatientClinicalDocumentTypeCodeService {

	public abstract long countAllPatientClinicalDocumentTypeCodes();


	public abstract void deletePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode);


	public abstract PatientClinicalDocumentTypeCode findPatientClinicalDocumentTypeCode(Long id);


	public abstract List<PatientClinicalDocumentTypeCode> findAllPatientClinicalDocumentTypeCodes();


	public abstract List<PatientClinicalDocumentTypeCode> findPatientClinicalDocumentTypeCodeEntries(int firstResult, int maxResults);


	public abstract void savePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode);


	public abstract PatientClinicalDocumentTypeCode updatePatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode);

}
