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
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.domain.reference.ResultInterpretationCode;

@Entity
public class VitalSignObservation implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date vitalSignObservationDateTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject vitalSignObservationType;

    /**
     */
    @ManyToOne
    private ActStatusCode vitalSignObservationStatusCode;

    /**
     */
    @Embedded
    private QuantityType vitalSignObservationValue;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vital_sign_observation_observation_interpretation_code")
    private Set<ResultInterpretationCode> vitalSignObservationInterpretationCode = new HashSet<ResultInterpretationCode>();

    /**
     */
    private String resultReferenceRange;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof VitalSignObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VitalSignObservation rhs = (VitalSignObservation) obj;
        return new EqualsBuilder().append(id, rhs.id).append(resultReferenceRange, rhs.resultReferenceRange).append(vitalSignObservationDateTime, rhs.vitalSignObservationDateTime).append(vitalSignObservationStatusCode, rhs.vitalSignObservationStatusCode).append(vitalSignObservationType, rhs.vitalSignObservationType).append(vitalSignObservationValue, rhs.vitalSignObservationValue).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(resultReferenceRange).append(vitalSignObservationDateTime).append(vitalSignObservationStatusCode).append(vitalSignObservationType).append(vitalSignObservationValue).toHashCode();
    }

	private static final long serialVersionUID = 1L;

	public Date getVitalSignObservationDateTime() {
        return this.vitalSignObservationDateTime;
    }

	public void setVitalSignObservationDateTime(Date vitalSignObservationDateTime) {
        this.vitalSignObservationDateTime = vitalSignObservationDateTime;
    }

	public CodedConceptValueObject getVitalSignObservationType() {
        return this.vitalSignObservationType;
    }

	public void setVitalSignObservationType(CodedConceptValueObject vitalSignObservationType) {
        this.vitalSignObservationType = vitalSignObservationType;
    }

	public ActStatusCode getVitalSignObservationStatusCode() {
        return this.vitalSignObservationStatusCode;
    }

	public void setVitalSignObservationStatusCode(ActStatusCode vitalSignObservationStatusCode) {
        this.vitalSignObservationStatusCode = vitalSignObservationStatusCode;
    }

	public QuantityType getVitalSignObservationValue() {
        return this.vitalSignObservationValue;
    }

	public void setVitalSignObservationValue(QuantityType vitalSignObservationValue) {
        this.vitalSignObservationValue = vitalSignObservationValue;
    }

	public Set<ResultInterpretationCode> getVitalSignObservationInterpretationCode() {
        return this.vitalSignObservationInterpretationCode;
    }

	public void setVitalSignObservationInterpretationCode(Set<ResultInterpretationCode> vitalSignObservationInterpretationCode) {
        this.vitalSignObservationInterpretationCode = vitalSignObservationInterpretationCode;
    }

	public String getResultReferenceRange() {
        return this.resultReferenceRange;
    }

	public void setResultReferenceRange(String resultReferenceRange) {
        this.resultReferenceRange = resultReferenceRange;
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
