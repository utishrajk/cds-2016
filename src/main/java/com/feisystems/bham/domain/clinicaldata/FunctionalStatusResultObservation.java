package com.feisystems.bham.domain.clinicaldata;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Embedded;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;

@Entity
public class FunctionalStatusResultObservation implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date functionalStatusResultObservationDateTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject functionalStatusResultObservationCode;

    /**
     */
    @ManyToOne
    private ActStatusCode functionalStatusResultObservationStatusCode;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "functional_status_result__assessment_scale_observations")
    private Set<AssessmentScaleObservation> assessmentScaleObservations = new HashSet<AssessmentScaleObservation>();

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
        if (!(obj instanceof FunctionalStatusResultObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        FunctionalStatusResultObservation rhs = (FunctionalStatusResultObservation) obj;
        return new EqualsBuilder().append(functionalStatusResultObservationCode, rhs.functionalStatusResultObservationCode).append(functionalStatusResultObservationDateTime, rhs.functionalStatusResultObservationDateTime).append(functionalStatusResultObservationStatusCode, rhs.functionalStatusResultObservationStatusCode).append(id, rhs.id).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(functionalStatusResultObservationCode).append(functionalStatusResultObservationDateTime).append(functionalStatusResultObservationStatusCode).append(id).toHashCode();
    }

	private static final long serialVersionUID = 1L;

	public Date getFunctionalStatusResultObservationDateTime() {
        return this.functionalStatusResultObservationDateTime;
    }

	public void setFunctionalStatusResultObservationDateTime(Date functionalStatusResultObservationDateTime) {
        this.functionalStatusResultObservationDateTime = functionalStatusResultObservationDateTime;
    }

	public CodedConceptValueObject getFunctionalStatusResultObservationCode() {
        return this.functionalStatusResultObservationCode;
    }

	public void setFunctionalStatusResultObservationCode(CodedConceptValueObject functionalStatusResultObservationCode) {
        this.functionalStatusResultObservationCode = functionalStatusResultObservationCode;
    }

	public ActStatusCode getFunctionalStatusResultObservationStatusCode() {
        return this.functionalStatusResultObservationStatusCode;
    }

	public void setFunctionalStatusResultObservationStatusCode(ActStatusCode functionalStatusResultObservationStatusCode) {
        this.functionalStatusResultObservationStatusCode = functionalStatusResultObservationStatusCode;
    }

	public Set<AssessmentScaleObservation> getAssessmentScaleObservations() {
        return this.assessmentScaleObservations;
    }

	public void setAssessmentScaleObservations(Set<AssessmentScaleObservation> assessmentScaleObservations) {
        this.assessmentScaleObservations = assessmentScaleObservations;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
