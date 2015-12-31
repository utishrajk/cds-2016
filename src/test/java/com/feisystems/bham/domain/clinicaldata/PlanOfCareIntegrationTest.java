package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.PlanOfCareService;
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
public class PlanOfCareIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PlanOfCareDataOnDemand dod;

	@Autowired
    PlanOfCareService planOfCareService;

	@Autowired
    PlanOfCareRepository planOfCareRepository;

	@Test
    public void testCountAllPlanOfCares() {
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", dod.getRandomPlanOfCare());
        long count = planOfCareService.countAllPlanOfCares();
        Assert.assertTrue("Counter for 'PlanOfCare' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindPlanOfCare() {
        PlanOfCare obj = dod.getRandomPlanOfCare();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to provide an identifier", id);
        obj = planOfCareService.findPlanOfCare(id);
        Assert.assertNotNull("Find method for 'PlanOfCare' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'PlanOfCare' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllPlanOfCares() {
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", dod.getRandomPlanOfCare());
        long count = planOfCareService.countAllPlanOfCares();
        Assert.assertTrue("Too expensive to perform a find all test for 'PlanOfCare', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<PlanOfCare> result = planOfCareService.findAllPlanOfCares();
        Assert.assertNotNull("Find all method for 'PlanOfCare' illegally returned null", result);
        Assert.assertTrue("Find all method for 'PlanOfCare' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindPlanOfCareEntries() {
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", dod.getRandomPlanOfCare());
        long count = planOfCareService.countAllPlanOfCares();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<PlanOfCare> result = planOfCareService.findPlanOfCareEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'PlanOfCare' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'PlanOfCare' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        PlanOfCare obj = dod.getRandomPlanOfCare();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to provide an identifier", id);
        obj = planOfCareService.findPlanOfCare(id);
        Assert.assertNotNull("Find method for 'PlanOfCare' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPlanOfCare(obj);
        Integer currentVersion = obj.getVersion();
        planOfCareRepository.flush();
        Assert.assertTrue("Version for 'PlanOfCare' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdatePlanOfCareUpdate() {
        PlanOfCare obj = dod.getRandomPlanOfCare();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to provide an identifier", id);
        obj = planOfCareService.findPlanOfCare(id);
        boolean modified =  dod.modifyPlanOfCare(obj);
        Integer currentVersion = obj.getVersion();
        PlanOfCare merged = planOfCareService.updatePlanOfCare(obj);
        planOfCareRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'PlanOfCare' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePlanOfCare() {
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", dod.getRandomPlanOfCare());
        PlanOfCare obj = dod.getNewTransientPlanOfCare(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'PlanOfCare' identifier to be null", obj.getId());
        try {
            planOfCareService.savePlanOfCare(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        planOfCareRepository.flush();
        Assert.assertNotNull("Expected 'PlanOfCare' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePlanOfCare() {
        PlanOfCare obj = dod.getRandomPlanOfCare();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PlanOfCare' failed to provide an identifier", id);
        obj = planOfCareService.findPlanOfCare(id);
        planOfCareService.deletePlanOfCare(obj);
        planOfCareRepository.flush();
        Assert.assertNull("Failed to remove 'PlanOfCare' with identifier '" + id + "'", planOfCareService.findPlanOfCare(id));
    }
}
