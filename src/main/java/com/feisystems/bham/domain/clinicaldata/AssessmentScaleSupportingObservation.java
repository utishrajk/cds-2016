package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;

@Entity
public class AssessmentScaleSupportingObservation implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date assessmentScaleSupportingObservationDateTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject assessmentScaleSupportingObservationCode;

    /**
     */
    @Embedded
    private QuantityType assessmentScaleSupportingObservationValue;

    /**
     */
    @ManyToOne
    private ActStatusCode assessmentScaleSupportingObservationStatusCode;

	public boolean equals(Object obj) {
        if (!(obj instanceof AssessmentScaleSupportingObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AssessmentScaleSupportingObservation rhs = (AssessmentScaleSupportingObservation) obj;
        return new EqualsBuilder().append(assessmentScaleSupportingObservationCode, rhs.assessmentScaleSupportingObservationCode).append(assessmentScaleSupportingObservationDateTime, rhs.assessmentScaleSupportingObservationDateTime).append(assessmentScaleSupportingObservationStatusCode, rhs.assessmentScaleSupportingObservationStatusCode).append(assessmentScaleSupportingObservationValue, rhs.assessmentScaleSupportingObservationValue).append(id, rhs.id).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(assessmentScaleSupportingObservationCode).append(assessmentScaleSupportingObservationDateTime).append(assessmentScaleSupportingObservationStatusCode).append(assessmentScaleSupportingObservationValue).append(id).toHashCode();
    }

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

	public Date getAssessmentScaleSupportingObservationDateTime() {
        return this.assessmentScaleSupportingObservationDateTime;
    }

	public void setAssessmentScaleSupportingObservationDateTime(Date assessmentScaleSupportingObservationDateTime) {
        this.assessmentScaleSupportingObservationDateTime = assessmentScaleSupportingObservationDateTime;
    }

	public CodedConceptValueObject getAssessmentScaleSupportingObservationCode() {
        return this.assessmentScaleSupportingObservationCode;
    }

	public void setAssessmentScaleSupportingObservationCode(CodedConceptValueObject assessmentScaleSupportingObservationCode) {
        this.assessmentScaleSupportingObservationCode = assessmentScaleSupportingObservationCode;
    }

	public QuantityType getAssessmentScaleSupportingObservationValue() {
        return this.assessmentScaleSupportingObservationValue;
    }

	public void setAssessmentScaleSupportingObservationValue(QuantityType assessmentScaleSupportingObservationValue) {
        this.assessmentScaleSupportingObservationValue = assessmentScaleSupportingObservationValue;
    }

	public ActStatusCode getAssessmentScaleSupportingObservationStatusCode() {
        return this.assessmentScaleSupportingObservationStatusCode;
    }

	public void setAssessmentScaleSupportingObservationStatusCode(ActStatusCode assessmentScaleSupportingObservationStatusCode) {
        this.assessmentScaleSupportingObservationStatusCode = assessmentScaleSupportingObservationStatusCode;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
