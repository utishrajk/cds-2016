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
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class UspstfSpecificRecommendation implements Serializable  {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date recommendationDateTime;

    /**
     */
    private String id;

    /**
     */
    private String title;

    /**
     */
    private String grade;

    /**
     */
    private String gradeVer;

    /**
     */
    private String text;

    /**
     */
    private String rationale;

    /**
     */
    private String url;

    /**
     */
    private String servFreq;

    /**
     */
    private String riskName;

    /**
     */
    private String riskText;

    /**
     */
    @ManyToOne
    private UspstfGeneralRecommendation UspstfGeneralRecommendation;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<UspstfRecommendationTool> tools = new HashSet<UspstfRecommendationTool>();

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	private static final long serialVersionUID = 1L;

	public Date getRecommendationDateTime() {
        return this.recommendationDateTime;
    }

	public void setRecommendationDateTime(Date recommendationDateTime) {
        this.recommendationDateTime = recommendationDateTime;
    }

	public String getId() {
        return this.id;
    }

	public void setId(String id) {
        this.id = id;
    }

	public String getTitle() {
        return this.title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getGrade() {
        return this.grade;
    }

	public void setGrade(String grade) {
        this.grade = grade;
    }

	public String getGradeVer() {
        return this.gradeVer;
    }

	public void setGradeVer(String gradeVer) {
        this.gradeVer = gradeVer;
    }

	public String getText() {
        return this.text;
    }

	public void setText(String text) {
        this.text = text;
    }

	public String getRationale() {
        return this.rationale;
    }

	public void setRationale(String rationale) {
        this.rationale = rationale;
    }

	public String getUrl() {
        return this.url;
    }

	public void setUrl(String url) {
        this.url = url;
    }

	public String getServFreq() {
        return this.servFreq;
    }

	public void setServFreq(String servFreq) {
        this.servFreq = servFreq;
    }

	public String getRiskName() {
        return this.riskName;
    }

	public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

	public String getRiskText() {
        return this.riskText;
    }

	public void setRiskText(String riskText) {
        this.riskText = riskText;
    }

	public UspstfGeneralRecommendation getUspstfGeneralRecommendation() {
        return this.UspstfGeneralRecommendation;
    }

	public void setUspstfGeneralRecommendation(UspstfGeneralRecommendation UspstfGeneralRecommendation) {
        this.UspstfGeneralRecommendation = UspstfGeneralRecommendation;
    }

	public Set<UspstfRecommendationTool> getTools() {
        return this.tools;
    }

	public void setTools(Set<UspstfRecommendationTool> tools) {
        this.tools = tools;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_")
    private Long id_;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId_() {
        return this.id_;
    }

	public void setId_(Long id) {
        this.id_ = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof UspstfSpecificRecommendation)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        UspstfSpecificRecommendation rhs = (UspstfSpecificRecommendation) obj;
        return new EqualsBuilder().append(UspstfGeneralRecommendation, rhs.UspstfGeneralRecommendation).append(grade, rhs.grade).append(gradeVer, rhs.gradeVer).append(id, rhs.id).append(id_, rhs.id_).append(rationale, rhs.rationale).append(recommendationDateTime, rhs.recommendationDateTime).append(riskName, rhs.riskName).append(riskText, rhs.riskText).append(servFreq, rhs.servFreq).append(text, rhs.text).append(title, rhs.title).append(url, rhs.url).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(UspstfGeneralRecommendation).append(grade).append(gradeVer).append(id).append(id_).append(rationale).append(recommendationDateTime).append(riskName).append(riskText).append(servFreq).append(text).append(title).append(url).toHashCode();
    }
}
