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

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.AdverseEventTypeCode;
import com.feisystems.bham.domain.reference.AllergyReactionCode;
import com.feisystems.bham.domain.reference.AllergySeverityCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;

@Entity
public class Allergy implements Serializable {

    /**
     */
    @ManyToOne
    private AdverseEventTypeCode adverseEventTypeCode;

    /**
     */
    @Embedded
    private CodedConceptValueObject allergen;

    /**
     */
    @ManyToOne
    private AllergyReactionCode allergyReaction;

    /**
     */
    @ManyToOne
    private ActStatusCode allergyStatusCode;

    /**
     */
    @ManyToOne
    private AllergySeverityCode allergySeverityCode;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date allergyStartDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date allergyEndDate;

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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof Allergy)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Allergy rhs = (Allergy) obj;
        return new EqualsBuilder().append(adverseEventTypeCode, rhs.adverseEventTypeCode).append(allergen, rhs.allergen).append(allergyEndDate, rhs.allergyEndDate).append(allergyReaction, rhs.allergyReaction).append(allergySeverityCode, rhs.allergySeverityCode).append(allergyStartDate, rhs.allergyStartDate).append(allergyStatusCode, rhs.allergyStatusCode).append(id, rhs.id).append(patient, rhs.patient).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(adverseEventTypeCode).append(allergen).append(allergyEndDate).append(allergyReaction).append(allergySeverityCode).append(allergyStartDate).append(allergyStatusCode).append(id).append(patient).toHashCode();
    }

	public AdverseEventTypeCode getAdverseEventTypeCode() {
        return this.adverseEventTypeCode;
    }

	public void setAdverseEventTypeCode(AdverseEventTypeCode adverseEventTypeCode) {
        this.adverseEventTypeCode = adverseEventTypeCode;
    }

	public CodedConceptValueObject getAllergen() {
        return this.allergen;
    }

	public void setAllergen(CodedConceptValueObject allergen) {
        this.allergen = allergen;
    }

	public AllergyReactionCode getAllergyReaction() {
        return this.allergyReaction;
    }

	public void setAllergyReaction(AllergyReactionCode allergyReaction) {
        this.allergyReaction = allergyReaction;
    }

	public ActStatusCode getAllergyStatusCode() {
        return this.allergyStatusCode;
    }

	public void setAllergyStatusCode(ActStatusCode allergyStatusCode) {
        this.allergyStatusCode = allergyStatusCode;
    }

	public AllergySeverityCode getAllergySeverityCode() {
        return this.allergySeverityCode;
    }

	public void setAllergySeverityCode(AllergySeverityCode allergySeverityCode) {
        this.allergySeverityCode = allergySeverityCode;
    }

	public Date getAllergyStartDate() {
        return this.allergyStartDate;
    }

	public void setAllergyStartDate(Date allergyStartDate) {
        this.allergyStartDate = allergyStartDate;
    }

	public Date getAllergyEndDate() {
        return this.allergyEndDate;
    }

	public void setAllergyEndDate(Date allergyEndDate) {
        this.allergyEndDate = allergyEndDate;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }

	private static final long serialVersionUID = 1L;
}
