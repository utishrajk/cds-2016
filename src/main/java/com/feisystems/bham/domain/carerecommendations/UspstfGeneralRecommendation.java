package com.feisystems.bham.domain.carerecommendations;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class UspstfGeneralRecommendation implements Serializable  {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date recommendationDateTime;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<UspstfSpecificRecommendation> uspstfSpecificRecommendations = new HashSet<UspstfSpecificRecommendation>();

    /**
     */
    private String title;

    /**
     */
    private String rationale;

    /**
     */
    private String clinical;

    /**
     */
    private String clinicalUrl;

    /**
     */
    private String other;

    /**
     */
    private String otherUrl;

    /**
     */
    private String topic;

	public boolean equals(Object obj) {
        if (!(obj instanceof UspstfGeneralRecommendation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        UspstfGeneralRecommendation rhs = (UspstfGeneralRecommendation) obj;
        return new EqualsBuilder().append(clinical, rhs.clinical).append(clinicalUrl, rhs.clinicalUrl).append(id, rhs.id).append(other, rhs.other).append(otherUrl, rhs.otherUrl).append(rationale, rhs.rationale).append(recommendationDateTime, rhs.recommendationDateTime).append(title, rhs.title).append(topic, rhs.topic).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(clinical).append(clinicalUrl).append(id).append(other).append(otherUrl).append(rationale).append(recommendationDateTime).append(title).append(topic).toHashCode();
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Date getRecommendationDateTime() {
        return this.recommendationDateTime;
    }

	public void setRecommendationDateTime(Date recommendationDateTime) {
        this.recommendationDateTime = recommendationDateTime;
    }

	public Set<UspstfSpecificRecommendation> getUspstfSpecificRecommendations() {
        return this.uspstfSpecificRecommendations;
    }

	public void setUspstfSpecificRecommendations(Set<UspstfSpecificRecommendation> uspstfSpecificRecommendations) {
        this.uspstfSpecificRecommendations = uspstfSpecificRecommendations;
    }

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getRationale() {
        return this.rationale;
    }

	public void setRationale(String rationale) {
        this.rationale = rationale;
    }

	public String getClinical() {
        return this.clinical;
    }

	public void setClinical(String clinical) {
        this.clinical = clinical;
    }

	public String getClinicalUrl() {
        return this.clinicalUrl;
    }

	public void setClinicalUrl(String clinicalUrl) {
        this.clinicalUrl = clinicalUrl;
    }

	public String getOther() {
        return this.other;
    }

	public void setOther(String other) {
        this.other = other;
    }

	public String getOtherUrl() {
        return this.otherUrl;
    }

	public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
    }

	public String getTopic() {
        return this.topic;
    }

	public void setTopic(String topic) {
        this.topic = topic;
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
