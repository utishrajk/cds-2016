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
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.reference.CgiICode;
import com.feisystems.bham.domain.reference.CgiSCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCode;

@Entity
public class Outcome implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endDate;

	@Size(max = 30)
	private String symptoms;

	@Size(max = 30)
	private String tolerability;

	@ManyToOne
	private ProcedureTypeCode procedureType;

	@ManyToOne
	private CgiICode cgiICode;

	@ManyToOne
	private CgiSCode cgiSCode;

	@ManyToOne
	private Patient patient;

	public boolean equals(Object obj) {
		if (!(obj instanceof Outcome)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Outcome rhs = (Outcome) obj;
		return new EqualsBuilder().append(id, rhs.id).append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(symptoms, rhs.symptoms)
				.append(tolerability, rhs.tolerability).append(procedureType, rhs.procedureType).append(patient, rhs.patient).append(cgiICode, rhs.cgiICode)
				.append(cgiSCode, rhs.cgiSCode).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(id).append(startDate).append(endDate).append(symptoms).append(tolerability).append(procedureType).append(patient)
				.append(cgiICode).append(cgiSCode).toHashCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getTolerability() {
		return tolerability;
	}

	public void setTolerability(String tolerability) {
		this.tolerability = tolerability;
	}

	public ProcedureTypeCode getProcedureType() {
		return procedureType;
	}

	public void setProcedureType(ProcedureTypeCode procedureType) {
		this.procedureType = procedureType;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public CgiICode getCgiICode() {
		return cgiICode;
	}

	public void setCgiICode(CgiICode cgiICode) {
		this.cgiICode = cgiICode;
	}

	public CgiSCode getCgiSCode() {
		return cgiSCode;
	}

	public void setCgiSCode(CgiSCode cgiSCode) {
		this.cgiSCode = cgiSCode;
	}

}
