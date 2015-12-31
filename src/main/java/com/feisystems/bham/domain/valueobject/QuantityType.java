package com.feisystems.bham.domain.valueobject;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.feisystems.bham.domain.reference.UnitOfMeasureCode;

@Embeddable
public class QuantityType implements Serializable {

    /**
     */
    @NotNull
    private Double measuredValue;

    /**
     */
    @ManyToOne
    private UnitOfMeasureCode unitOfMeasureCode;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	private static final long serialVersionUID = 1L;

	public Double getMeasuredValue() {
        return this.measuredValue;
    }

	public void setMeasuredValue(Double measuredValue) {
        this.measuredValue = measuredValue;
    }

	public UnitOfMeasureCode getUnitOfMeasureCode() {
        return this.unitOfMeasureCode;
    }

	public void setUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }
}
