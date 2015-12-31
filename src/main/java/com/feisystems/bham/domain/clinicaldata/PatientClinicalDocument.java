package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCode;

@Entity
public class PatientClinicalDocument implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 30)
    private String name;

    /**
     */
    @ManyToOne
    private PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode;

    /**
     */
    @Size(max = 500)
    private String description;

    /**
     * TODO: Change this to byte[]. Roo does not support byte[]
     */
    @Lob
    private String content;

    /**
     */
    @NotNull
    private String contentType;

    /**
     */
    @NotNull
    private String contentStandardName;

    /**
     */
    @NotNull
    private String contentStandardVersion;

    /**
     */
    @NotNull
    private Long documentSize;

    /**
     */
    private String DocumentProvenance;

    /**
     */
    @Size(max = 100)
    private String documentUrl;

    /**
     */
    @ManyToOne
    private Patient patient;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof PatientClinicalDocument)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PatientClinicalDocument rhs = (PatientClinicalDocument) obj;
        return new EqualsBuilder().append(DocumentProvenance, rhs.DocumentProvenance).append(content, rhs.content).append(contentStandardName, rhs.contentStandardName).append(contentStandardVersion, rhs.contentStandardVersion).append(contentType, rhs.contentType).append(description, rhs.description).append(documentSize, rhs.documentSize).append(documentUrl, rhs.documentUrl).append(id, rhs.id).append(name, rhs.name).append(patient, rhs.patient).append(patientClinicalDocumentTypeCode, rhs.patientClinicalDocumentTypeCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(DocumentProvenance).append(content).append(contentStandardName).append(contentStandardVersion).append(contentType).append(description).append(documentSize).append(documentUrl).append(id).append(name).append(patient).append(patientClinicalDocumentTypeCode).toHashCode();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	private static final long serialVersionUID = 1L;

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public PatientClinicalDocumentTypeCode getPatientClinicalDocumentTypeCode() {
        return this.patientClinicalDocumentTypeCode;
    }

	public void setPatientClinicalDocumentTypeCode(PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode) {
        this.patientClinicalDocumentTypeCode = patientClinicalDocumentTypeCode;
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public String getContent() {
        return this.content;
    }

	public void setContent(String content) {
        this.content = content;
    }

	public String getContentType() {
        return this.contentType;
    }

	public void setContentType(String contentType) {
        this.contentType = contentType;
    }

	public String getContentStandardName() {
        return this.contentStandardName;
    }

	public void setContentStandardName(String contentStandardName) {
        this.contentStandardName = contentStandardName;
    }

	public String getContentStandardVersion() {
        return this.contentStandardVersion;
    }

	public void setContentStandardVersion(String contentStandardVersion) {
        this.contentStandardVersion = contentStandardVersion;
    }

	public Long getDocumentSize() {
        return this.documentSize;
    }

	public void setDocumentSize(Long documentSize) {
        this.documentSize = documentSize;
    }

	public String getDocumentProvenance() {
        return this.DocumentProvenance;
    }

	public void setDocumentProvenance(String DocumentProvenance) {
        this.DocumentProvenance = DocumentProvenance;
    }

	public String getDocumentUrl() {
        return this.documentUrl;
    }

	public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
