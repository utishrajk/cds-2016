package com.feisystems.bham.service.dto;

import java.util.List;

public class AbstractProviderDto {
	private Long id;
	
	private String npi;
	
	private String email;
	
	private String mailingAddressTelephoneNumber;
	
	private String mailingAddressFaxNumber;
	
	private String website;
	
	private String firstLineMailingAddress;
	
	private String secondLineMailingAddress;

	private String mailingAddressCityName;
	
	private String mailingAddressStateName;
	
	private String mailingAddressPostalCode;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailingAddressTelephoneNumber() {
		return mailingAddressTelephoneNumber;
	}

	public void setMailingAddressTelephoneNumber(
			String mailingAddressTelephoneNumber) {
		this.mailingAddressTelephoneNumber = mailingAddressTelephoneNumber;
	}

	public String getMailingAddressFaxNumber() {
		return mailingAddressFaxNumber;
	}

	public void setMailingAddressFaxNumber(String mailingAddressFaxNumber) {
		this.mailingAddressFaxNumber = mailingAddressFaxNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFirstLineMailingAddress() {
		return firstLineMailingAddress;
	}

	public void setFirstLineMailingAddress(String firstLineMailingAddress) {
		this.firstLineMailingAddress = firstLineMailingAddress;
	}

	public String getSecondLineMailingAddress() {
		return secondLineMailingAddress;
	}

	public void setSecondLineMailingAddress(String secondLineMailingAddress) {
		this.secondLineMailingAddress = secondLineMailingAddress;
	}

	public String getMailingAddressCityName() {
		return mailingAddressCityName;
	}

	public void setMailingAddressCityName(String mailingAddressCityName) {
		this.mailingAddressCityName = mailingAddressCityName;
	}

	public String getMailingAddressStateName() {
		return mailingAddressStateName;
	}

	public void setMailingAddressStateName(String mailingAddressStateName) {
		this.mailingAddressStateName = mailingAddressStateName;
	}

	public String getMailingAddressPostalCode() {
		return mailingAddressPostalCode;
	}

	public void setMailingAddressPostalCode(String mailingAddressPostalCode) {
		this.mailingAddressPostalCode = mailingAddressPostalCode;
	}

}
