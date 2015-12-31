package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultOrganizerService;
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
public class FunctionalStatusResultOrganizerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    FunctionalStatusResultOrganizerDataOnDemand dod;

	@Autowired
    FunctionalStatusResultOrganizerService functionalStatusResultOrganizerService;

	@Autowired
    FunctionalStatusResultOrganizerRepository functionalStatusResultOrganizerRepository;

	@Test
    public void testCountAllFunctionalStatusResultOrganizers() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", dod.getRandomFunctionalStatusResultOrganizer());
        long count = functionalStatusResultOrganizerService.countAllFunctionalStatusResultOrganizers();
        Assert.assertTrue("Counter for 'FunctionalStatusResultOrganizer' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindFunctionalStatusResultOrganizer() {
        FunctionalStatusResultOrganizer obj = dod.getRandomFunctionalStatusResultOrganizer();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to provide an identifier", id);
        obj = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusResultOrganizer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'FunctionalStatusResultOrganizer' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllFunctionalStatusResultOrganizers() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", dod.getRandomFunctionalStatusResultOrganizer());
        long count = functionalStatusResultOrganizerService.countAllFunctionalStatusResultOrganizers();
        Assert.assertTrue("Too expensive to perform a find all test for 'FunctionalStatusResultOrganizer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<FunctionalStatusResultOrganizer> result = functionalStatusResultOrganizerService.findAllFunctionalStatusResultOrganizers();
        Assert.assertNotNull("Find all method for 'FunctionalStatusResultOrganizer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'FunctionalStatusResultOrganizer' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindFunctionalStatusResultOrganizerEntries() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", dod.getRandomFunctionalStatusResultOrganizer());
        long count = functionalStatusResultOrganizerService.countAllFunctionalStatusResultOrganizers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<FunctionalStatusResultOrganizer> result = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'FunctionalStatusResultOrganizer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'FunctionalStatusResultOrganizer' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        FunctionalStatusResultOrganizer obj = dod.getRandomFunctionalStatusResultOrganizer();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to provide an identifier", id);
        obj = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        Assert.assertNotNull("Find method for 'FunctionalStatusResultOrganizer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyFunctionalStatusResultOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        functionalStatusResultOrganizerRepository.flush();
        Assert.assertTrue("Version for 'FunctionalStatusResultOrganizer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateFunctionalStatusResultOrganizerUpdate() {
        FunctionalStatusResultOrganizer obj = dod.getRandomFunctionalStatusResultOrganizer();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to provide an identifier", id);
        obj = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        boolean modified =  dod.modifyFunctionalStatusResultOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        FunctionalStatusResultOrganizer merged = functionalStatusResultOrganizerService.updateFunctionalStatusResultOrganizer(obj);
        functionalStatusResultOrganizerRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'FunctionalStatusResultOrganizer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveFunctionalStatusResultOrganizer() {
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", dod.getRandomFunctionalStatusResultOrganizer());
        FunctionalStatusResultOrganizer obj = dod.getNewTransientFunctionalStatusResultOrganizer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'FunctionalStatusResultOrganizer' identifier to be null", obj.getId());
        try {
            functionalStatusResultOrganizerService.saveFunctionalStatusResultOrganizer(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        functionalStatusResultOrganizerRepository.flush();
        Assert.assertNotNull("Expected 'FunctionalStatusResultOrganizer' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteFunctionalStatusResultOrganizer() {
        FunctionalStatusResultOrganizer obj = dod.getRandomFunctionalStatusResultOrganizer();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'FunctionalStatusResultOrganizer' failed to provide an identifier", id);
        obj = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        functionalStatusResultOrganizerService.deleteFunctionalStatusResultOrganizer(obj);
        functionalStatusResultOrganizerRepository.flush();
        Assert.assertNull("Failed to remove 'FunctionalStatusResultOrganizer' with identifier '" + id + "'", functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id));
    }
}
