package com.feisystems.bham.service.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The Class ProblemDto.
 */
public class ProblemDto {

	/** The id. */
	private Long id;

	/** The patient id. */
	private Long patientId;
	
	/** The first name. */
	private String patientFirstName;
	
	/** The last name. */
	private String patientLastName;
		
	/** The problem start date. */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    private Date startDate;

	/** The problem end date. */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date endDate;

	/** The problem code. */
	private String problemCode;
	
	/** The problem status code. */
	private String problemStatusCode;
	
	/** The problem display name. */
	private String problemDisplayName;

	/** The problem full name. */
	private String problemName;
	
	/** The medical record number. */
	@Size(max = 30)
	private String patientMedicalRecordNumber;
	
	/**
	 * Gets the id.
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 * @return the first name
	 */
	public String getPatientFirstName() {
		return patientFirstName;
	}

	/**
	 * Sets the first name.
	 * @param patientFirstName the new first name
	 */
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	/**
	 * Gets the last name.
	 * @return the last name
	 */
	public String getPatientLastName() {
		return patientLastName;
	}

	/**
	 * Sets the last name.
	 * @param patientLastName the new last name
	 */
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
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

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	
	public String getProblemStatusCode() {
		return problemStatusCode;
	}

	public void setProblemStatusCode(String problemStatusCode) {
		this.problemStatusCode = problemStatusCode;
	}

	public String getProblemDisplayName() {
		return problemDisplayName;
	}

	public void setProblemDisplayName(String problemDisplayName) {
		this.problemDisplayName = problemDisplayName;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getPatientMedicalRecordNumber() {
		return patientMedicalRecordNumber;
	}

	public void setPatientMedicalRecordNumber(String patientMedicalRecordNumber) {
		this.patientMedicalRecordNumber = patientMedicalRecordNumber;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	


}
