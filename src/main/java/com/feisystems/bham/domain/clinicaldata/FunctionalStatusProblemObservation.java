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
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.patient.Patient;

@Entity
public class FunctionalStatusProblemObservation implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date functionalStatusProblemObservationDateTime;

    /**
     */
    @ManyToOne
    private ActStatusCode functionalStatusProblemObservationStatusCode;

    /**
     */
    @Embedded
    private CodedConceptValueObject functionalStatusProblemObservationCode;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "functional_status_problem_assessment_scale_observations")
    private Set<AssessmentScaleObservation> assessmentScaleObservations = new HashSet<AssessmentScaleObservation>();

    /**
     */
    @ManyToOne
    private Patient patient;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	private static final long serialVersionUID = 1L;

	public boolean equals(Object obj) {
        if (!(obj instanceof FunctionalStatusProblemObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        FunctionalStatusProblemObservation rhs = (FunctionalStatusProblemObservation) obj;
        return new EqualsBuilder().append(functionalStatusProblemObservationCode, rhs.functionalStatusProblemObservationCode).append(functionalStatusProblemObservationDateTime, rhs.functionalStatusProblemObservationDateTime).append(functionalStatusProblemObservationStatusCode, rhs.functionalStatusProblemObservationStatusCode).append(id, rhs.id).append(patient, rhs.patient).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(functionalStatusProblemObservationCode).append(functionalStatusProblemObservationDateTime).append(functionalStatusProblemObservationStatusCode).append(id).append(patient).toHashCode();
    }

	public Date getFunctionalStatusProblemObservationDateTime() {
        return this.functionalStatusProblemObservationDateTime;
    }

	public void setFunctionalStatusProblemObservationDateTime(Date functionalStatusProblemObservationDateTime) {
        this.functionalStatusProblemObservationDateTime = functionalStatusProblemObservationDateTime;
    }

	public ActStatusCode getFunctionalStatusProblemObservationStatusCode() {
        return this.functionalStatusProblemObservationStatusCode;
    }

	public void setFunctionalStatusProblemObservationStatusCode(ActStatusCode functionalStatusProblemObservationStatusCode) {
        this.functionalStatusProblemObservationStatusCode = functionalStatusProblemObservationStatusCode;
    }

	public CodedConceptValueObject getFunctionalStatusProblemObservationCode() {
        return this.functionalStatusProblemObservationCode;
    }

	public void setFunctionalStatusProblemObservationCode(CodedConceptValueObject functionalStatusProblemObservationCode) {
        this.functionalStatusProblemObservationCode = functionalStatusProblemObservationCode;
    }

	public Set<AssessmentScaleObservation> getAssessmentScaleObservations() {
        return this.assessmentScaleObservations;
    }

	public void setAssessmentScaleObservations(Set<AssessmentScaleObservation> assessmentScaleObservations) {
        this.assessmentScaleObservations = assessmentScaleObservations;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
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
