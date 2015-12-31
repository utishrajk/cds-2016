package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ProductFormCodeService;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class ProductFormCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ProductFormCodeDataOnDemand dod;

	@Autowired
    ProductFormCodeService productFormCodeService;

	@Autowired
    ProductFormCodeRepository productFormCodeRepository;

	@Test
    public void testCountAllProductFormCodes() {
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", dod.getRandomProductFormCode());
        long count = productFormCodeService.countAllProductFormCodes();
        Assert.assertTrue("Counter for 'ProductFormCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindProductFormCode() {
        ProductFormCode obj = dod.getRandomProductFormCode();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to provide an identifier", id);
        obj = productFormCodeService.findProductFormCode(id);
        Assert.assertNotNull("Find method for 'ProductFormCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'ProductFormCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllProductFormCodes() {
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", dod.getRandomProductFormCode());
        long count = productFormCodeService.countAllProductFormCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'ProductFormCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<ProductFormCode> result = productFormCodeService.findAllProductFormCodes();
        Assert.assertNotNull("Find all method for 'ProductFormCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'ProductFormCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindProductFormCodeEntries() {
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", dod.getRandomProductFormCode());
        long count = productFormCodeService.countAllProductFormCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<ProductFormCode> result = productFormCodeService.findProductFormCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'ProductFormCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'ProductFormCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        ProductFormCode obj = dod.getRandomProductFormCode();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to provide an identifier", id);
        obj = productFormCodeService.findProductFormCode(id);
        Assert.assertNotNull("Find method for 'ProductFormCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyProductFormCode(obj);
        Integer currentVersion = obj.getVersion();
        productFormCodeRepository.flush();
        Assert.assertTrue("Version for 'ProductFormCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateProductFormCodeUpdate() {
        ProductFormCode obj = dod.getRandomProductFormCode();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to provide an identifier", id);
        obj = productFormCodeService.findProductFormCode(id);
        boolean modified =  dod.modifyProductFormCode(obj);
        Integer currentVersion = obj.getVersion();
        ProductFormCode merged = (ProductFormCode)productFormCodeService.updateProductFormCode(obj);
        productFormCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'ProductFormCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveProductFormCode() {
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", dod.getRandomProductFormCode());
        ProductFormCode obj = dod.getNewTransientProductFormCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'ProductFormCode' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'ProductFormCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteProductFormCode() {
        ProductFormCode obj = dod.getRandomProductFormCode();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ProductFormCode' failed to provide an identifier", id);
        obj = productFormCodeService.findProductFormCode(id);
        productFormCodeService.deleteProductFormCode(obj);
        productFormCodeRepository.flush();
        Assert.assertNull("Failed to remove 'ProductFormCode' with identifier '" + id + "'", productFormCodeService.findProductFormCode(id));
    }
}
