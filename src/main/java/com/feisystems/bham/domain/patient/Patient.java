package com.feisystems.bham.domain.patient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.clinicaldata.Allergy;
import com.feisystems.bham.domain.clinicaldata.Encounter;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusProblemObservation;
import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultOrganizer;
import com.feisystems.bham.domain.clinicaldata.HospitalDischargeInstruction;
import com.feisystems.bham.domain.clinicaldata.Medication;
import com.feisystems.bham.domain.clinicaldata.Outcome;
import com.feisystems.bham.domain.clinicaldata.PatientClinicalDocument;
import com.feisystems.bham.domain.clinicaldata.PatientGoal;
import com.feisystems.bham.domain.clinicaldata.PlanOfCare;
import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ReasonForVisit;
import com.feisystems.bham.domain.clinicaldata.ResultOrganizer;
import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.VitalSignOrganizer;
import com.feisystems.bham.domain.provider.AbstractProvider;
import com.feisystems.bham.domain.reference.AdministrativeGenderCode;
import com.feisystems.bham.domain.reference.EthnicGroupCode;
import com.feisystems.bham.domain.reference.LanguageCode;
import com.feisystems.bham.domain.reference.MaritalStatusCode;
import com.feisystems.bham.domain.reference.RaceCode;
import com.feisystems.bham.domain.reference.ReligiousAffiliationCode;
import com.feisystems.bham.domain.valueobject.AddressType;
import com.feisystems.bham.domain.valueobject.TelephoneType;

