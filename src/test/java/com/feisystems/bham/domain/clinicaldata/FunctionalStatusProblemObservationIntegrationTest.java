package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusProblemObservationService;
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
public class FunctionalStatusProblemObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    FunctionalStatusProblemObservationDataOnDemand dod;

	@Autowired
    FunctionalStatusProblemObservationService functionalStatusProblemObservationService;

	@Autowired
    FunctionalStatusProblemObservationRepository functionalStatusProblemObservationRepository;

	@Test
    public void testCountAllFunctionalStatusProblemObservations() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", dod.getRandomFunctionalStatusProblemObservation());
        long count = functionalStatusProblemObservationService.countAllFunctionalStatusProblemObservations();
        Assert.assertTrue("Counter for 'FunctionalStatusProblemObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindFunctionalStatusProblemObservation() {
        FunctionalStatusProblemObservation obj = dod.getRandomFunctionalStatusProblemObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to provide an identifier", id);
        obj = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusProblemObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'FunctionalStatusProblemObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllFunctionalStatusProblemObservations() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", dod.getRandomFunctionalStatusProblemObservation());
        long count = functionalStatusProblemObservationService.countAllFunctionalStatusProblemObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'FunctionalStatusProblemObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<FunctionalStatusProblemObservation> result = functionalStatusProblemObservationService.findAllFunctionalStatusProblemObservations();
        Assert.assertNotNull("Find all method for 'FunctionalStatusProblemObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'FunctionalStatusProblemObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindFunctionalStatusProblemObservationEntries() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", dod.getRandomFunctionalStatusProblemObservation());
        long count = functionalStatusProblemObservationService.countAllFunctionalStatusProblemObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<FunctionalStatusProblemObservation> result = functionalStatusProblemObservationService.findFunctionalStatusProblemObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'FunctionalStatusProblemObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'FunctionalStatusProblemObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        FunctionalStatusProblemObservation obj = dod.getRandomFunctionalStatusProblemObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to provide an identifier", id);
        obj = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusProblemObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyFunctionalStatusProblemObservation(obj);
        Integer currentVersion = obj.getVersion();
        functionalStatusProblemObservationRepository.flush();
        Assert.assertTrue("Version for 'FunctionalStatusProblemObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateFunctionalStatusProblemObservationUpdate() {
        FunctionalStatusProblemObservation obj = dod.getRandomFunctionalStatusProblemObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to provide an identifier", id);
        obj = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        boolean modified =  dod.modifyFunctionalStatusProblemObservation(obj);
        Integer currentVersion = obj.getVersion();
        FunctionalStatusProblemObservation merged = functionalStatusProblemObservationService.updateFunctionalStatusProblemObservation(obj);
        functionalStatusProblemObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'FunctionalStatusProblemObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveFunctionalStatusProblemObservation() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", dod.getRandomFunctionalStatusProblemObservation());
        FunctionalStatusProblemObservation obj = dod.getNewTransientFunctionalStatusProblemObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'FunctionalStatusProblemObservation' identifier to be null", obj.getId());
        try {
            functionalStatusProblemObservationService.saveFunctionalStatusProblemObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        functionalStatusProblemObservationRepository.flush();
        Assert.assertNotNull("Expected 'FunctionalStatusProblemObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteFunctionalStatusProblemObservation() {
        FunctionalStatusProblemObservation obj = dod.getRandomFunctionalStatusProblemObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusProblemObservation' failed to provide an identifier", id);
        obj = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        functionalStatusProblemObservationService.deleteFunctionalStatusProblemObservation(obj);
        functionalStatusProblemObservationRepository.flush();
        Assert.assertNull("Failed to remove 'FunctionalStatusProblemObservation' with identifier '" + id + "'", functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id));
    }
}
