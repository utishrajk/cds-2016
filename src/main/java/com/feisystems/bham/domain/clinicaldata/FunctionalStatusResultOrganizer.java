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
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.patient.Patient;

@Entity
public class FunctionalStatusResultOrganizer implements Serializable {

    /**
     */
    @Embedded
    private CodedConceptValueObject functionalStatusResultOrganizerCode;

    /**
     */
    @ManyToOne
    private ActStatusCode functionalStatusResultOrganizerStatusCode;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date functionalStatusResultOrganizerDateTime;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "functional_status_result_organizer_observations")
    private Set<FunctionalStatusResultObservation> functionalStatusResultObservations = new HashSet<FunctionalStatusResultObservation>();

    /**
     */
    @ManyToOne
    private Patient patient;

	private static final long serialVersionUID = 1L;

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

	public boolean equals(Object obj) {
        if (!(obj instanceof FunctionalStatusResultOrganizer)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        FunctionalStatusResultOrganizer rhs = (FunctionalStatusResultOrganizer) obj;
        return new EqualsBuilder().append(functionalStatusResultOrganizerCode, rhs.functionalStatusResultOrganizerCode).append(functionalStatusResultOrganizerDateTime, rhs.functionalStatusResultOrganizerDateTime).append(functionalStatusResultOrganizerStatusCode, rhs.functionalStatusResultOrganizerStatusCode).append(id, rhs.id).append(patient, rhs.patient).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(functionalStatusResultOrganizerCode).append(functionalStatusResultOrganizerDateTime).append(functionalStatusResultOrganizerStatusCode).append(id).append(patient).toHashCode();
    }

	public CodedConceptValueObject getFunctionalStatusResultOrganizerCode() {
        return this.functionalStatusResultOrganizerCode;
    }

	public void setFunctionalStatusResultOrganizerCode(CodedConceptValueObject functionalStatusResultOrganizerCode) {
        this.functionalStatusResultOrganizerCode = functionalStatusResultOrganizerCode;
    }

	public ActStatusCode getFunctionalStatusResultOrganizerStatusCode() {
        return this.functionalStatusResultOrganizerStatusCode;
    }

	public void setFunctionalStatusResultOrganizerStatusCode(ActStatusCode functionalStatusResultOrganizerStatusCode) {
        this.functionalStatusResultOrganizerStatusCode = functionalStatusResultOrganizerStatusCode;
    }

	public Date getFunctionalStatusResultOrganizerDateTime() {
        return this.functionalStatusResultOrganizerDateTime;
    }

	public void setFunctionalStatusResultOrganizerDateTime(Date functionalStatusResultOrganizerDateTime) {
        this.functionalStatusResultOrganizerDateTime = functionalStatusResultOrganizerDateTime;
    }

	public Set<FunctionalStatusResultObservation> getFunctionalStatusResultObservations() {
        return this.functionalStatusResultObservations;
    }

	public void setFunctionalStatusResultObservations(Set<FunctionalStatusResultObservation> functionalStatusResultObservations) {
        this.functionalStatusResultObservations = functionalStatusResultObservations;
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
