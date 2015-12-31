package com.feisystems.bham.service.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ProcedureObservationDto {
	
	private Long id;
	
	private Long patientId;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date procedureStartDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date procedureEndDate;
	
    private String procedureTypeCode;
	
    private String procedureTypeName;
	
    private String bodySiteCode;
	
    private String procedureStatusCode;
	
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

	public Date getProcedureStartDate() {
		return procedureStartDate;
	}

	public void setProcedureStartDate(Date procedureStartDate) {
		this.procedureStartDate = procedureStartDate;
	}

	public Date getProcedureEndDate() {
		return procedureEndDate;
	}

	public void setProcedureEndDate(Date procedureEndDate) {
		this.procedureEndDate = procedureEndDate;
	}

	public String getBodySiteCode() {
		return bodySiteCode;
	}

	public void setBodySiteCode(String bodySiteCode) {
		this.bodySiteCode = bodySiteCode;
	}

	public String getProcedureStatusCode() {
		return procedureStatusCode;
	}

	public void setProcedureStatusCode(String procedureStatusCode) {
		this.procedureStatusCode = procedureStatusCode;
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
}
