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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;

@Entity
public class PatientGoal implements Serializable {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date goalAchievementTargetTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject goalFocus;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date goalPursuitEffectiveTime;

    /**
     */
    @Embedded
    private QuantityType targetGoalValue;

    /**
     */
    @ManyToOne
    private BodySiteCode bodySiteCode;

    /**
     */
    @ManyToOne
    private Patient patient;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof PatientGoal)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PatientGoal rhs = (PatientGoal) obj;
        return new EqualsBuilder().append(bodySiteCode, rhs.bodySiteCode).append(goalAchievementTargetTime, rhs.goalAchievementTargetTime).append(goalFocus, rhs.goalFocus).append(goalPursuitEffectiveTime, rhs.goalPursuitEffectiveTime).append(id, rhs.id).append(patient, rhs.patient).append(targetGoalValue, rhs.targetGoalValue).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(bodySiteCode).append(goalAchievementTargetTime).append(goalFocus).append(goalPursuitEffectiveTime).append(id).append(patient).append(targetGoalValue).toHashCode();
    }

	public Date getGoalAchievementTargetTime() {
        return this.goalAchievementTargetTime;
    }

	public void setGoalAchievementTargetTime(Date goalAchievementTargetTime) {
        this.goalAchievementTargetTime = goalAchievementTargetTime;
    }

	public CodedConceptValueObject getGoalFocus() {
        return this.goalFocus;
    }

	public void setGoalFocus(CodedConceptValueObject goalFocus) {
        this.goalFocus = goalFocus;
    }

	public Date getGoalPursuitEffectiveTime() {
        return this.goalPursuitEffectiveTime;
    }

	public void setGoalPursuitEffectiveTime(Date goalPursuitEffectiveTime) {
        this.goalPursuitEffectiveTime = goalPursuitEffectiveTime;
    }

	public QuantityType getTargetGoalValue() {
        return this.targetGoalValue;
    }

	public void setTargetGoalValue(QuantityType targetGoalValue) {
        this.targetGoalValue = targetGoalValue;
    }

	public BodySiteCode getBodySiteCode() {
        return this.bodySiteCode;
    }

	public void setBodySiteCode(BodySiteCode bodySiteCode) {
        this.bodySiteCode = bodySiteCode;
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

	private static final long serialVersionUID = 1L;

}
