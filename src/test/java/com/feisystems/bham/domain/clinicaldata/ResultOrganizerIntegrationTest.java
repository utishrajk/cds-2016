package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.ResultOrganizerService;
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
public class ResultOrganizerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ResultOrganizerDataOnDemand dod;

	@Autowired
    ResultOrganizerService resultOrganizerService;

	@Autowired
    ResultOrganizerRepository resultOrganizerRepository;

	@Test
    public void testCountAllResultOrganizers() {
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", dod.getRandomResultOrganizer());
        long count = resultOrganizerService.countAllResultOrganizers();
        Assert.assertTrue("Counter for 'ResultOrganizer' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindResultOrganizer() {
        ResultOrganizer obj = dod.getRandomResultOrganizer();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to provide an identifier", id);
        obj = resultOrganizerService.findResultOrganizer(id);
        Assert.assertNotNull("Find method for 'ResultOrganizer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'ResultOrganizer' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllResultOrganizers() {
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", dod.getRandomResultOrganizer());
        long count = resultOrganizerService.countAllResultOrganizers();
        Assert.assertTrue("Too expensive to perform a find all test for 'ResultOrganizer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<ResultOrganizer> result = resultOrganizerService.findAllResultOrganizers();
        Assert.assertNotNull("Find all method for 'ResultOrganizer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'ResultOrganizer' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindResultOrganizerEntries() {
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", dod.getRandomResultOrganizer());
        long count = resultOrganizerService.countAllResultOrganizers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<ResultOrganizer> result = resultOrganizerService.findResultOrganizerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'ResultOrganizer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'ResultOrganizer' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        ResultOrganizer obj = dod.getRandomResultOrganizer();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to provide an identifier", id);
        obj = resultOrganizerService.findResultOrganizer(id);
        Assert.assertNotNull("Find method for 'ResultOrganizer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyResultOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        resultOrganizerRepository.flush();
        Assert.assertTrue("Version for 'ResultOrganizer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateResultOrganizerUpdate() {
        ResultOrganizer obj = dod.getRandomResultOrganizer();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to provide an identifier", id);
        obj = resultOrganizerService.findResultOrganizer(id);
        boolean modified =  dod.modifyResultOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        ResultOrganizer merged = resultOrganizerService.updateResultOrganizer(obj);
        resultOrganizerRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'ResultOrganizer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveResultOrganizer() {
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", dod.getRandomResultOrganizer());
        ResultOrganizer obj = dod.getNewTransientResultOrganizer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'ResultOrganizer' identifier to be null", obj.getId());
        try {
            resultOrganizerService.saveResultOrganizer(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        resultOrganizerRepository.flush();
        Assert.assertNotNull("Expected 'ResultOrganizer' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteResultOrganizer() {
        ResultOrganizer obj = dod.getRandomResultOrganizer();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'ResultOrganizer' failed to provide an identifier", id);
        obj = resultOrganizerService.findResultOrganizer(id);
        resultOrganizerService.deleteResultOrganizer(obj);
        resultOrganizerRepository.flush();
        Assert.assertNull("Failed to remove 'ResultOrganizer' with identifier '" + id + "'", resultOrganizerService.findResultOrganizer(id));
    }
}
