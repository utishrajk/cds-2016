package com.feisystems.bham.domain.clinicaldata;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCode;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.reference.RouteCode;
import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.domain.reference.ProductFormCode;
import com.feisystems.bham.domain.patient.Patient;

@Entity
public class Medication implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 250)
    private String freeTextSig;

    /**
     */
    @ManyToOne
    private MedicationDeliveryMethodCode medicationDeliveryMethodCode;

    /**
     */
    @ManyToOne
    private ActStatusCode medicationStatusCode;

    /**
     */
    @Embedded
    private CodedConceptValueObject medicationCode;

    /**
     */
    @ManyToOne
    private RouteCode routeCode;

    /**
     */
    @ManyToOne
    private BodySiteCode bodySiteCode;

    /**
     */
    @Embedded
    private QuantityType doseQuantity;

    /**
     */
    @ManyToOne
    private ProductFormCode productFormCode;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date medicationStartDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date medicationEndDate;

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

	public String getFreeTextSig() {
        return this.freeTextSig;
    }

	public void setFreeTextSig(String freeTextSig) {
        this.freeTextSig = freeTextSig;
    }

	public MedicationDeliveryMethodCode getMedicationDeliveryMethodCode() {
        return this.medicationDeliveryMethodCode;
    }

	public void setMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode) {
        this.medicationDeliveryMethodCode = medicationDeliveryMethodCode;
    }

	public ActStatusCode getMedicationStatusCode() {
        return this.medicationStatusCode;
    }

	public void setMedicationStatusCode(ActStatusCode medicationStatusCode) {
        this.medicationStatusCode = medicationStatusCode;
    }

	public CodedConceptValueObject getMedicationCode() {
        return this.medicationCode;
    }

	public void setMedicationCode(CodedConceptValueObject medicationCode) {
        this.medicationCode = medicationCode;
    }

	public RouteCode getRouteCode() {
        return this.routeCode;
    }

	public void setRouteCode(RouteCode routeCode) {
        this.routeCode = routeCode;
    }

	public BodySiteCode getBodySiteCode() {
        return this.bodySiteCode;
    }

	public void setBodySiteCode(BodySiteCode bodySiteCode) {
        this.bodySiteCode = bodySiteCode;
    }

	public QuantityType getDoseQuantity() {
        return this.doseQuantity;
    }

	public void setDoseQuantity(QuantityType doseQuantity) {
        this.doseQuantity = doseQuantity;
    }

	public ProductFormCode getProductFormCode() {
        return this.productFormCode;
    }

	public void setProductFormCode(ProductFormCode productFormCode) {
        this.productFormCode = productFormCode;
    }

	public Date getMedicationStartDate() {
        return this.medicationStartDate;
    }

	public void setMedicationStartDate(Date medicationStartDate) {
        this.medicationStartDate = medicationStartDate;
    }

	public Date getMedicationEndDate() {
        return this.medicationEndDate;
    }

	public void setMedicationEndDate(Date medicationEndDate) {
        this.medicationEndDate = medicationEndDate;
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
        if (!(obj instanceof Medication)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Medication rhs = (Medication) obj;
        return new EqualsBuilder().append(bodySiteCode, rhs.bodySiteCode).append(doseQuantity, rhs.doseQuantity).append(freeTextSig, rhs.freeTextSig).append(id, rhs.id).append(medicationCode, rhs.medicationCode).append(medicationDeliveryMethodCode, rhs.medicationDeliveryMethodCode).append(medicationEndDate, rhs.medicationEndDate).append(medicationStartDate, rhs.medicationStartDate).append(medicationStatusCode, rhs.medicationStatusCode).append(patient, rhs.patient).append(productFormCode, rhs.productFormCode).append(routeCode, rhs.routeCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(bodySiteCode).append(doseQuantity).append(freeTextSig).append(id).append(medicationCode).append(medicationDeliveryMethodCode).append(medicationEndDate).append(medicationStartDate).append(medicationStatusCode).append(patient).append(productFormCode).append(routeCode).toHashCode();
    }
}
