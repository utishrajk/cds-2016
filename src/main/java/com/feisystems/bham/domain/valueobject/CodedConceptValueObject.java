package com.feisystems.bham.domain.valueobject;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class CodedConceptValueObject implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 250)
    private String code;

    /**
     */
    @Size(max = 250)
    private String codeSystem;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String displayName;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String codeSystemName;

    /**
     */
    @Size(max = 250)
    private String originalText;

    /**
     */
    private Double codeSystemVersion;

	private static final long serialVersionUID = 1L;
	
	public String getCode() {
        return this.code;
    }

	public void setCode(String code) {
        this.code = code;
    }

	public String getCodeSystem() {
        return this.codeSystem;
    }

	public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

	public String getDisplayName() {
        return this.displayName;
    }

	public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

	public String getCodeSystemName() {
        return this.codeSystemName;
    }

	public void setCodeSystemName(String codeSystemName) {
        this.codeSystemName = codeSystemName;
    }

	public String getOriginalText() {
        return this.originalText;
    }

	public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

	public Double getCodeSystemVersion() {
        return this.codeSystemVersion;
    }

	public void setCodeSystemVersion(Double codeSystemVersion) {
        this.codeSystemVersion = codeSystemVersion;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
