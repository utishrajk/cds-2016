package com.feisystems.bham.domain.carerecommendations;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class UspstfRecommendationTool implements Serializable  {

    /**
     */
    private String title;

    /**
     */
    private String url;

    /**
     */
    private String text;

	private static final long serialVersionUID = 1L;

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

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getUrl() {
        return this.url;
    }

	public void setUrl(String url) {
        this.url = url;
    }

	public String getText() {
        return this.text;
    }

	public void setText(String text) {
        this.text = text;
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof UspstfRecommendationTool)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        UspstfRecommendationTool rhs = (UspstfRecommendationTool) obj;
        return new EqualsBuilder().append(id, rhs.id).append(text, rhs.text).append(title, rhs.title).append(url, rhs.url).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(text).append(title).append(url).toHashCode();
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
