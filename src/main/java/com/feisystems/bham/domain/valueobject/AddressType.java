package com.feisystems.bham.domain.valueobject;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.feisystems.bham.domain.reference.AddressUseCode;
import com.feisystems.bham.domain.reference.StateCode;
import com.feisystems.bham.domain.reference.CountryCode;

@Embeddable
public class AddressType implements Serializable  {

    /**
     */
    @ManyToOne
    private AddressUseCode addressUseCode;

    /**
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String streetAddressLine;

    /**
     */
    @NotNull
    @Size(max = 30)
    private String city;

    /**
     */
    @NotNull
    @ManyToOne
    private StateCode stateCode;

    /**
     */
    @NotNull
    @Pattern(regexp = "\\d{5}(?:[-\\s]\\d{4})?")
    private String postalCode;

    /**
     */
    @NotNull
    @ManyToOne
    private CountryCode countryCode;

	public AddressUseCode getAddressUseCode() {
        return this.addressUseCode;
    }

	public void setAddressUseCode(AddressUseCode addressUseCode) {
        this.addressUseCode = addressUseCode;
    }

	public String getStreetAddressLine() {
        return this.streetAddressLine;
    }

	public void setStreetAddressLine(String streetAddressLine) {
        this.streetAddressLine = streetAddressLine;
    }

	public String getCity() {
        return this.city;
    }

	public void setCity(String city) {
        this.city = city;
    }

	public StateCode getStateCode() {
        return this.stateCode;
    }

	public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

	public String getPostalCode() {
        return this.postalCode;
    }

	public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

	public CountryCode getCountryCode() {
        return this.countryCode;
    }

	public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

	private static final long serialVersionUID = 1L;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
