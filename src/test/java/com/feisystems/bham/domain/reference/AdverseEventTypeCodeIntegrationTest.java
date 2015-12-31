package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.AdverseEventTypeCodeService;
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
public class AdverseEventTypeCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AdverseEventTypeCodeDataOnDemand dod;

	@Autowired
    AdverseEventTypeCodeService adverseEventTypeCodeService;

	@Autowired
    AdverseEventTypeCodeRepository adverseEventTypeCodeRepository;

	@Test
    public void testCountAllAdverseEventTypeCodes() {
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", dod.getRandomAdverseEventTypeCode());
        long count = adverseEventTypeCodeService.countAllAdverseEventTypeCodes();
        Assert.assertTrue("Counter for 'AdverseEventTypeCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAdverseEventTypeCode() {
        AdverseEventTypeCode obj = dod.getRandomAdverseEventTypeCode();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to provide an identifier", id);
        obj = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        Assert.assertNotNull("Find method for 'AdverseEventTypeCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AdverseEventTypeCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAdverseEventTypeCodes() {
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", dod.getRandomAdverseEventTypeCode());
        long count = adverseEventTypeCodeService.countAllAdverseEventTypeCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'AdverseEventTypeCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AdverseEventTypeCode> result = adverseEventTypeCodeService.findAllAdverseEventTypeCodes();
        Assert.assertNotNull("Find all method for 'AdverseEventTypeCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AdverseEventTypeCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAdverseEventTypeCodeEntries() {
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", dod.getRandomAdverseEventTypeCode());
        long count = adverseEventTypeCodeService.countAllAdverseEventTypeCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AdverseEventTypeCode> result = adverseEventTypeCodeService.findAdverseEventTypeCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AdverseEventTypeCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AdverseEventTypeCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        AdverseEventTypeCode obj = dod.getRandomAdverseEventTypeCode();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to provide an identifier", id);
        obj = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        Assert.assertNotNull("Find method for 'AdverseEventTypeCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAdverseEventTypeCode(obj);
        Integer currentVersion = obj.getVersion();
        adverseEventTypeCodeRepository.flush();
        Assert.assertTrue("Version for 'AdverseEventTypeCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateAdverseEventTypeCodeUpdate() {
        AdverseEventTypeCode obj = dod.getRandomAdverseEventTypeCode();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to provide an identifier", id);
        obj = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        boolean modified =  dod.modifyAdverseEventTypeCode(obj);
        Integer currentVersion = obj.getVersion();
        AdverseEventTypeCode merged = (AdverseEventTypeCode)adverseEventTypeCodeService.updateAdverseEventTypeCode(obj);
        adverseEventTypeCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AdverseEventTypeCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveAdverseEventTypeCode() {
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", dod.getRandomAdverseEventTypeCode());
        AdverseEventTypeCode obj = dod.getNewTransientAdverseEventTypeCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AdverseEventTypeCode' identifier to be null", obj.getId());
        try {
            adverseEventTypeCodeService.saveAdverseEventTypeCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        adverseEventTypeCodeRepository.flush();
        Assert.assertNotNull("Expected 'AdverseEventTypeCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteAdverseEventTypeCode() {
        AdverseEventTypeCode obj = dod.getRandomAdverseEventTypeCode();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AdverseEventTypeCode' failed to provide an identifier", id);
        obj = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        adverseEventTypeCodeService.deleteAdverseEventTypeCode(obj);
        adverseEventTypeCodeRepository.flush();
        Assert.assertNull("Failed to remove 'AdverseEventTypeCode' with identifier '" + id + "'", adverseEventTypeCodeService.findAdverseEventTypeCode(id));
    }
}
