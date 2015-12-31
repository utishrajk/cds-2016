package com.feisystems.bham.domain.valueobject;
import java.io.Serializable;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.feisystems.bham.domain.reference.TelecomUseCode;
import javax.persistence.ManyToOne;

@Embeddable
public class TelephoneType implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 30)
    private String telephone;

    /**
     */
    @ManyToOne
    private TelecomUseCode telecomUseCode;

	private static final long serialVersionUID = 1L;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String getTelephone() {
        return this.telephone;
    }

	public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

	public TelecomUseCode getTelecomUseCode() {
        return this.telecomUseCode;
    }

	public void setTelecomUseCode(TelecomUseCode telecomUseCode) {
        this.telecomUseCode = telecomUseCode;
    }
}
