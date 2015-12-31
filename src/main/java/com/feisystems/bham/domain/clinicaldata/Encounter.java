package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.provider.AbstractProvider;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;

@Entity
public class Encounter implements Serializable {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date encounterEventTime;

    /**
     */
    @Embedded
    private CodedConceptValueObject encounterType;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AbstractProvider> performers = new HashSet<AbstractProvider>();

    /**
     */
    private String encounterFreeText;

    /**
     */
    @ManyToOne
    private Patient patient;

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

	public Date getEncounterEventTime() {
        return this.encounterEventTime;
    }

	public void setEncounterEventTime(Date encounterEventTime) {
        this.encounterEventTime = encounterEventTime;
    }

	public CodedConceptValueObject getEncounterType() {
        return this.encounterType;
    }

	public void setEncounterType(CodedConceptValueObject encounterType) {
        this.encounterType = encounterType;
    }

	public Set<AbstractProvider> getPerformers() {
        return this.performers;
    }

	public void setPerformers(Set<AbstractProvider> performers) {
        this.performers = performers;
    }

	public String getEncounterFreeText() {
        return this.encounterFreeText;
    }

	public void setEncounterFreeText(String encounterFreeText) {
        this.encounterFreeText = encounterFreeText;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	private static final long serialVersionUID = 1L;

	public boolean equals(Object obj) {
        if (!(obj instanceof Encounter)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Encounter rhs = (Encounter) obj;
        return new EqualsBuilder().append(encounterEventTime, rhs.encounterEventTime).append(encounterFreeText, rhs.encounterFreeText).append(encounterType, rhs.encounterType).append(id, rhs.id).append(patient, rhs.patient).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(encounterEventTime).append(encounterFreeText).append(encounterType).append(id).append(patient).toHashCode();
    }

}
