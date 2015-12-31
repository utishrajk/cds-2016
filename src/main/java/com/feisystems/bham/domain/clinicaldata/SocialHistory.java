package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
import com.feisystems.bham.domain.reference.SocialHistoryTypeCode;

@Entity
public class SocialHistory implements Serializable {

    /**
     */
    private String socialHistoryFreeText;

    /**
     */
    @ManyToOne
    private SocialHistoryTypeCode socialHistoryTypeCode;

    /**
     */
    @ManyToOne
    private ActStatusCode socialHistoryStatusCode;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date socialHistoryStartDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date SocialHistoryEndDate;

    /**
     */
    @ManyToOne
    private Patient patient;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof SocialHistory)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        SocialHistory rhs = (SocialHistory) obj;
        return new EqualsBuilder().append(SocialHistoryEndDate, rhs.SocialHistoryEndDate).append(id, rhs.id).append(patient, rhs.patient).append(socialHistoryFreeText, rhs.socialHistoryFreeText).append(socialHistoryStartDate, rhs.socialHistoryStartDate).append(socialHistoryStatusCode, rhs.socialHistoryStatusCode).append(socialHistoryTypeCode, rhs.socialHistoryTypeCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(SocialHistoryEndDate).append(id).append(patient).append(socialHistoryFreeText).append(socialHistoryStartDate).append(socialHistoryStatusCode).append(socialHistoryTypeCode).toHashCode();
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

	private static final long serialVersionUID = 1L;

	public String getSocialHistoryFreeText() {
        return this.socialHistoryFreeText;
    }

	public void setSocialHistoryFreeText(String socialHistoryFreeText) {
        this.socialHistoryFreeText = socialHistoryFreeText;
    }

	public SocialHistoryTypeCode getSocialHistoryTypeCode() {
        return this.socialHistoryTypeCode;
    }

	public void setSocialHistoryTypeCode(SocialHistoryTypeCode socialHistoryTypeCode) {
        this.socialHistoryTypeCode = socialHistoryTypeCode;
    }

	public ActStatusCode getSocialHistoryStatusCode() {
        return this.socialHistoryStatusCode;
    }

	public void setSocialHistoryStatusCode(ActStatusCode socialHistoryStatusCode) {
        this.socialHistoryStatusCode = socialHistoryStatusCode;
    }

	public Date getSocialHistoryStartDate() {
        return this.socialHistoryStartDate;
    }

	public void setSocialHistoryStartDate(Date socialHistoryStartDate) {
        this.socialHistoryStartDate = socialHistoryStartDate;
    }

	public Date getSocialHistoryEndDate() {
        return this.SocialHistoryEndDate;
    }

	public void setSocialHistoryEndDate(Date SocialHistoryEndDate) {
        this.SocialHistoryEndDate = SocialHistoryEndDate;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
