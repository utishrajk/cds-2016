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
public class ResultObservation implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date resultObservationDateTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject resultObservationType;

    /**
     */
    @ManyToOne
    private ActStatusCode resultObservationStatusCode;

    /**
     */
    @Embedded
    private QuantityType resultObservationValue;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ResultInterpretationCode> resultObservationInterpretationCode = new HashSet<ResultInterpretationCode>();
    
    public String toString() {
	    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     */
    private String resultReferenceRange;

	public boolean equals(Object obj) {
        if (!(obj instanceof ResultObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ResultObservation rhs = (ResultObservation) obj;
        return new EqualsBuilder().append(id, rhs.id).append(resultObservationDateTime, rhs.resultObservationDateTime).append(resultObservationStatusCode, rhs.resultObservationStatusCode).append(resultObservationType, rhs.resultObservationType).append(resultObservationValue, rhs.resultObservationValue).append(resultReferenceRange, rhs.resultReferenceRange).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(resultObservationDateTime).append(resultObservationStatusCode).append(resultObservationType).append(resultObservationValue).append(resultReferenceRange).toHashCode();
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

	public Date getResultObservationDateTime() {
        return this.resultObservationDateTime;
    }

	public void setResultObservationDateTime(Date resultObservationDateTime) {
        this.resultObservationDateTime = resultObservationDateTime;
    }

	public CodedConceptValueObject getResultObservationType() {
        return this.resultObservationType;
    }

	public void setResultObservationType(CodedConceptValueObject resultObservationType) {
        this.resultObservationType = resultObservationType;
    }

	public ActStatusCode getResultObservationStatusCode() {
        return this.resultObservationStatusCode;
    }

	public void setResultObservationStatusCode(ActStatusCode resultObservationStatusCode) {
        this.resultObservationStatusCode = resultObservationStatusCode;
    }

	public QuantityType getResultObservationValue() {
        return this.resultObservationValue;
    }

	public void setResultObservationValue(QuantityType resultObservationValue) {
        this.resultObservationValue = resultObservationValue;
    }

	public Set<ResultInterpretationCode> getResultObservationInterpretationCode() {
        return this.resultObservationInterpretationCode;
    }

	public void setResultObservationInterpretationCode(Set<ResultInterpretationCode> resultObservationInterpretationCode) {
        this.resultObservationInterpretationCode = resultObservationInterpretationCode;
    }

	public String getResultReferenceRange() {
        return this.resultReferenceRange;
    }

	public void setResultReferenceRange(String resultReferenceRange) {
        this.resultReferenceRange = resultReferenceRange;
    }
}
