package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.ActStatusCodeService;
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
public class ActStatusCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ActStatusCodeDataOnDemand dod;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    ActStatusCodeRepository actStatusCodeRepository;

	@Test
    public void testCountAllActStatusCodes() {
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", dod.getRandomActStatusCode());
        long count = actStatusCodeService.countAllActStatusCodes();
        Assert.assertTrue("Counter for 'ActStatusCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindActStatusCode() {
        ActStatusCode obj = dod.getRandomActStatusCode();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to provide an identifier", id);
        obj = actStatusCodeService.findActStatusCode(id);
        Assert.assertNotNull("Find method for 'ActStatusCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'ActStatusCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllActStatusCodes() {
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", dod.getRandomActStatusCode());
        long count = actStatusCodeService.countAllActStatusCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'ActStatusCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<ActStatusCode> result = actStatusCodeService.findAllActStatusCodes();
        Assert.assertNotNull("Find all method for 'ActStatusCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'ActStatusCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindActStatusCodeEntries() {
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", dod.getRandomActStatusCode());
        long count = actStatusCodeService.countAllActStatusCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<ActStatusCode> result = actStatusCodeService.findActStatusCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'ActStatusCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'ActStatusCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        ActStatusCode obj = dod.getRandomActStatusCode();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to provide an identifier", id);
        obj = actStatusCodeService.findActStatusCode(id);
        Assert.assertNotNull("Find method for 'ActStatusCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyActStatusCode(obj);
        Integer currentVersion = obj.getVersion();
        actStatusCodeRepository.flush();
        Assert.assertTrue("Version for 'ActStatusCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateActStatusCodeUpdate() {
        ActStatusCode obj = dod.getRandomActStatusCode();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to provide an identifier", id);
        obj = actStatusCodeService.findActStatusCode(id);
        boolean modified =  dod.modifyActStatusCode(obj);
        Integer currentVersion = obj.getVersion();
        ActStatusCode merged = (ActStatusCode)actStatusCodeService.updateActStatusCode(obj);
        actStatusCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'ActStatusCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveActStatusCode() {
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to initialize correctly", dod.getRandomActStatusCode());
        ActStatusCode obj = dod.getNewTransientActStatusCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'ActStatusCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'ActStatusCode' identifier to be null", obj.getId());
        try {
            actStatusCodeService.saveActStatusCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        actStatusCodeRepository.flush();
        Assert.assertNotNull("Expected 'ActStatusCode' identifier to no longer be null", obj.getId());
    }

}
