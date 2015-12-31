package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ProductFormCodeService;
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

@Component
@Configurable
public class ProductFormCodeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<ProductFormCode> data;

	@Autowired
    ProductFormCodeService productFormCodeService;

	@Autowired
    ProductFormCodeRepository productFormCodeRepository;

	public ProductFormCode getNewTransientProductFormCode(int index) {
        ProductFormCode obj = new ProductFormCode();
        setCode(obj, index);
        setCodeSystem(obj, index);
        setCodeSystemName(obj, index);
        setCodeSystemVersion(obj, index);
        setDisplayName(obj, index);
        setOriginalText(obj, index);
        return obj;
    }

	public void setCode(ProductFormCode obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setCodeSystem(ProductFormCode obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setCodeSystemName(ProductFormCode obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setCodeSystemVersion(ProductFormCode obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDisplayName(ProductFormCode obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setOriginalText(ProductFormCode obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public ProductFormCode getSpecificProductFormCode(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ProductFormCode obj = data.get(index);
        Long id = obj.getId();
        return productFormCodeService.findProductFormCode(id);
    }

	public ProductFormCode getRandomProductFormCode() {
        init();
        ProductFormCode obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return productFormCodeService.findProductFormCode(id);
    }

	public boolean modifyProductFormCode(ProductFormCode obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = productFormCodeService.findProductFormCodeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ProductFormCode' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ProductFormCode>();
        for (int i = 0; i < 10; i++) {
            ProductFormCode obj = getNewTransientProductFormCode(i);
            try {
                productFormCodeService.saveProductFormCode(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            productFormCodeRepository.flush();
            data.add(obj);
        }
    }
}
