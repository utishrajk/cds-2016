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
import com.feisystems.bham.domain.reference.ProblemCode;

@Entity
public class Problem implements Serializable {

    /**
     */
    private Integer ageAtOnSet;

    /**
     */
    @ManyToOne
    private ActStatusCode problemStatusCode;

    /**
     */
    @ManyToOne
    private ProblemCode problemCode;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date problemStartDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date problemEndDate;

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

	public boolean equals(Object obj) {
        if (!(obj instanceof Problem)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Problem rhs = (Problem) obj;
        return new EqualsBuilder().append(ageAtOnSet, rhs.ageAtOnSet).append(id, rhs.id).append(patient, rhs.patient).append(problemCode, rhs.problemCode).append(problemEndDate, rhs.problemEndDate).append(problemStartDate, rhs.problemStartDate).append(problemStatusCode, rhs.problemStatusCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(ageAtOnSet).append(id).append(patient).append(problemCode).append(problemEndDate).append(problemStartDate).append(problemStatusCode).toHashCode();
    }

	private static final long serialVersionUID = 1L;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Integer getAgeAtOnSet() {
        return this.ageAtOnSet;
    }

	public void setAgeAtOnSet(Integer ageAtOnSet) {
        this.ageAtOnSet = ageAtOnSet;
    }

	public ActStatusCode getProblemStatusCode() {
        return this.problemStatusCode;
    }

	public void setProblemStatusCode(ActStatusCode problemStatusCode) {
        this.problemStatusCode = problemStatusCode;
    }

	public ProblemCode getProblemCode() {
        return this.problemCode;
    }

	public void setProblemCode(ProblemCode problemCode) {
        this.problemCode = problemCode;
    }

	public Date getProblemStartDate() {
        return this.problemStartDate;
    }

	public void setProblemStartDate(Date problemStartDate) {
        this.problemStartDate = problemStartDate;
    }

	public Date getProblemEndDate() {
        return this.problemEndDate;
    }

	public void setProblemEndDate(Date problemEndDate) {
        this.problemEndDate = problemEndDate;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
