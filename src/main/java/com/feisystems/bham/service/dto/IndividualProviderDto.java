package com.feisystems.bham.service.dto;

import java.util.List;

public class IndividualProviderDto extends AbstractProviderDto {

	private String userName;

	private String credential;

	private String confirmPassword;

	private String lastName;

	private String firstName;

	private String middleName;

	private String namePrefix;

	private String nameSuffix;

	private String administrativeGenderCode;

	private String providerTaxonomyCode;

	private String providerTaxonomyCodeDisplayName;
	
	private List<String> roles;
	
	public IndividualProviderDto() {
		super();
	}

	public IndividualProviderDto(Long id, String userName, String firstName,
			String lastName, String email, List<String> roles) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setId(id);
		this.setEmail(email);
		this.roles = roles;
	}

	public String getProviderTaxonomyCode() {
		return providerTaxonomyCode;
	}

	public void setProviderTaxonomyCode(String providerTaxonomyCode) {
		this.providerTaxonomyCode = providerTaxonomyCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getAdministrativeGenderCode() {
		return administrativeGenderCode;
	}

	public void setAdministrativeGenderCode(String administrativeGenderCode) {
		this.administrativeGenderCode = administrativeGenderCode;
	}

	public String getProviderTaxonomyCodeDisplayName() {
		return providerTaxonomyCodeDisplayName;
	}

	public void setProviderTaxonomyCodeDisplayName(
			String providerTaxonomyCodeDisplayName) {
		this.providerTaxonomyCodeDisplayName = providerTaxonomyCodeDisplayName;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
