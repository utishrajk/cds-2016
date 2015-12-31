package com.feisystems.bham.domain.decisionfacts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id", "version", "displayName", "codeSystem", "codeSystemName", "codeSystemVersion", "originalText"})
public abstract class AbstractCodedConcept {
	
    private Long id;

    private Integer version;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String code;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String displayName;

    /**
     */
    @Size(max = 250)
    private String codeSystem;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String codeSystemName;

    /**
     */
    private Double codeSystemVersion;

    /**
     */
    @Size(max = 250)
    private String originalText;

	public String getCode() {
        return this.code;
    }

	public void setCode(String code) {
        this.code = code;
    }

	public String getDisplayName() {
        return this.displayName;
    }

	public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

	public String getCodeSystem() {
        return this.codeSystem;
    }

	public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

	public String getCodeSystemName() {
        return this.codeSystemName;
    }

	public void setCodeSystemName(String codeSystemName) {
        this.codeSystemName = codeSystemName;
    }

	public Double getCodeSystemVersion() {
        return this.codeSystemVersion;
    }

	public void setCodeSystemVersion(Double codeSystemVersion) {
        this.codeSystemVersion = codeSystemVersion;
    }

	public String getOriginalText() {
        return this.originalText;
    }

	public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

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
}
