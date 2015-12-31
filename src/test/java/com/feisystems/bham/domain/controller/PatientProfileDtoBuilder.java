package com.feisystems.bham.domain.controller;

import java.util.Date;

import com.feisystems.bham.service.dto.PatientProfileDto;

public class PatientProfileDtoBuilder {
	
	private PatientProfileDto dto;
	
	public PatientProfileDtoBuilder() {
		dto = new PatientProfileDto();
	}
	
	public PatientProfileDtoBuilder id(Long id) {
		dto.setId(id);
		return this;		
	}
	
	public PatientProfileDtoBuilder firstName(String firstName) {
		dto.setFirstName(firstName);		
		return this;
	}
	
	public PatientProfileDtoBuilder lastName(String lastName) {
		dto.setLastName(lastName);
		return this;
	}
	
	public PatientProfileDtoBuilder birthDate(Date birthDate) {
		dto.setBirthDate(birthDate);		
		return this;
	}
	
	public PatientProfileDtoBuilder username(String username) {
		dto.setUsername(username);
		return this;
	}
	
	public PatientProfileDtoBuilder medicalRecordNumber(String medicalRecordNumber) {
		dto.setMedicalRecordNumber(medicalRecordNumber);		
		return this;
	}
	
	public PatientProfileDtoBuilder administrativeGenderCode(String administrativeGenderCode) {
		dto.setAdministrativeGenderCode(administrativeGenderCode);		
		return this;
	}
	
	public PatientProfileDtoBuilder administrativeGenderDisplayName(String administrativeGenderCodeDisplayName) {
		dto.setAdministrativeGenderCodeDisplayName(administrativeGenderCodeDisplayName);		
		return this;
	}
	
	public PatientProfileDtoBuilder raceCode(String raceCode) {
		dto.setRaceCode(raceCode);
		return this;
	}
	
	public PatientProfileDtoBuilder raceCodeDisplayName(String raceCodeDisplayName) {
		dto.setRaceCodeDisplayName(raceCodeDisplayName);
		return this;
	}

	public PatientProfileDtoBuilder addressPostalCode(String addressPostalCode) {
		dto.setAddressPostalCode(addressPostalCode);
		return this;
	}
	
	public PatientProfileDtoBuilder addressStateCode(String addressStateCode) {
		dto.setAddressStateCode(addressStateCode);
		return this;
	}
	
	public PatientProfileDto build() {
		return dto;
	}
	
}
