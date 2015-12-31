package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfRecommendationToolService;
import java.security.SecureRandom;
import java.util.ArrayList;
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
public class UspstfRecommendationToolDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<UspstfRecommendationTool> data;

	@Autowired
    UspstfRecommendationToolService uspstfRecommendationToolService;

	@Autowired
    UspstfRecommendationToolRepository uspstfRecommendationToolRepository;

	public UspstfRecommendationTool getNewTransientUspstfRecommendationTool(int index) {
        UspstfRecommendationTool obj = new UspstfRecommendationTool();
        setText(obj, index);
        setTitle(obj, index);
        setUrl(obj, index);
        return obj;
    }

	public void setText(UspstfRecommendationTool obj, int index) {
        String text = "text_" + index;
        obj.setText(text);
    }

	public void setTitle(UspstfRecommendationTool obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public void setUrl(UspstfRecommendationTool obj, int index) {
        String url = "url_" + index;
        obj.setUrl(url);
    }

	public UspstfRecommendationTool getSpecificUspstfRecommendationTool(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UspstfRecommendationTool obj = data.get(index);
        Long id = obj.getId();
        return uspstfRecommendationToolService.findUspstfRecommendationTool(id);
    }

	public UspstfRecommendationTool getRandomUspstfRecommendationTool() {
        init();
        UspstfRecommendationTool obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return uspstfRecommendationToolService.findUspstfRecommendationTool(id);
    }

	public boolean modifyUspstfRecommendationTool(UspstfRecommendationTool obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = uspstfRecommendationToolService.findUspstfRecommendationToolEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UspstfRecommendationTool' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UspstfRecommendationTool>();
        for (int i = 0; i < 10; i++) {
            UspstfRecommendationTool obj = getNewTransientUspstfRecommendationTool(i);
            try {
                uspstfRecommendationToolService.saveUspstfRecommendationTool(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            uspstfRecommendationToolRepository.flush();
            data.add(obj);
        }
    }
}
