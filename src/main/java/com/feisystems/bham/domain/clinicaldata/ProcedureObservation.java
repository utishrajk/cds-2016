package com.feisystems.bham.domain.clinicaldata;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.provider.AbstractProvider;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCode;

@Entity
public class ProcedureObservation implements Serializable {

    /**
     */
    @ManyToOne
    private ActStatusCode procedureStatusCode;

    /**
     */
    @ManyToOne
    private ProcedureTypeCode procedureType;

    /**
     */
    @ManyToOne
    private BodySiteCode bodySiteCode;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date procedureStartDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date procedureEndDate;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AbstractProvider> procedurePerformers = new HashSet<AbstractProvider>();

    /**
     */
    @ManyToOne
    private Patient patient;

	public boolean equals(Object obj) {
        if (!(obj instanceof ProcedureObservation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ProcedureObservation rhs = (ProcedureObservation) obj;
        return new EqualsBuilder().append(bodySiteCode, rhs.bodySiteCode).append(id, rhs.id).append(patient, rhs.patient).append(procedureEndDate, rhs.procedureEndDate).append(procedureStartDate, rhs.procedureStartDate).append(procedureStatusCode, rhs.procedureStatusCode).append(procedureType, rhs.procedureType).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(bodySiteCode).append(id).append(patient).append(procedureEndDate).append(procedureStartDate).append(procedureStatusCode).append(procedureType).toHashCode();
    }

	private static final long serialVersionUID = 1L;

	public ActStatusCode getProcedureStatusCode() {
        return this.procedureStatusCode;
    }

	public void setProcedureStatusCode(ActStatusCode procedureStatusCode) {
        this.procedureStatusCode = procedureStatusCode;
    }

	public BodySiteCode getBodySiteCode() {
        return this.bodySiteCode;
    }

	public void setBodySiteCode(BodySiteCode bodySiteCode) {
        this.bodySiteCode = bodySiteCode;
    }

	public Date getProcedureStartDate() {
        return this.procedureStartDate;
    }

	public void setProcedureStartDate(Date procedureStartDate) {
        this.procedureStartDate = procedureStartDate;
    }

	public Date getProcedureEndDate() {
        return this.procedureEndDate;
    }

	public void setProcedureEndDate(Date procedureEndDate) {
        this.procedureEndDate = procedureEndDate;
    }

	public Set<AbstractProvider> getProcedurePerformers() {
        return this.procedurePerformers;
    }

	public void setProcedurePerformers(Set<AbstractProvider> procedurePerformers) {
        this.procedurePerformers = procedurePerformers;
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

	public ProcedureTypeCode getProcedureType() {
		return procedureType;
	}

	public void setProcedureType(ProcedureTypeCode procedureType) {
		this.procedureType = procedureType;
	}
	
}
