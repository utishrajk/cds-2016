package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.ReasonForVisitService;
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
public class ReasonForVisitIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ReasonForVisitDataOnDemand dod;

	@Autowired
    ReasonForVisitService reasonForVisitService;

	@Autowired
    ReasonForVisitRepository reasonForVisitRepository;

	@Test
    public void testCountAllReasonForVisits() {
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", dod.getRandomReasonForVisit());
        long count = reasonForVisitService.countAllReasonForVisits();
        Assert.assertTrue("Counter for 'ReasonForVisit' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindReasonForVisit() {
        ReasonForVisit obj = dod.getRandomReasonForVisit();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to provide an identifier", id);
        obj = reasonForVisitService.findReasonForVisit(id);
        Assert.assertNotNull("Find method for 'ReasonForVisit' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'ReasonForVisit' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllReasonForVisits() {
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", dod.getRandomReasonForVisit());
        long count = reasonForVisitService.countAllReasonForVisits();
        Assert.assertTrue("Too expensive to perform a find all test for 'ReasonForVisit', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<ReasonForVisit> result = reasonForVisitService.findAllReasonForVisits();
        Assert.assertNotNull("Find all method for 'ReasonForVisit' illegally returned null", result);
        Assert.assertTrue("Find all method for 'ReasonForVisit' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindReasonForVisitEntries() {
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", dod.getRandomReasonForVisit());
        long count = reasonForVisitService.countAllReasonForVisits();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<ReasonForVisit> result = reasonForVisitService.findReasonForVisitEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'ReasonForVisit' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'ReasonForVisit' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        ReasonForVisit obj = dod.getRandomReasonForVisit();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to provide an identifier", id);
        obj = reasonForVisitService.findReasonForVisit(id);
        Assert.assertNotNull("Find method for 'ReasonForVisit' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyReasonForVisit(obj);
        Integer currentVersion = obj.getVersion();
        reasonForVisitRepository.flush();
        Assert.assertTrue("Version for 'ReasonForVisit' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateReasonForVisitUpdate() {
        ReasonForVisit obj = dod.getRandomReasonForVisit();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to provide an identifier", id);
        obj = reasonForVisitService.findReasonForVisit(id);
        boolean modified =  dod.modifyReasonForVisit(obj);
        Integer currentVersion = obj.getVersion();
        ReasonForVisit merged = reasonForVisitService.updateReasonForVisit(obj);
        reasonForVisitRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'ReasonForVisit' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveReasonForVisit() {
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", dod.getRandomReasonForVisit());
        ReasonForVisit obj = dod.getNewTransientReasonForVisit(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'ReasonForVisit' identifier to be null", obj.getId());
        try {
            reasonForVisitService.saveReasonForVisit(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        reasonForVisitRepository.flush();
        Assert.assertNotNull("Expected 'ReasonForVisit' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteReasonForVisit() {
        ReasonForVisit obj = dod.getRandomReasonForVisit();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ReasonForVisit' failed to provide an identifier", id);
        obj = reasonForVisitService.findReasonForVisit(id);
        reasonForVisitService.deleteReasonForVisit(obj);
        reasonForVisitRepository.flush();
        Assert.assertNull("Failed to remove 'ReasonForVisit' with identifier '" + id + "'", reasonForVisitService.findReasonForVisit(id));
    }
}
