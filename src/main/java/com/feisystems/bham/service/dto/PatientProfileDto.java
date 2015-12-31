package com.feisystems.bham.service.dto;

import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class PatientProfileDto.
 */
public class PatientProfileDto {

	/** The id. */
	private Long id;

	/** The medical record number. */
	@Size(max = 30)
	private String medicalRecordNumber;

	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** Full name. */
	private String fullName;

	/** The username. */
	@JsonIgnore
	private String username;
	
	/** The birth date. */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date birthDate;

	/** The administrativeGender code. */
	private String administrativeGenderCode;

	/** The administrativeGender display name */
	private String administrativeGenderCodeDisplayName;

	/** The race code. */
	private String raceCode;
	
	/** The race display name. */
	private String raceCodeDisplayName;

	/** The address postal code. */
	@Pattern(regexp = "(^\\d{5}$|^\\d{5}-\\d{4})*")
	private String addressPostalCode;

	/** The address state code. */
	private String addressStateCode;
	
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
	 * Gets the username.
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

	/**
	 * Gets the medical record number.
	 * @return the medical record number
	 */
	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	/**
	 * Sets the medical record number.
	 * @param medicalRecordNumber the new medical record number
	 */
	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public String getFullName() {
		this.fullName = this.firstName + " " + this.lastName;
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the first name.
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the birth date.
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the address state code.
	 * @return the address state code
	 */
	public String getAddressStateCode() {
		return addressStateCode;
	}

	/**
	 * Sets the address state code.
	 * @param addressStateCode the new address state code
	 */
	public void setAddressStateCode(String addressStateCode) {
		this.addressStateCode = addressStateCode;
	}

	/**
	 * Gets the address postal code.
	 * @return the address postal code
	 */
	public String getAddressPostalCode() {
		return addressPostalCode;
	}

	/**
	 * Sets the address postal code.
	 * @param addressPostalCode the new address postal code
	 */
	public void setAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	
	public String getAdministrativeGenderCode() {
		return administrativeGenderCode;
	}

	public void setAdministrativeGenderCode(String administrativeGenderCode) {
		this.administrativeGenderCode = administrativeGenderCode;
	}

	public String getAdministrativeGenderCodeDisplayName() {
		return administrativeGenderCodeDisplayName;
	}

	public void setAdministrativeGenderCodeDisplayName(String administrativeGenderCodeDisplayName) {
		this.administrativeGenderCodeDisplayName = administrativeGenderCodeDisplayName;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getRaceCodeDisplayName() {
		return raceCodeDisplayName;
	}

	public void setRaceCodeDisplayName(String raceCodeDisplayName) {
		this.raceCodeDisplayName = raceCodeDisplayName;
	}


}
