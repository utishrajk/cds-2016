package com.feisystems.bham.service.dto;


public class OrganizationProviderDto extends AbstractProviderDto {
	
    private String orgName;

    private String otherOrgName;

    private String authorizedOfficialLastName;

    private String authorizedOfficialFirstName;

    private String authorizedOfficialTitle;

    private String authorizedOfficialNamePrefix;

    private String authorizedOfficialTelephoneNumber;
    
    public OrganizationProviderDto() {
	}
    
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOtherOrgName() {
		return otherOrgName;
	}

	public void setOtherOrgName(String otherOrgName) {
		this.otherOrgName = otherOrgName;
	}

	public String getAuthorizedOfficialLastName() {
		return authorizedOfficialLastName;
	}

	public void setAuthorizedOfficialLastName(String authorizedOfficialLastName) {
		this.authorizedOfficialLastName = authorizedOfficialLastName;
	}

	public String getAuthorizedOfficialFirstName() {
		return authorizedOfficialFirstName;
	}

	public void setAuthorizedOfficialFirstName(String authorizedOfficialFirstName) {
		this.authorizedOfficialFirstName = authorizedOfficialFirstName;
	}

	public String getAuthorizedOfficialTitle() {
		return authorizedOfficialTitle;
	}

	public void setAuthorizedOfficialTitle(String authorizedOfficialTitle) {
		this.authorizedOfficialTitle = authorizedOfficialTitle;
	}

	public String getAuthorizedOfficialNamePrefix() {
		return authorizedOfficialNamePrefix;
	}

	public void setAuthorizedOfficialNamePrefix(String authorizedOfficialNamePrefix) {
		this.authorizedOfficialNamePrefix = authorizedOfficialNamePrefix;
	}

	public String getAuthorizedOfficialTelephoneNumber() {
		return authorizedOfficialTelephoneNumber;
	}

	public void setAuthorizedOfficialTelephoneNumber(
			String authorizedOfficialTelephoneNumber) {
		this.authorizedOfficialTelephoneNumber = authorizedOfficialTelephoneNumber;
	}
    
    
}
