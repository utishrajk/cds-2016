package com.feisystems.bham.domain.carerecommendations;
import com.feisystems.bham.service.carerecommendations.UspstfGeneralRecommendationService;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class UspstfGeneralRecommendationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    UspstfGeneralRecommendationDataOnDemand dod;

	@Autowired
    UspstfGeneralRecommendationService uspstfGeneralRecommendationService;

	@Autowired
    UspstfGeneralRecommendationRepository uspstfGeneralRecommendationRepository;

	@Test
    public void testCountAllUspstfGeneralRecommendations() {
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", dod.getRandomUspstfGeneralRecommendation());
        long count = uspstfGeneralRecommendationService.countAllUspstfGeneralRecommendations();
        Assert.assertTrue("Counter for 'UspstfGeneralRecommendation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindUspstfGeneralRecommendation() {
        UspstfGeneralRecommendation obj = dod.getRandomUspstfGeneralRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to provide an identifier", id);
        obj = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        Assert.assertNotNull("Find method for 'UspstfGeneralRecommendation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UspstfGeneralRecommendation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllUspstfGeneralRecommendations() {
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", dod.getRandomUspstfGeneralRecommendation());
        long count = uspstfGeneralRecommendationService.countAllUspstfGeneralRecommendations();
        Assert.assertTrue("Too expensive to perform a find all test for 'UspstfGeneralRecommendation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UspstfGeneralRecommendation> result = uspstfGeneralRecommendationService.findAllUspstfGeneralRecommendations();
        Assert.assertNotNull("Find all method for 'UspstfGeneralRecommendation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UspstfGeneralRecommendation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindUspstfGeneralRecommendationEntries() {
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", dod.getRandomUspstfGeneralRecommendation());
        long count = uspstfGeneralRecommendationService.countAllUspstfGeneralRecommendations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UspstfGeneralRecommendation> result = uspstfGeneralRecommendationService.findUspstfGeneralRecommendationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UspstfGeneralRecommendation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UspstfGeneralRecommendation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        UspstfGeneralRecommendation obj = dod.getRandomUspstfGeneralRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to provide an identifier", id);
        obj = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        Assert.assertNotNull("Find method for 'UspstfGeneralRecommendation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUspstfGeneralRecommendation(obj);
        Integer currentVersion = obj.getVersion();
        uspstfGeneralRecommendationRepository.flush();
        Assert.assertTrue("Version for 'UspstfGeneralRecommendation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateUspstfGeneralRecommendationUpdate() {
        UspstfGeneralRecommendation obj = dod.getRandomUspstfGeneralRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to provide an identifier", id);
        obj = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        boolean modified =  dod.modifyUspstfGeneralRecommendation(obj);
        Integer currentVersion = obj.getVersion();
        UspstfGeneralRecommendation merged = uspstfGeneralRecommendationService.updateUspstfGeneralRecommendation(obj);
        uspstfGeneralRecommendationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'UspstfGeneralRecommendation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveUspstfGeneralRecommendation() {
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", dod.getRandomUspstfGeneralRecommendation());
        UspstfGeneralRecommendation obj = dod.getNewTransientUspstfGeneralRecommendation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UspstfGeneralRecommendation' identifier to be null", obj.getId());
        try {
            uspstfGeneralRecommendationService.saveUspstfGeneralRecommendation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        uspstfGeneralRecommendationRepository.flush();
        Assert.assertNotNull("Expected 'UspstfGeneralRecommendation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteUspstfGeneralRecommendation() {
        UspstfGeneralRecommendation obj = dod.getRandomUspstfGeneralRecommendation();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UspstfGeneralRecommendation' failed to provide an identifier", id);
        obj = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        uspstfGeneralRecommendationService.deleteUspstfGeneralRecommendation(obj);
        uspstfGeneralRecommendationRepository.flush();
        Assert.assertNull("Failed to remove 'UspstfGeneralRecommendation' with identifier '" + id + "'", uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id));
    }
}
