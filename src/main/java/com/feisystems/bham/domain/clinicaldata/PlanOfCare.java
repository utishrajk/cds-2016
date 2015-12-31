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
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;

@Entity
public class PlanOfCare implements Serializable {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date planOfCareDateTime;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Problem> patientProblems = new HashSet<Problem>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PatientGoal> patientGoals = new HashSet<PatientGoal>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ProcedureObservation> interventions = new HashSet<ProcedureObservation>();

    /**
     */
    @ManyToOne
    private Patient patient;

	private static final long serialVersionUID = 1L;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof PlanOfCare)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlanOfCare rhs = (PlanOfCare) obj;
        return new EqualsBuilder().append(id, rhs.id).append(patient, rhs.patient).append(planOfCareDateTime, rhs.planOfCareDateTime).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(patient).append(planOfCareDateTime).toHashCode();
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

	public Date getPlanOfCareDateTime() {
        return this.planOfCareDateTime;
    }

	public void setPlanOfCareDateTime(Date planOfCareDateTime) {
        this.planOfCareDateTime = planOfCareDateTime;
    }

	public Set<Problem> getPatientProblems() {
        return this.patientProblems;
    }

	public void setPatientProblems(Set<Problem> patientProblems) {
        this.patientProblems = patientProblems;
    }

	public Set<PatientGoal> getPatientGoals() {
        return this.patientGoals;
    }

	public void setPatientGoals(Set<PatientGoal> patientGoals) {
        this.patientGoals = patientGoals;
    }

	public Set<ProcedureObservation> getInterventions() {
        return this.interventions;
    }

	public void setInterventions(Set<ProcedureObservation> interventions) {
        this.interventions = interventions;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