@Entity
public class Patient implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    /**
     */
    @Size(max = 30)
    private String prefix;

    /**
     */
    @Embedded
    private AddressType address;

    /**
     */
    @Embedded
    private TelephoneType telephone;

    /**
     */
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String email;

    /**
     */
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthDay;

    /**
     */
    private String username;

    /**
     */
    @Pattern(regexp = "\\d{3}-?\\d{2}-?\\d{4}")
    private String socialSecurityNumber;

    /**
     */
    @ManyToOne
    private AdministrativeGenderCode administrativeGenderCode;

    /**
     */
    @ManyToOne
    private MaritalStatusCode maritalStatusCode;

    /**
     */
    @ManyToOne
    private ReligiousAffiliationCode religiousAffiliationCode;

    /**
     */
    @ManyToOne
    private RaceCode raceCode;

    /**
     */
    @ManyToOne
    private EthnicGroupCode ethnicGroupCode;

    /**
     */
    @ManyToOne
    private LanguageCode languageCode;

    /**
     */
    @Size(max = 30)
    private String medicalRecordNumber;

    /**
     */
    @Size(max = 30)
    private String patientIdNumber;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AbstractProvider> providers = new HashSet<AbstractProvider>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PatientClinicalDocument> patientClinicalDocuments = new HashSet<PatientClinicalDocument>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<Problem> problems = new HashSet<Problem>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<Allergy> allergies = new HashSet<Allergy>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<ProcedureObservation> procedureObservations = new HashSet<ProcedureObservation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<SocialHistory> socialHistories = new HashSet<SocialHistory>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<Medication> medications = new HashSet<Medication>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<ResultOrganizer> resultOrganizers = new HashSet<ResultOrganizer>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<VitalSignOrganizer> vitalSignOrganizers = new HashSet<VitalSignOrganizer>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<FunctionalStatusProblemObservation> functionalStatusProblemObservations = new HashSet<FunctionalStatusProblemObservation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<FunctionalStatusResultOrganizer> functionalStatusResultOrganizers = new HashSet<FunctionalStatusResultOrganizer>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PlanOfCare> planOfCares = new HashSet<PlanOfCare>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<Encounter> encounters = new HashSet<Encounter>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<ReasonForVisit> reasonForVisits = new HashSet<ReasonForVisit>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<HospitalDischargeInstruction> hospitalDischargeInstructions = new HashSet<HospitalDischargeInstruction>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PatientGoal> patientGoals = new HashSet<PatientGoal>();
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<Outcome> outcomes = new HashSet<Outcome>();

	public String getFirstName() {
        return this.firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getPrefix() {
        return this.prefix;
    }

	public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

	public AddressType getAddress() {
        return this.address;
    }

	public void setAddress(AddressType address) {
        this.address = address;
    }

	public TelephoneType getTelephone() {
        return this.telephone;
    }

	public void setTelephone(TelephoneType telephone) {
        this.telephone = telephone;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public Date getBirthDay() {
        return this.birthDay;
    }

	public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

	public String getUsername() {
        return this.username;
    }

	public void setUsername(String username) {
        this.username = username;
    }

	public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

	public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

	public AdministrativeGenderCode getAdministrativeGenderCode() {
        return this.administrativeGenderCode;
    }

	public void setAdministrativeGenderCode(AdministrativeGenderCode administrativeGenderCode) {
        this.administrativeGenderCode = administrativeGenderCode;
    }

	public MaritalStatusCode getMaritalStatusCode() {
        return this.maritalStatusCode;
    }

	public void setMaritalStatusCode(MaritalStatusCode maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

	public ReligiousAffiliationCode getReligiousAffiliationCode() {
        return this.religiousAffiliationCode;
    }

	public void setReligiousAffiliationCode(ReligiousAffiliationCode religiousAffiliationCode) {
        this.religiousAffiliationCode = religiousAffiliationCode;
    }

	public RaceCode getRaceCode() {
        return this.raceCode;
    }

	public void setRaceCode(RaceCode raceCode) {
        this.raceCode = raceCode;
    }

	public EthnicGroupCode getEthnicGroupCode() {
        return this.ethnicGroupCode;
    }

	public void setEthnicGroupCode(EthnicGroupCode ethnicGroupCode) {
        this.ethnicGroupCode = ethnicGroupCode;
    }

	public LanguageCode getLanguageCode() {
        return this.languageCode;
    }

	public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

	public String getMedicalRecordNumber() {
        return this.medicalRecordNumber;
    }

	public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

	public String getPatientIdNumber() {
        return this.patientIdNumber;
    }

	public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

	public Set<AbstractProvider> getProviders() {
        return this.providers;
    }

	public void setProviders(Set<AbstractProvider> providers) {
        this.providers = providers;
    }

	public Set<PatientClinicalDocument> getPatientClinicalDocuments() {
        return this.patientClinicalDocuments;
    }

	public void setPatientClinicalDocuments(Set<PatientClinicalDocument> patientClinicalDocuments) {
        this.patientClinicalDocuments = patientClinicalDocuments;
    }

	public Set<Problem> getProblems() {
        return this.problems;
    }

	public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

	public Set<Allergy> getAllergies() {
        return this.allergies;
    }

	public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

	public Set<ProcedureObservation> getProcedureObservations() {
        return this.procedureObservations;
    }

	public void setProcedureObservations(Set<ProcedureObservation> procedureObservations) {
        this.procedureObservations = procedureObservations;
    }

	public Set<SocialHistory> getSocialHistories() {
        return this.socialHistories;
    }

	public void setSocialHistories(Set<SocialHistory> socialHistories) {
        this.socialHistories = socialHistories;
    }

	public Set<Medication> getMedications() {
        return this.medications;
    }

	public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

	public Set<ResultOrganizer> getResultOrganizers() {
        return this.resultOrganizers;
    }

	public void setResultOrganizers(Set<ResultOrganizer> resultOrganizers) {
        this.resultOrganizers = resultOrganizers;
    }

	public Set<VitalSignOrganizer> getVitalSignOrganizers() {
        return this.vitalSignOrganizers;
    }

	public void setVitalSignOrganizers(Set<VitalSignOrganizer> vitalSignOrganizers) {
        this.vitalSignOrganizers = vitalSignOrganizers;
    }

	public Set<FunctionalStatusProblemObservation> getFunctionalStatusProblemObservations() {
        return this.functionalStatusProblemObservations;
    }

	public void setFunctionalStatusProblemObservations(Set<FunctionalStatusProblemObservation> functionalStatusProblemObservations) {
        this.functionalStatusProblemObservations = functionalStatusProblemObservations;
    }

	public Set<FunctionalStatusResultOrganizer> getFunctionalStatusResultOrganizers() {
        return this.functionalStatusResultOrganizers;
    }

	public void setFunctionalStatusResultOrganizers(Set<FunctionalStatusResultOrganizer> functionalStatusResultOrganizers) {
        this.functionalStatusResultOrganizers = functionalStatusResultOrganizers;
    }

	public Set<PlanOfCare> getPlanOfCares() {
        return this.planOfCares;
    }

	public void setPlanOfCares(Set<PlanOfCare> planOfCares) {
        this.planOfCares = planOfCares;
    }

	public Set<Encounter> getEncounters() {
        return this.encounters;
    }

	public void setEncounters(Set<Encounter> encounters) {
        this.encounters = encounters;
    }

	public Set<ReasonForVisit> getReasonForVisits() {
        return this.reasonForVisits;
    }

	public void setReasonForVisits(Set<ReasonForVisit> reasonForVisits) {
        this.reasonForVisits = reasonForVisits;
    }

	public Set<HospitalDischargeInstruction> getHospitalDischargeInstructions() {
        return this.hospitalDischargeInstructions;
    }

	public void setHospitalDischargeInstructions(Set<HospitalDischargeInstruction> hospitalDischargeInstructions) {
        this.hospitalDischargeInstructions = hospitalDischargeInstructions;
    }

	public Set<PatientGoal> getPatientGoals() {
        return this.patientGoals;
    }

	public void setPatientGoals(Set<PatientGoal> patientGoals) {
        this.patientGoals = patientGoals;
    }
	
	public Set<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(Set<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof Patient)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Patient rhs = (Patient) obj;
        return new EqualsBuilder().append(address, rhs.address).append(administrativeGenderCode, rhs.administrativeGenderCode).append(birthDay, rhs.birthDay).append(email, rhs.email).append(ethnicGroupCode, rhs.ethnicGroupCode).append(firstName, rhs.firstName).append(id, rhs.id).append(languageCode, rhs.languageCode).append(lastName, rhs.lastName).append(maritalStatusCode, rhs.maritalStatusCode).append(medicalRecordNumber, rhs.medicalRecordNumber).append(patientIdNumber, rhs.patientIdNumber).append(prefix, rhs.prefix).append(raceCode, rhs.raceCode).append(religiousAffiliationCode, rhs.religiousAffiliationCode).append(socialSecurityNumber, rhs.socialSecurityNumber).append(telephone, rhs.telephone).append(username, rhs.username).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(address).append(administrativeGenderCode).append(birthDay).append(email).append(ethnicGroupCode).append(firstName).append(id).append(languageCode).append(lastName).append(maritalStatusCode).append(medicalRecordNumber).append(patientIdNumber).append(prefix).append(raceCode).append(religiousAffiliationCode).append(socialSecurityNumber).append(telephone).append(username).toHashCode();
    }

	private static final long serialVersionUID = 1L;
}
