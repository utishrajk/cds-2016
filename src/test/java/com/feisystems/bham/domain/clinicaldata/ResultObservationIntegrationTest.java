package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.ResultObservationService;
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
public class ResultObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ResultObservationDataOnDemand dod;

	@Autowired
    ResultObservationService resultObservationService;

	@Autowired
    ResultObservationRepository resultObservationRepository;

	@Test
    public void testCountAllResultObservations() {
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", dod.getRandomResultObservation());
        long count = resultObservationService.countAllResultObservations();
        Assert.assertTrue("Counter for 'ResultObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindResultObservation() {
        ResultObservation obj = dod.getRandomResultObservation();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to provide an identifier", id);
        obj = resultObservationService.findResultObservation(id);
        Assert.assertNotNull("Find method for 'ResultObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'ResultObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllResultObservations() {
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", dod.getRandomResultObservation());
        long count = resultObservationService.countAllResultObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'ResultObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<ResultObservation> result = resultObservationService.findAllResultObservations();
        Assert.assertNotNull("Find all method for 'ResultObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'ResultObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindResultObservationEntries() {
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", dod.getRandomResultObservation());
        long count = resultObservationService.countAllResultObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<ResultObservation> result = resultObservationService.findResultObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'ResultObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'ResultObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        ResultObservation obj = dod.getRandomResultObservation();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to provide an identifier", id);
        obj = resultObservationService.findResultObservation(id);
        Assert.assertNotNull("Find method for 'ResultObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyResultObservation(obj);
        Integer currentVersion = obj.getVersion();
        resultObservationRepository.flush();
        Assert.assertTrue("Version for 'ResultObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateResultObservationUpdate() {
        ResultObservation obj = dod.getRandomResultObservation();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to provide an identifier", id);
        obj = resultObservationService.findResultObservation(id);
        boolean modified =  dod.modifyResultObservation(obj);
        Integer currentVersion = obj.getVersion();
        ResultObservation merged = resultObservationService.updateResultObservation(obj);
        resultObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'ResultObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveResultObservation() {
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", dod.getRandomResultObservation());
        ResultObservation obj = dod.getNewTransientResultObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'ResultObservation' identifier to be null", obj.getId());
        try {
            resultObservationService.saveResultObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        resultObservationRepository.flush();
        Assert.assertNotNull("Expected 'ResultObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteResultObservation() {
        ResultObservation obj = dod.getRandomResultObservation();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultObservation' failed to provide an identifier", id);
        obj = resultObservationService.findResultObservation(id);
        resultObservationService.deleteResultObservation(obj);
        resultObservationRepository.flush();
        Assert.assertNull("Failed to remove 'ResultObservation' with identifier '" + id + "'", resultObservationService.findResultObservation(id));
    }
}
