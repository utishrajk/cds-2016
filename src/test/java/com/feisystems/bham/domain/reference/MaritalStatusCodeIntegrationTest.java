package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.MaritalStatusCodeService;
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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class MaritalStatusCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MaritalStatusCodeDataOnDemand dod;

	@Autowired
    MaritalStatusCodeService maritalStatusCodeService;

	@Autowired
    MaritalStatusCodeRepository maritalStatusCodeRepository;

	@Test
    public void testCountAllMaritalStatusCodes() {
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", dod.getRandomMaritalStatusCode());
        long count = maritalStatusCodeService.countAllMaritalStatusCodes();
        Assert.assertTrue("Counter for 'MaritalStatusCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMaritalStatusCode() {
        MaritalStatusCode obj = dod.getRandomMaritalStatusCode();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to provide an identifier", id);
        obj = maritalStatusCodeService.findMaritalStatusCode(id);
        Assert.assertNotNull("Find method for 'MaritalStatusCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'MaritalStatusCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMaritalStatusCodes() {
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", dod.getRandomMaritalStatusCode());
        long count = maritalStatusCodeService.countAllMaritalStatusCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'MaritalStatusCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<MaritalStatusCode> result = maritalStatusCodeService.findAllMaritalStatusCodes();
        Assert.assertNotNull("Find all method for 'MaritalStatusCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'MaritalStatusCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMaritalStatusCodeEntries() {
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", dod.getRandomMaritalStatusCode());
        long count = maritalStatusCodeService.countAllMaritalStatusCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<MaritalStatusCode> result = maritalStatusCodeService.findMaritalStatusCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'MaritalStatusCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'MaritalStatusCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        MaritalStatusCode obj = dod.getRandomMaritalStatusCode();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to provide an identifier", id);
        obj = maritalStatusCodeService.findMaritalStatusCode(id);
        Assert.assertNotNull("Find method for 'MaritalStatusCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMaritalStatusCode(obj);
        Integer currentVersion = obj.getVersion();
        maritalStatusCodeRepository.flush();
        Assert.assertTrue("Version for 'MaritalStatusCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMaritalStatusCodeUpdate() {
        MaritalStatusCode obj = dod.getRandomMaritalStatusCode();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to provide an identifier", id);
        obj = maritalStatusCodeService.findMaritalStatusCode(id);
        boolean modified =  dod.modifyMaritalStatusCode(obj);
        Integer currentVersion = obj.getVersion();
        MaritalStatusCode merged = (MaritalStatusCode)maritalStatusCodeService.updateMaritalStatusCode(obj);
        maritalStatusCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'MaritalStatusCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMaritalStatusCode() {
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", dod.getRandomMaritalStatusCode());
        MaritalStatusCode obj = dod.getNewTransientMaritalStatusCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'MaritalStatusCode' identifier to be null", obj.getId());
        try {
            maritalStatusCodeService.saveMaritalStatusCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        maritalStatusCodeRepository.flush();
        Assert.assertNotNull("Expected 'MaritalStatusCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMaritalStatusCode() {
        MaritalStatusCode obj = dod.getRandomMaritalStatusCode();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MaritalStatusCode' failed to provide an identifier", id);
        obj = maritalStatusCodeService.findMaritalStatusCode(id);
        maritalStatusCodeService.deleteMaritalStatusCode(obj);
        maritalStatusCodeRepository.flush();
        Assert.assertNull("Failed to remove 'MaritalStatusCode' with identifier '" + id + "'", maritalStatusCodeService.findMaritalStatusCode(id));
    }
}
