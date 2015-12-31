package com.feisystems.bham.service.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SocialHistoryDto {
	
	private Long id;
	
	private Long patientId;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date startDate;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date endDate;
	
	private String socialHistoryTypeCode;
	
	private String socialHistoryStatusCode;
	
	private String socialHistoryTypeName;
	
	private String socialHistoryStatusName;
	
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

	public String getSocialHistoryTypeCode() {
		return socialHistoryTypeCode;
	}

	public void setSocialHistoryTypeCode(String socialHistoryTypeCode) {
		this.socialHistoryTypeCode = socialHistoryTypeCode;
	}

	public String getSocialHistoryStatusCode() {
		return socialHistoryStatusCode;
	}

	public void setSocialHistoryStatusCode(String socialHistoryStatusCode) {
		this.socialHistoryStatusCode = socialHistoryStatusCode;
	}

	public String getSocialHistoryTypeName() {
		return socialHistoryTypeName;
	}

	public void setSocialHistoryTypeName(String socialHistoryTypeName) {
		this.socialHistoryTypeName = socialHistoryTypeName;
	}

	public String getSocialHistoryStatusName() {
		return socialHistoryStatusName;
	}

	public void setSocialHistoryStatusName(String socialHistoryStatusName) {
		this.socialHistoryStatusName = socialHistoryStatusName;
	}

}
