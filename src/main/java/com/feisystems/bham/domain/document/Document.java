package com.feisystems.bham.domain.document;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.feisystems.bham.domain.reference.DocumentTypeCode;

@Entity
public class Document implements Serializable {

    /**
     */
    @NotNull
    @Size(max = 30)
    private String name;

    /**
     */
    @Size(max = 500)
    private String description;

    /**
     */
    @ManyToOne
    private DocumentTypeCode documentTypeCode;

    /**
     * TODO: Change this to byte[]. Roo does not support byte[]
     */
    @Lob
    private String content;

    /**
     */
    @NotNull
    private String contentType;

    /**
     */
    private String contentStandardName;

    /**
     */
    private String contentStandardVersion;

    /**
     */
    private String DocumentProvenance;

    /**
     */
    @NotNull
    private Long documentSize;

    /**
     */
    @Size(max = 250)
    private String documentUrl;

	public boolean equals(Object obj) {
        if (!(obj instanceof Document)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Document rhs = (Document) obj;
        return new EqualsBuilder().append(DocumentProvenance, rhs.DocumentProvenance).append(content, rhs.content).append(contentStandardName, rhs.contentStandardName).append(contentStandardVersion, rhs.contentStandardVersion).append(contentType, rhs.contentType).append(description, rhs.description).append(documentSize, rhs.documentSize).append(documentTypeCode, rhs.documentTypeCode).append(documentUrl, rhs.documentUrl).append(id, rhs.id).append(name, rhs.name).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(DocumentProvenance).append(content).append(contentStandardName).append(contentStandardVersion).append(contentType).append(description).append(documentSize).append(documentTypeCode).append(documentUrl).append(id).append(name).toHashCode();
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public DocumentTypeCode getDocumentTypeCode() {
        return this.documentTypeCode;
    }

	public void setDocumentTypeCode(DocumentTypeCode documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

	public String getContent() {
        return this.content;
    }

	public void setContent(String content) {
        this.content = content;
    }

	public String getContentType() {
        return this.contentType;
    }

	public void setContentType(String contentType) {
        this.contentType = contentType;
    }

	public String getContentStandardName() {
        return this.contentStandardName;
    }

	public void setContentStandardName(String contentStandardName) {
        this.contentStandardName = contentStandardName;
    }

	public String getContentStandardVersion() {
        return this.contentStandardVersion;
    }

	public void setContentStandardVersion(String contentStandardVersion) {
        this.contentStandardVersion = contentStandardVersion;
    }

	public String getDocumentProvenance() {
        return this.DocumentProvenance;
    }

	public void setDocumentProvenance(String DocumentProvenance) {
        this.DocumentProvenance = DocumentProvenance;
    }

	public Long getDocumentSize() {
        return this.documentSize;
    }

	public void setDocumentSize(Long documentSize) {
        this.documentSize = documentSize;
    }

	public String getDocumentUrl() {
        return this.documentUrl;
    }

	public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
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

	private static final long serialVersionUID = 1L;
}
