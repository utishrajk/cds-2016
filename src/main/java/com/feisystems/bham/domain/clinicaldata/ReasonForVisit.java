package com.feisystems.bham.domain.clinicaldata;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;

@Entity
public class ReasonForVisit implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar reasonForVisitDateTime;

    /**
     */
    @Lob
    private String reasonForVisitText;

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

	public Calendar getReasonForVisitDateTime() {
        return this.reasonForVisitDateTime;
    }

	public void setReasonForVisitDateTime(Calendar reasonForVisitDateTime) {
        this.reasonForVisitDateTime = reasonForVisitDateTime;
    }

	public String getReasonForVisitText() {
        return this.reasonForVisitText;
    }

	public void setReasonForVisitText(String reasonForVisitText) {
        this.reasonForVisitText = reasonForVisitText;
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
        if (!(obj instanceof ReasonForVisit)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ReasonForVisit rhs = (ReasonForVisit) obj;
        return new EqualsBuilder().append(id, rhs.id).append(patient, rhs.patient).append(reasonForVisitDateTime, rhs.reasonForVisitDateTime).append(reasonForVisitText, rhs.reasonForVisitText).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(patient).append(reasonForVisitDateTime).append(reasonForVisitText).toHashCode();
    }
}
