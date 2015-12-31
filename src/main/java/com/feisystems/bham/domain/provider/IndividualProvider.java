package com.feisystems.bham.domain.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.feisystems.bham.communication.VerificationToken;
import com.feisystems.bham.domain.reference.AdministrativeGenderCode;

@Entity
public class IndividualProvider extends AbstractProvider implements Serializable {
	
    @NotNull
    @Size(min = 0, max = 50)
    @Column(unique=true)
    private String userName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @NotNull
    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String middleName;

    @Size(max = 30)
    private String namePrefix;

    @Size(max = 30)
    private String nameSuffix;

    @NotNull
    @Size(max = 80)
    private String credential;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "name", referencedColumnName = "name")})
    private Set<Authority> authorities;
    
    @OneToMany(mappedBy="user", targetEntity=VerificationToken.class, cascade=CascadeType.ALL)
    private List<VerificationToken> verificationTokens = new ArrayList<VerificationToken>();
    
    private boolean isVerified;

    @ManyToOne
    private AdministrativeGenderCode administrativeGenderCode;

	@Override
	public String toString() {
		return "IndividualProvider [userName=" + userName + ", lastName="
				+ lastName + ", firstName=" + firstName + ", middleName="
				+ middleName + ", namePrefix=" + namePrefix + ", nameSuffix="
				+ nameSuffix + ", credential=" + credential + ", authorities="
				+ authorities + ", administrativeGenderCode="
				+ administrativeGenderCode + "]";
	}

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getFirstName() {
        return this.firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getMiddleName() {
        return this.middleName;
    }

	public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

	public String getNamePrefix() {
        return this.namePrefix;
    }

	public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

	public String getNameSuffix() {
        return this.nameSuffix;
    }

	public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

	public String getCredential() {
        return this.credential;
    }

	public void setCredential(String credential) {
        this.credential = credential;
    }

	public AdministrativeGenderCode getAdministrativeGenderCode() {
        return this.administrativeGenderCode;
    }

	public void setAdministrativeGenderCode(AdministrativeGenderCode administrativeGenderCode) {
        this.administrativeGenderCode = administrativeGenderCode;
    }
	
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}


	private static final long serialVersionUID = 1L;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((credential == null) ? 0 : credential.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndividualProvider other = (IndividualProvider) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	public synchronized void addVerificationToken(VerificationToken token) {
        verificationTokens.add(token);
    }

    public synchronized List<VerificationToken> getVerificationTokens() {
        return Collections.unmodifiableList(this.verificationTokens);
    }
    
}
