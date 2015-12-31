package com.feisystems.bham.domain.clinicaldata;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;


import com.feisystems.bham.domain.patient.Patient;

@Entity
public class ResultOrganizer implements Serializable {

    /**
     */
    @Embedded
    private CodedConceptValueObject resultOrganizerCode;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date resultOrganizerDateTime;

    /**
     */
    @ManyToOne
    private ActStatusCode resultOrganizerStatusCode;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ResultObservation> resultObservations = new HashSet<ResultObservation>();

    /**
     */
    @ManyToOne
    private Patient patient;

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

	public boolean equals(Object obj) {
        if (!(obj instanceof ResultOrganizer)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ResultOrganizer rhs = (ResultOrganizer) obj;
        return new EqualsBuilder().append(id, rhs.id).append(patient, rhs.patient).append(resultOrganizerCode, rhs.resultOrganizerCode).append(resultOrganizerDateTime, rhs.resultOrganizerDateTime).append(resultOrganizerStatusCode, rhs.resultOrganizerStatusCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(patient).append(resultOrganizerCode).append(resultOrganizerDateTime).append(resultOrganizerStatusCode).toHashCode();
    }

	public CodedConceptValueObject getResultOrganizerCode() {
        return this.resultOrganizerCode;
    }

	public void setResultOrganizerCode(CodedConceptValueObject resultOrganizerCode) {
        this.resultOrganizerCode = resultOrganizerCode;
    }

	public Date getResultOrganizerDateTime() {
        return this.resultOrganizerDateTime;
    }

	public void setResultOrganizerDateTime(Date resultOrganizerDateTime) {
        this.resultOrganizerDateTime = resultOrganizerDateTime;
    }

	public ActStatusCode getResultOrganizerStatusCode() {
        return this.resultOrganizerStatusCode;
    }

	public void setResultOrganizerStatusCode(ActStatusCode resultOrganizerStatusCode) {
        this.resultOrganizerStatusCode = resultOrganizerStatusCode;
    }

	public Set<ResultObservation> getResultObservations() {
        return this.resultObservations;
    }

	public void setResultObservations(Set<ResultObservation> resultObservations) {
        this.resultObservations = resultObservations;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
