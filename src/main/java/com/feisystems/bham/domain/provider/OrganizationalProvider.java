package com.feisystems.bham.domain.provider;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OrganizationalProvider extends AbstractProvider implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 30)
    private String orgName;

    /**
     */
    @Size(max = 30)
    private String otherOrgName;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String authorizedOfficialLastName;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String authorizedOfficialFirstName;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String authorizedOfficialTitle;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String authorizedOfficialNamePrefix;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String authorizedOfficialTelephoneNumber;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


	private static final long serialVersionUID = 1L;

//	public boolean equals(Object obj) {
//        if (!(obj instanceof OrganizationalProvider)) {
//            return false;
//        }
//        if (this == obj) {
//            return true;
//        }
//        OrganizationalProvider rhs = (OrganizationalProvider) obj;
//        return new EqualsBuilder().append(authorizedOfficialFirstName, rhs.authorizedOfficialFirstName).append(authorizedOfficialLastName, rhs.authorizedOfficialLastName).append(authorizedOfficialNamePrefix, rhs.authorizedOfficialNamePrefix).append(authorizedOfficialTelephoneNumber, rhs.authorizedOfficialTelephoneNumber).append(authorizedOfficialTitle, rhs.authorizedOfficialTitle).append(enumerationDate, rhs.enumerationDate).append(firstLineMailingAddress, rhs.firstLineMailingAddress).append(firstLinePracticeLocationAddress, rhs.firstLinePracticeLocationAddress).append(id, rhs.id).append(lastUpdateDate, rhs.lastUpdateDate).append(mailingAddressCityName, rhs.mailingAddressCityName).append(mailingAddressCountryCode, rhs.mailingAddressCountryCode).append(mailingAddressFaxNumber, rhs.mailingAddressFaxNumber).append(mailingAddressPostalCode, rhs.mailingAddressPostalCode).append(mailingAddressStateName, rhs.mailingAddressStateName).append(mailingAddressTelephoneNumber, rhs.mailingAddressTelephoneNumber).append(npi, rhs.npi).append(orgName, rhs.orgName).append(otherOrgName, rhs.otherOrgName).append(practiceLocationAddressCityName, rhs.practiceLocationAddressCityName).append(practiceLocationAddressCountryCode, rhs.practiceLocationAddressCountryCode).append(practiceLocationAddressFaxNumber, rhs.practiceLocationAddressFaxNumber).append(practiceLocationAddressPostalCode, rhs.practiceLocationAddressPostalCode).append(practiceLocationAddressStateName, rhs.practiceLocationAddressStateName).append(practiceLocationAddressTelephoneNumber, rhs.practiceLocationAddressTelephoneNumber).append(providerEntityType, rhs.providerEntityType).append(providerTaxonomyCode, rhs.providerTaxonomyCode).append(secondLineMailingAddress, rhs.secondLineMailingAddress).append(secondLinePracticeLocationAddress, rhs.secondLinePracticeLocationAddress).isEquals();
//    }
//
//	public int hashCode() {
//        return new HashCodeBuilder().append(authorizedOfficialFirstName).append(authorizedOfficialLastName).append(authorizedOfficialNamePrefix).append(authorizedOfficialTelephoneNumber).append(authorizedOfficialTitle).append(enumerationDate).append(firstLineMailingAddress).append(firstLinePracticeLocationAddress).append(id).append(lastUpdateDate).append(mailingAddressCityName).append(mailingAddressCountryCode).append(mailingAddressFaxNumber).append(mailingAddressPostalCode).append(mailingAddressStateName).append(mailingAddressTelephoneNumber).append(npi).append(orgName).append(otherOrgName).append(practiceLocationAddressCityName).append(practiceLocationAddressCountryCode).append(practiceLocationAddressFaxNumber).append(practiceLocationAddressPostalCode).append(practiceLocationAddressStateName).append(practiceLocationAddressTelephoneNumber).append(providerEntityType).append(providerTaxonomyCode).append(secondLineMailingAddress).append(secondLinePracticeLocationAddress).toHashCode();
//    }

	public String getOrgName() {
        return this.orgName;
    }

	public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

	public String getOtherOrgName() {
        return this.otherOrgName;
    }

	public void setOtherOrgName(String otherOrgName) {
        this.otherOrgName = otherOrgName;
    }

	public String getAuthorizedOfficialLastName() {
        return this.authorizedOfficialLastName;
    }

	public void setAuthorizedOfficialLastName(String authorizedOfficialLastName) {
        this.authorizedOfficialLastName = authorizedOfficialLastName;
    }

	public String getAuthorizedOfficialFirstName() {
        return this.authorizedOfficialFirstName;
    }

	public void setAuthorizedOfficialFirstName(String authorizedOfficialFirstName) {
        this.authorizedOfficialFirstName = authorizedOfficialFirstName;
    }

	public String getAuthorizedOfficialTitle() {
        return this.authorizedOfficialTitle;
    }

	public void setAuthorizedOfficialTitle(String authorizedOfficialTitle) {
        this.authorizedOfficialTitle = authorizedOfficialTitle;
    }

	public String getAuthorizedOfficialNamePrefix() {
        return this.authorizedOfficialNamePrefix;
    }

	public void setAuthorizedOfficialNamePrefix(String authorizedOfficialNamePrefix) {
        this.authorizedOfficialNamePrefix = authorizedOfficialNamePrefix;
    }

	public String getAuthorizedOfficialTelephoneNumber() {
        return this.authorizedOfficialTelephoneNumber;
    }

	public void setAuthorizedOfficialTelephoneNumber(String authorizedOfficialTelephoneNumber) {
        this.authorizedOfficialTelephoneNumber = authorizedOfficialTelephoneNumber;
    }
}
