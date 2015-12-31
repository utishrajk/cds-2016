package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.BodySiteCodeService;
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
public class BodySiteCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BodySiteCodeDataOnDemand dod;

	@Autowired
    BodySiteCodeService bodySiteCodeService;

	@Autowired
    BodySiteCodeRepository bodySiteCodeRepository;

	@Test
    public void testCountAllBodySiteCodes() {
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", dod.getRandomBodySiteCode());
        long count = bodySiteCodeService.countAllBodySiteCodes();
        Assert.assertTrue("Counter for 'BodySiteCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindBodySiteCode() {
        BodySiteCode obj = dod.getRandomBodySiteCode();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to provide an identifier", id);
        obj = bodySiteCodeService.findBodySiteCode(id);
        Assert.assertNotNull("Find method for 'BodySiteCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BodySiteCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllBodySiteCodes() {
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", dod.getRandomBodySiteCode());
        long count = bodySiteCodeService.countAllBodySiteCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'BodySiteCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BodySiteCode> result = bodySiteCodeService.findAllBodySiteCodes();
        Assert.assertNotNull("Find all method for 'BodySiteCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BodySiteCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindBodySiteCodeEntries() {
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", dod.getRandomBodySiteCode());
        long count = bodySiteCodeService.countAllBodySiteCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BodySiteCode> result = bodySiteCodeService.findBodySiteCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'BodySiteCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BodySiteCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        BodySiteCode obj = dod.getRandomBodySiteCode();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to provide an identifier", id);
        obj = bodySiteCodeService.findBodySiteCode(id);
        Assert.assertNotNull("Find method for 'BodySiteCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBodySiteCode(obj);
        Integer currentVersion = obj.getVersion();
        bodySiteCodeRepository.flush();
        Assert.assertTrue("Version for 'BodySiteCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateBodySiteCodeUpdate() {
        BodySiteCode obj = dod.getRandomBodySiteCode();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to provide an identifier", id);
        obj = bodySiteCodeService.findBodySiteCode(id);
        boolean modified =  dod.modifyBodySiteCode(obj);
        Integer currentVersion = obj.getVersion();
        BodySiteCode merged = (BodySiteCode)bodySiteCodeService.updateBodySiteCode(obj);
        bodySiteCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'BodySiteCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveBodySiteCode() {
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", dod.getRandomBodySiteCode());
        BodySiteCode obj = dod.getNewTransientBodySiteCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BodySiteCode' identifier to be null", obj.getId());
        try {
            bodySiteCodeService.saveBodySiteCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        bodySiteCodeRepository.flush();
        Assert.assertNotNull("Expected 'BodySiteCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteBodySiteCode() {
        BodySiteCode obj = dod.getRandomBodySiteCode();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BodySiteCode' failed to provide an identifier", id);
        obj = bodySiteCodeService.findBodySiteCode(id);
        bodySiteCodeService.deleteBodySiteCode(obj);
        bodySiteCodeRepository.flush();
        Assert.assertNull("Failed to remove 'BodySiteCode' with identifier '" + id + "'", bodySiteCodeService.findBodySiteCode(id));
    }
}
