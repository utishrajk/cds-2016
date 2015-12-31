package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.EncounterService;
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
public class EncounterIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    EncounterDataOnDemand dod;

	@Autowired
    EncounterService encounterService;

	@Autowired
    EncounterRepository encounterRepository;

	@Test
    public void testCountAllEncounters() {
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", dod.getRandomEncounter());
        long count = encounterService.countAllEncounters();
        Assert.assertTrue("Counter for 'Encounter' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindEncounter() {
        Encounter obj = dod.getRandomEncounter();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to provide an identifier", id);
        obj = encounterService.findEncounter(id);
        Assert.assertNotNull("Find method for 'Encounter' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Encounter' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllEncounters() {
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", dod.getRandomEncounter());
        long count = encounterService.countAllEncounters();
        Assert.assertTrue("Too expensive to perform a find all test for 'Encounter', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Encounter> result = encounterService.findAllEncounters();
        Assert.assertNotNull("Find all method for 'Encounter' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Encounter' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEncounterEntries() {
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", dod.getRandomEncounter());
        long count = encounterService.countAllEncounters();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Encounter> result = encounterService.findEncounterEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Encounter' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Encounter' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Encounter obj = dod.getRandomEncounter();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to provide an identifier", id);
        obj = encounterService.findEncounter(id);
        Assert.assertNotNull("Find method for 'Encounter' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEncounter(obj);
        Integer currentVersion = obj.getVersion();
        encounterRepository.flush();
        Assert.assertTrue("Version for 'Encounter' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateEncounterUpdate() {
        Encounter obj = dod.getRandomEncounter();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to provide an identifier", id);
        obj = encounterService.findEncounter(id);
        boolean modified =  dod.modifyEncounter(obj);
        Integer currentVersion = obj.getVersion();
        Encounter merged = encounterService.updateEncounter(obj);
        encounterRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Encounter' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveEncounter() {
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", dod.getRandomEncounter());
        Encounter obj = dod.getNewTransientEncounter(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Encounter' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Encounter' identifier to be null", obj.getId());
        try {
            encounterService.saveEncounter(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        encounterRepository.flush();
        Assert.assertNotNull("Expected 'Encounter' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteEncounter() {
        Encounter obj = dod.getRandomEncounter();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Encounter' failed to provide an identifier", id);
        obj = encounterService.findEncounter(id);
        encounterService.deleteEncounter(obj);
        encounterRepository.flush();
        Assert.assertNull("Failed to remove 'Encounter' with identifier '" + id + "'", encounterService.findEncounter(id));
    }
}
