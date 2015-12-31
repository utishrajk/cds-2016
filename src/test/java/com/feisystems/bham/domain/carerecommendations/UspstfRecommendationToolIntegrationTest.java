package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfRecommendationToolService;
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
public class UspstfRecommendationToolIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    UspstfRecommendationToolDataOnDemand dod;

	@Autowired
    UspstfRecommendationToolService uspstfRecommendationToolService;

	@Autowired
    UspstfRecommendationToolRepository uspstfRecommendationToolRepository;

	@Test
    public void testCountAllUspstfRecommendationTools() {
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", dod.getRandomUspstfRecommendationTool());
        long count = uspstfRecommendationToolService.countAllUspstfRecommendationTools();
        Assert.assertTrue("Counter for 'UspstfRecommendationTool' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindUspstfRecommendationTool() {
        UspstfRecommendationTool obj = dod.getRandomUspstfRecommendationTool();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to provide an identifier", id);
        obj = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        Assert.assertNotNull("Find method for 'UspstfRecommendationTool' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UspstfRecommendationTool' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllUspstfRecommendationTools() {
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", dod.getRandomUspstfRecommendationTool());
        long count = uspstfRecommendationToolService.countAllUspstfRecommendationTools();
        Assert.assertTrue("Too expensive to perform a find all test for 'UspstfRecommendationTool', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UspstfRecommendationTool> result = uspstfRecommendationToolService.findAllUspstfRecommendationTools();
        Assert.assertNotNull("Find all method for 'UspstfRecommendationTool' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UspstfRecommendationTool' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindUspstfRecommendationToolEntries() {
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", dod.getRandomUspstfRecommendationTool());
        long count = uspstfRecommendationToolService.countAllUspstfRecommendationTools();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UspstfRecommendationTool> result = uspstfRecommendationToolService.findUspstfRecommendationToolEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UspstfRecommendationTool' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UspstfRecommendationTool' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        UspstfRecommendationTool obj = dod.getRandomUspstfRecommendationTool();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to provide an identifier", id);
        obj = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        Assert.assertNotNull("Find method for 'UspstfRecommendationTool' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUspstfRecommendationTool(obj);
        Integer currentVersion = obj.getVersion();
        uspstfRecommendationToolRepository.flush();
        Assert.assertTrue("Version for 'UspstfRecommendationTool' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateUspstfRecommendationToolUpdate() {
        UspstfRecommendationTool obj = dod.getRandomUspstfRecommendationTool();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to provide an identifier", id);
        obj = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        boolean modified =  dod.modifyUspstfRecommendationTool(obj);
        Integer currentVersion = obj.getVersion();
        UspstfRecommendationTool merged = uspstfRecommendationToolService.updateUspstfRecommendationTool(obj);
        uspstfRecommendationToolRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'UspstfRecommendationTool' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveUspstfRecommendationTool() {
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", dod.getRandomUspstfRecommendationTool());
        UspstfRecommendationTool obj = dod.getNewTransientUspstfRecommendationTool(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UspstfRecommendationTool' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'UspstfRecommendationTool' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteUspstfRecommendationTool() {
        UspstfRecommendationTool obj = dod.getRandomUspstfRecommendationTool();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfRecommendationTool' failed to provide an identifier", id);
        obj = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        uspstfRecommendationToolService.deleteUspstfRecommendationTool(obj);
        uspstfRecommendationToolRepository.flush();
        Assert.assertNull("Failed to remove 'UspstfRecommendationTool' with identifier '" + id + "'", uspstfRecommendationToolService.findUspstfRecommendationTool(id));
    }
}
