package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;

@Entity
public class VitalSignOrganizer implements Serializable {

    /**
     */
    @Embedded
    private CodedConceptValueObject vitalSignOrganizerCode;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date vitalSignOrganizerDateTime;

    /**
     */
    @ManyToOne
    private ActStatusCode vitalSignOrganizerStatusCode;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<VitalSignObservation> vitalSignObservations = new HashSet<VitalSignObservation>();

    /**
     */
    @ManyToOne
    private Patient patient;

	private static final long serialVersionUID = 1L;

	public CodedConceptValueObject getVitalSignOrganizerCode() {
        return this.vitalSignOrganizerCode;
    }

	public void setVitalSignOrganizerCode(CodedConceptValueObject vitalSignOrganizerCode) {
        this.vitalSignOrganizerCode = vitalSignOrganizerCode;
    }

	public Date getVitalSignOrganizerDateTime() {
        return this.vitalSignOrganizerDateTime;
    }

	public void setVitalSignOrganizerDateTime(Date vitalSignOrganizerDateTime) {
        this.vitalSignOrganizerDateTime = vitalSignOrganizerDateTime;
    }

	public ActStatusCode getVitalSignOrganizerStatusCode() {
        return this.vitalSignOrganizerStatusCode;
    }

	public void setVitalSignOrganizerStatusCode(ActStatusCode vitalSignOrganizerStatusCode) {
        this.vitalSignOrganizerStatusCode = vitalSignOrganizerStatusCode;
    }

	public Set<VitalSignObservation> getVitalSignObservations() {
        return this.vitalSignObservations;
    }

	public void setVitalSignObservations(Set<VitalSignObservation> vitalSignObservations) {
        this.vitalSignObservations = vitalSignObservations;
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

	public boolean equals(Object obj) {
        if (!(obj instanceof VitalSignOrganizer)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VitalSignOrganizer rhs = (VitalSignOrganizer) obj;
        return new EqualsBuilder().append(id, rhs.id).append(patient, rhs.patient).append(vitalSignOrganizerCode, rhs.vitalSignOrganizerCode).append(vitalSignOrganizerDateTime, rhs.vitalSignOrganizerDateTime).append(vitalSignOrganizerStatusCode, rhs.vitalSignOrganizerStatusCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(patient).append(vitalSignOrganizerCode).append(vitalSignOrganizerDateTime).append(vitalSignOrganizerStatusCode).toHashCode();
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
}
