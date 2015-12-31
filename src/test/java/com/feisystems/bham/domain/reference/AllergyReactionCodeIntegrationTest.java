package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.AllergyReactionCodeService;
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
public class AllergyReactionCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AllergyReactionCodeDataOnDemand dod;

	@Autowired
    AllergyReactionCodeService allergyReactionCodeService;

	@Autowired
    AllergyReactionCodeRepository allergyReactionCodeRepository;

	@Test
    public void testCountAllAllergyReactionCodes() {
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", dod.getRandomAllergyReactionCode());
        long count = allergyReactionCodeService.countAllAllergyReactionCodes();
        Assert.assertTrue("Counter for 'AllergyReactionCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAllergyReactionCode() {
        AllergyReactionCode obj = dod.getRandomAllergyReactionCode();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to provide an identifier", id);
        obj = allergyReactionCodeService.findAllergyReactionCode(id);
        Assert.assertNotNull("Find method for 'AllergyReactionCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AllergyReactionCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAllergyReactionCodes() {
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", dod.getRandomAllergyReactionCode());
        long count = allergyReactionCodeService.countAllAllergyReactionCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'AllergyReactionCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AllergyReactionCode> result = allergyReactionCodeService.findAllAllergyReactionCodes();
        Assert.assertNotNull("Find all method for 'AllergyReactionCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AllergyReactionCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAllergyReactionCodeEntries() {
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", dod.getRandomAllergyReactionCode());
        long count = allergyReactionCodeService.countAllAllergyReactionCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AllergyReactionCode> result = allergyReactionCodeService.findAllergyReactionCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AllergyReactionCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AllergyReactionCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        AllergyReactionCode obj = dod.getRandomAllergyReactionCode();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to provide an identifier", id);
        obj = allergyReactionCodeService.findAllergyReactionCode(id);
        Assert.assertNotNull("Find method for 'AllergyReactionCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAllergyReactionCode(obj);
        Integer currentVersion = obj.getVersion();
        allergyReactionCodeRepository.flush();
        Assert.assertTrue("Version for 'AllergyReactionCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateAllergyReactionCodeUpdate() {
        AllergyReactionCode obj = dod.getRandomAllergyReactionCode();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to provide an identifier", id);
        obj = allergyReactionCodeService.findAllergyReactionCode(id);
        boolean modified =  dod.modifyAllergyReactionCode(obj);
        Integer currentVersion = obj.getVersion();
        AllergyReactionCode merged = (AllergyReactionCode)allergyReactionCodeService.updateAllergyReactionCode(obj);
        allergyReactionCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AllergyReactionCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveAllergyReactionCode() {
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", dod.getRandomAllergyReactionCode());
        AllergyReactionCode obj = dod.getNewTransientAllergyReactionCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AllergyReactionCode' identifier to be null", obj.getId());
        try {
            allergyReactionCodeService.saveAllergyReactionCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        allergyReactionCodeRepository.flush();
        Assert.assertNotNull("Expected 'AllergyReactionCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteAllergyReactionCode() {
        AllergyReactionCode obj = dod.getRandomAllergyReactionCode();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AllergyReactionCode' failed to provide an identifier", id);
        obj = allergyReactionCodeService.findAllergyReactionCode(id);
        allergyReactionCodeService.deleteAllergyReactionCode(obj);
        allergyReactionCodeRepository.flush();
        Assert.assertNull("Failed to remove 'AllergyReactionCode' with identifier '" + id + "'", allergyReactionCodeService.findAllergyReactionCode(id));
    }
}
