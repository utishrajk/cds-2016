package com.feisystems.bham.domain.decisionfacts;

import java.io.Serializable;

public class Patient implements Serializable {

	public Patient(String administrativeGenderCode, String age,
			String raceCode, String zipCode) {
		super();
		this.administrativeGenderCode = administrativeGenderCode;
		this.age = age;
		this.raceCode = raceCode;
		this.zipCode = zipCode;
	}

	private static final long serialVersionUID = 1L;

	private String administrativeGenderCode;
	
	private String age;
	
	private String raceCode;
	
	private String zipCode;
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAdministrativeGenderCode() {
		return administrativeGenderCode;
	}

	public void setAdministrativeGenderCode(String administrativeGenderCode) {
		this.administrativeGenderCode = administrativeGenderCode;
	}


	

	public Patient(String administrativeGenderCode,
			String problemCode) {
		super();
		this.administrativeGenderCode = administrativeGenderCode;
		this.problemCode = problemCode;
	}
	
	private String problemCode;

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

}
