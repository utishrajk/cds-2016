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
import javax.persistence.JoinTable;
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

import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;

@Entity
public class AssessmentScaleObservation implements Serializable  {

    /**
     */
    @Embedded
    private CodedConceptValueObject assessmentScaleObservationCode;

    /**
     */
    private String derivationExpr;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date assessmentScaleObservationDateTime;

    /**
     */
    @ManyToOne
    private ActStatusCode assessmentScaleObservationStatusCode;

    /**
     */
    @Embedded
    private QuantityType assessmentScaleObservationValue;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_scale_assessment_scale_supporting_observations")
    private Set<AssessmentScaleSupportingObservation> assessmentScaleSupportingObservations = new HashSet<AssessmentScaleSupportingObservation>();

	public CodedConceptValueObject getAssessmentScaleObservationCode() {
        return this.assessmentScaleObservationCode;
    }

	public void setAssessmentScaleObservationCode(CodedConceptValueObject assessmentScaleObservationCode) {
        this.assessmentScaleObservationCode = assessmentScaleObservationCode;
    }

	public String getDerivationExpr() {
        return this.derivationExpr;
    }

	public void setDerivationExpr(String derivationExpr) {
        this.derivationExpr = derivationExpr;
    }

	public Date getAssessmentScaleObservationDateTime() {
        return this.assessmentScaleObservationDateTime;
    }

	public void setAssessmentScaleObservationDateTime(Date assessmentScaleObservationDateTime) {
        this.assessmentScaleObservationDateTime = assessmentScaleObservationDateTime;
    }

	public ActStatusCode getAssessmentScaleObservationStatusCode() {
        return this.assessmentScaleObservationStatusCode;
    }

	public void setAssessmentScaleObservationStatusCode(ActStatusCode assessmentScaleObservationStatusCode) {
        this.assessmentScaleObservationStatusCode = assessmentScaleObservationStatusCode;
    }

	public QuantityType getAssessmentScaleObservationValue() {
        return this.assessmentScaleObservationValue;
    }

	public void setAssessmentScaleObservationValue(QuantityType assessmentScaleObservationValue) {
        this.assessmentScaleObservationValue = assessmentScaleObservationValue;
    }

	public Set<AssessmentScaleSupportingObservation> getAssessmentScaleSupportingObservations() {
        return this.assessmentScaleSupportingObservations;
    }

	public void setAssessmentScaleSupportingObservations(Set<AssessmentScaleSupportingObservation> assessmentScaleSupportingObservations) {
        this.assessmentScaleSupportingObservations = assessmentScaleSupportingObservations;
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

	public boolean equals(Object obj) {
        if (!(obj instanceof AssessmentScaleObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AssessmentScaleObservation rhs = (AssessmentScaleObservation) obj;
        return new EqualsBuilder().append(assessmentScaleObservationCode, rhs.assessmentScaleObservationCode).append(assessmentScaleObservationDateTime, rhs.assessmentScaleObservationDateTime).append(assessmentScaleObservationStatusCode, rhs.assessmentScaleObservationStatusCode).append(assessmentScaleObservationValue, rhs.assessmentScaleObservationValue).append(derivationExpr, rhs.derivationExpr).append(id, rhs.id).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(assessmentScaleObservationCode).append(assessmentScaleObservationDateTime).append(assessmentScaleObservationStatusCode).append(assessmentScaleObservationValue).append(derivationExpr).append(id).toHashCode();
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
