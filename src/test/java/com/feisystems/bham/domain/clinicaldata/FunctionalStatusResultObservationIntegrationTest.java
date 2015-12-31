package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultObservationService;
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
public class FunctionalStatusResultObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    FunctionalStatusResultObservationDataOnDemand dod;

	@Autowired
    FunctionalStatusResultObservationService functionalStatusResultObservationService;

	@Autowired
    FunctionalStatusResultObservationRepository functionalStatusResultObservationRepository;

	@Test
    public void testCountAllFunctionalStatusResultObservations() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", dod.getRandomFunctionalStatusResultObservation());
        long count = functionalStatusResultObservationService.countAllFunctionalStatusResultObservations();
        Assert.assertTrue("Counter for 'FunctionalStatusResultObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindFunctionalStatusResultObservation() {
        FunctionalStatusResultObservation obj = dod.getRandomFunctionalStatusResultObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to provide an identifier", id);
        obj = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusResultObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'FunctionalStatusResultObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllFunctionalStatusResultObservations() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", dod.getRandomFunctionalStatusResultObservation());
        long count = functionalStatusResultObservationService.countAllFunctionalStatusResultObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'FunctionalStatusResultObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<FunctionalStatusResultObservation> result = functionalStatusResultObservationService.findAllFunctionalStatusResultObservations();
        Assert.assertNotNull("Find all method for 'FunctionalStatusResultObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'FunctionalStatusResultObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindFunctionalStatusResultObservationEntries() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", dod.getRandomFunctionalStatusResultObservation());
        long count = functionalStatusResultObservationService.countAllFunctionalStatusResultObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<FunctionalStatusResultObservation> result = functionalStatusResultObservationService.findFunctionalStatusResultObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'FunctionalStatusResultObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'FunctionalStatusResultObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        FunctionalStatusResultObservation obj = dod.getRandomFunctionalStatusResultObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to provide an identifier", id);
        obj = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusResultObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyFunctionalStatusResultObservation(obj);
        Integer currentVersion = obj.getVersion();
        functionalStatusResultObservationRepository.flush();
        Assert.assertTrue("Version for 'FunctionalStatusResultObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateFunctionalStatusResultObservationUpdate() {
        FunctionalStatusResultObservation obj = dod.getRandomFunctionalStatusResultObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to provide an identifier", id);
        obj = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        boolean modified =  dod.modifyFunctionalStatusResultObservation(obj);
        Integer currentVersion = obj.getVersion();
        FunctionalStatusResultObservation merged = functionalStatusResultObservationService.updateFunctionalStatusResultObservation(obj);
        functionalStatusResultObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'FunctionalStatusResultObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveFunctionalStatusResultObservation() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", dod.getRandomFunctionalStatusResultObservation());
        FunctionalStatusResultObservation obj = dod.getNewTransientFunctionalStatusResultObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'FunctionalStatusResultObservation' identifier to be null", obj.getId());
        try {
            functionalStatusResultObservationService.saveFunctionalStatusResultObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        functionalStatusResultObservationRepository.flush();
        Assert.assertNotNull("Expected 'FunctionalStatusResultObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteFunctionalStatusResultObservation() {
        FunctionalStatusResultObservation obj = dod.getRandomFunctionalStatusResultObservation();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultObservation' failed to provide an identifier", id);
        obj = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        functionalStatusResultObservationService.deleteFunctionalStatusResultObservation(obj);
        functionalStatusResultObservationRepository.flush();
        Assert.assertNull("Failed to remove 'FunctionalStatusResultObservation' with identifier '" + id + "'", functionalStatusResultObservationService.findFunctionalStatusResultObservation(id));
    }
}
