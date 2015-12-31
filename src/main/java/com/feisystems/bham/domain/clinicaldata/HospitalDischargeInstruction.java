package com.feisystems.bham.domain.clinicaldata;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.patient.Patient;

@Entity
public class HospitalDischargeInstruction implements Serializable {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar hospitalDischargeInstructionDateTime;

    /**
     */
    @Lob
    private String hospitalDischargeInstructionText;

    /**
     */
    @ManyToOne
    private Patient patient;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	private static final long serialVersionUID = 1L;

	public Calendar getHospitalDischargeInstructionDateTime() {
        return this.hospitalDischargeInstructionDateTime;
    }

	public void setHospitalDischargeInstructionDateTime(Calendar hospitalDischargeInstructionDateTime) {
        this.hospitalDischargeInstructionDateTime = hospitalDischargeInstructionDateTime;
    }

	public String getHospitalDischargeInstructionText() {
        return this.hospitalDischargeInstructionText;
    }

	public void setHospitalDischargeInstructionText(String hospitalDischargeInstructionText) {
        this.hospitalDischargeInstructionText = hospitalDischargeInstructionText;
    }

	public Patient getPatient() {
        return this.patient;
    }

	public void setPatient(Patient patient) {
        this.patient = patient;
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

	public boolean equals(Object obj) {
        if (!(obj instanceof HospitalDischargeInstruction)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        HospitalDischargeInstruction rhs = (HospitalDischargeInstruction) obj;
        return new EqualsBuilder().append(hospitalDischargeInstructionDateTime, rhs.hospitalDischargeInstructionDateTime).append(hospitalDischargeInstructionText, rhs.hospitalDischargeInstructionText).append(id, rhs.id).append(patient, rhs.patient).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(hospitalDischargeInstructionDateTime).append(hospitalDischargeInstructionText).append(id).append(patient).toHashCode();
    }

}
