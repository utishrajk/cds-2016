package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfSpecificRecommendationService;
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
public class UspstfSpecificRecommendationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    UspstfSpecificRecommendationDataOnDemand dod;

	@Autowired
    UspstfSpecificRecommendationService uspstfSpecificRecommendationService;

	@Autowired
    UspstfSpecificRecommendationRepository uspstfSpecificRecommendationRepository;

	@Test
    public void testCountAllUspstfSpecificRecommendations() {
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", dod.getRandomUspstfSpecificRecommendation());
        long count = uspstfSpecificRecommendationService.countAllUspstfSpecificRecommendations();
        Assert.assertTrue("Counter for 'UspstfSpecificRecommendation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindUspstfSpecificRecommendation() {
        UspstfSpecificRecommendation obj = dod.getRandomUspstfSpecificRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to provide an identifier", id);
        obj = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
        Assert.assertNotNull("Find method for 'UspstfSpecificRecommendation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UspstfSpecificRecommendation' returned the incorrect identifier", id, obj.getId_());
    }

	@Test
    public void testFindAllUspstfSpecificRecommendations() {
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", dod.getRandomUspstfSpecificRecommendation());
        long count = uspstfSpecificRecommendationService.countAllUspstfSpecificRecommendations();
        Assert.assertTrue("Too expensive to perform a find all test for 'UspstfSpecificRecommendation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UspstfSpecificRecommendation> result = uspstfSpecificRecommendationService.findAllUspstfSpecificRecommendations();
        Assert.assertNotNull("Find all method for 'UspstfSpecificRecommendation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UspstfSpecificRecommendation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindUspstfSpecificRecommendationEntries() {
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", dod.getRandomUspstfSpecificRecommendation());
        long count = uspstfSpecificRecommendationService.countAllUspstfSpecificRecommendations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UspstfSpecificRecommendation> result = uspstfSpecificRecommendationService.findUspstfSpecificRecommendationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UspstfSpecificRecommendation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UspstfSpecificRecommendation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        UspstfSpecificRecommendation obj = dod.getRandomUspstfSpecificRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to provide an identifier", id);
        obj = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
        Assert.assertNotNull("Find method for 'UspstfSpecificRecommendation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUspstfSpecificRecommendation(obj);
        Integer currentVersion = obj.getVersion();
        uspstfSpecificRecommendationRepository.flush();
        Assert.assertTrue("Version for 'UspstfSpecificRecommendation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateUspstfSpecificRecommendationUpdate() {
        UspstfSpecificRecommendation obj = dod.getRandomUspstfSpecificRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to provide an identifier", id);
        obj = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
        boolean modified =  dod.modifyUspstfSpecificRecommendation(obj);
        Integer currentVersion = obj.getVersion();
        UspstfSpecificRecommendation merged = uspstfSpecificRecommendationService.updateUspstfSpecificRecommendation(obj);
        uspstfSpecificRecommendationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId_(), id);
        Assert.assertTrue("Version for 'UspstfSpecificRecommendation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveUspstfSpecificRecommendation() {
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", dod.getRandomUspstfSpecificRecommendation());
        UspstfSpecificRecommendation obj = dod.getNewTransientUspstfSpecificRecommendation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UspstfSpecificRecommendation' identifier to be null", obj.getId_());
        try {
            uspstfSpecificRecommendationService.saveUspstfSpecificRecommendation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        uspstfSpecificRecommendationRepository.flush();
        Assert.assertNotNull("Expected 'UspstfSpecificRecommendation' identifier to no longer be null", obj.getId_());
    }

	@Test
    public void testDeleteUspstfSpecificRecommendation() {
        UspstfSpecificRecommendation obj = dod.getRandomUspstfSpecificRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'UspstfSpecificRecommendation' failed to provide an identifier", id);
        obj = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id);
        uspstfSpecificRecommendationService.deleteUspstfSpecificRecommendation(obj);
        uspstfSpecificRecommendationRepository.flush();
        Assert.assertNull("Failed to remove 'UspstfSpecificRecommendation' with identifier '" + id + "'", uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id));
    }
}
