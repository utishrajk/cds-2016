package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfSpecificRecommendationService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class UspstfSpecificRecommendationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<UspstfSpecificRecommendation> data;

	@Autowired
    UspstfGeneralRecommendationDataOnDemand uspstfGeneralRecommendationDataOnDemand;

	@Autowired
    UspstfSpecificRecommendationService uspstfSpecificRecommendationService;

	@Autowired
    UspstfSpecificRecommendationRepository uspstfSpecificRecommendationRepository;

	public UspstfSpecificRecommendation getNewTransientUspstfSpecificRecommendation(int index) {
        UspstfSpecificRecommendation obj = new UspstfSpecificRecommendation();
        setGrade(obj, index);
        setGradeVer(obj, index);
        setId(obj, index);
        setRationale(obj, index);
        setRecommendationDateTime(obj, index);
        setRiskName(obj, index);
        setRiskText(obj, index);
        setServFreq(obj, index);
        setText(obj, index);
        setTitle(obj, index);
        setUrl(obj, index);
        return obj;
    }

	public void setGrade(UspstfSpecificRecommendation obj, int index) {
        String grade = "grade_" + index;
        obj.setGrade(grade);
    }

	public void setGradeVer(UspstfSpecificRecommendation obj, int index) {
        String gradeVer = "gradeVer_" + index;
        obj.setGradeVer(gradeVer);
    }

	public void setId(UspstfSpecificRecommendation obj, int index) {
        String id = "id_" + index;
        obj.setId(id);
    }

	public void setRationale(UspstfSpecificRecommendation obj, int index) {
        String rationale = "rationale_" + index;
        obj.setRationale(rationale);
    }

	public void setRecommendationDateTime(UspstfSpecificRecommendation obj, int index) {
        Date recommendationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setRecommendationDateTime(recommendationDateTime);
    }

	public void setRiskName(UspstfSpecificRecommendation obj, int index) {
        String riskName = "riskName_" + index;
        obj.setRiskName(riskName);
    }

	public void setRiskText(UspstfSpecificRecommendation obj, int index) {
        String riskText = "riskText_" + index;
        obj.setRiskText(riskText);
    }

	public void setServFreq(UspstfSpecificRecommendation obj, int index) {
        String servFreq = "servFreq_" + index;
        obj.setServFreq(servFreq);
    }

	public void setText(UspstfSpecificRecommendation obj, int index) {
        String text = "text_" + index;
        obj.setText(text);
    }

	public void setTitle(UspstfSpecificRecommendation obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public void setUrl(UspstfSpecificRecommendation obj, int index) {
        String url = "url_" + index;
        obj.setUrl(url);
    }

	public UspstfSpecificRecommendation getSpecificUspstfSpecificRecommendation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UspstfSpecificRecommendation obj = data.get(index);
        Long id = obj.getId_();
        return uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
    }

	public UspstfSpecificRecommendation getRandomUspstfSpecificRecommendation() {
        init();
        UspstfSpecificRecommendation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId_();
        return uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
    }

	public boolean modifyUspstfSpecificRecommendation(UspstfSpecificRecommendation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = uspstfSpecificRecommendationService.findUspstfSpecificRecommendationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UspstfSpecificRecommendation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UspstfSpecificRecommendation>();
        for (int i = 0; i < 10; i++) {
            UspstfSpecificRecommendation obj = getNewTransientUspstfSpecificRecommendation(i);
            try {
                uspstfSpecificRecommendationService.saveUspstfSpecificRecommendation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            uspstfSpecificRecommendationRepository.flush();
            data.add(obj);
        }
    }
}
