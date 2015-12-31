package com.feisystems.bham.service.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OutcomeDto {

	private Long id;
	
	private Long patientId;
	
	private Integer version;
    
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date startDate;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date endDate;
	
    private String symptoms;
	
    private String tolerability;
	
    private String procedureTypeCode;
	
    private String procedureTypeName;
	
    private String cgiSCode;
	
    private String cgiSName;
	
    private String cgiICode;
	
    private String cgiIName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getProcedureTypeCode() {
		return procedureTypeCode;
	}

	public void setProcedureTypeCode(String procedureTypeCode) {
		this.procedureTypeCode = procedureTypeCode;
	}

	public String getProcedureTypeName() {
		return procedureTypeName;
	}

	public void setProcedureTypeName(String procedureTypeName) {
		this.procedureTypeName = procedureTypeName;
	}

	public String getCgiSCode() {
		return cgiSCode;
	}

	public void setCgiSCode(String cgiSCode) {
		this.cgiSCode = cgiSCode;
	}

	public String getCgiSName() {
		return cgiSName;
	}

	public void setCgiSName(String cgiSName) {
		this.cgiSName = cgiSName;
	}

	public String getCgiICode() {
		return cgiICode;
	}

	public void setCgiICode(String cgiICode) {
		this.cgiICode = cgiICode;
	}

	public String getCgiIName() {
		return cgiIName;
	}

	public void setCgiIName(String cgiIName) {
		this.cgiIName = cgiIName;
	}

}
