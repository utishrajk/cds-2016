package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfGeneralRecommendationService;
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
public class UspstfGeneralRecommendationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<UspstfGeneralRecommendation> data;

	@Autowired
    UspstfGeneralRecommendationService uspstfGeneralRecommendationService;

	@Autowired
    UspstfGeneralRecommendationRepository uspstfGeneralRecommendationRepository;

	public UspstfGeneralRecommendation getNewTransientUspstfGeneralRecommendation(int index) {
        UspstfGeneralRecommendation obj = new UspstfGeneralRecommendation();
        setClinical(obj, index);
        setClinicalUrl(obj, index);
        setOther(obj, index);
        setOtherUrl(obj, index);
        setRationale(obj, index);
        setRecommendationDateTime(obj, index);
        setTitle(obj, index);
        setTopic(obj, index);
        return obj;
    }

	public void setClinical(UspstfGeneralRecommendation obj, int index) {
        String clinical = "clinical_" + index;
        obj.setClinical(clinical);
    }

	public void setClinicalUrl(UspstfGeneralRecommendation obj, int index) {
        String clinicalUrl = "clinicalUrl_" + index;
        obj.setClinicalUrl(clinicalUrl);
    }

	public void setOther(UspstfGeneralRecommendation obj, int index) {
        String other = "other_" + index;
        obj.setOther(other);
    }

	public void setOtherUrl(UspstfGeneralRecommendation obj, int index) {
        String otherUrl = "otherUrl_" + index;
        obj.setOtherUrl(otherUrl);
    }

	public void setRationale(UspstfGeneralRecommendation obj, int index) {
        String rationale = "rationale_" + index;
        obj.setRationale(rationale);
    }

	public void setRecommendationDateTime(UspstfGeneralRecommendation obj, int index) {
        Date recommendationDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setRecommendationDateTime(recommendationDateTime);
    }

	public void setTitle(UspstfGeneralRecommendation obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public void setTopic(UspstfGeneralRecommendation obj, int index) {
        String topic = "topic_" + index;
        obj.setTopic(topic);
    }

	public UspstfGeneralRecommendation getSpecificUspstfGeneralRecommendation(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UspstfGeneralRecommendation obj = data.get(index);
        Long id = obj.getId();
        return uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
    }

	public UspstfGeneralRecommendation getRandomUspstfGeneralRecommendation() {
        init();
        UspstfGeneralRecommendation obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
    }

	public boolean modifyUspstfGeneralRecommendation(UspstfGeneralRecommendation obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = uspstfGeneralRecommendationService.findUspstfGeneralRecommendationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UspstfGeneralRecommendation' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UspstfGeneralRecommendation>();
        for (int i = 0; i < 10; i++) {
            UspstfGeneralRecommendation obj = getNewTransientUspstfGeneralRecommendation(i);
            try {
                uspstfGeneralRecommendationService.saveUspstfGeneralRecommendation(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            uspstfGeneralRecommendationRepository.flush();
            data.add(obj);
        }
    }
}
