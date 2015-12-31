package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.VitalSignOrganizerService;
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
public class VitalSignOrganizerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    VitalSignOrganizerDataOnDemand dod;

	@Autowired
    VitalSignOrganizerService vitalSignOrganizerService;

	@Autowired
    VitalSignOrganizerRepository vitalSignOrganizerRepository;

	@Test
    public void testCountAllVitalSignOrganizers() {
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", dod.getRandomVitalSignOrganizer());
        long count = vitalSignOrganizerService.countAllVitalSignOrganizers();
        Assert.assertTrue("Counter for 'VitalSignOrganizer' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindVitalSignOrganizer() {
        VitalSignOrganizer obj = dod.getRandomVitalSignOrganizer();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to provide an identifier", id);
        obj = vitalSignOrganizerService.findVitalSignOrganizer(id);
        Assert.assertNotNull("Find method for 'VitalSignOrganizer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'VitalSignOrganizer' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllVitalSignOrganizers() {
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", dod.getRandomVitalSignOrganizer());
        long count = vitalSignOrganizerService.countAllVitalSignOrganizers();
        Assert.assertTrue("Too expensive to perform a find all test for 'VitalSignOrganizer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<VitalSignOrganizer> result = vitalSignOrganizerService.findAllVitalSignOrganizers();
        Assert.assertNotNull("Find all method for 'VitalSignOrganizer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'VitalSignOrganizer' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindVitalSignOrganizerEntries() {
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", dod.getRandomVitalSignOrganizer());
        long count = vitalSignOrganizerService.countAllVitalSignOrganizers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<VitalSignOrganizer> result = vitalSignOrganizerService.findVitalSignOrganizerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'VitalSignOrganizer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'VitalSignOrganizer' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        VitalSignOrganizer obj = dod.getRandomVitalSignOrganizer();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to provide an identifier", id);
        obj = vitalSignOrganizerService.findVitalSignOrganizer(id);
        Assert.assertNotNull("Find method for 'VitalSignOrganizer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyVitalSignOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        vitalSignOrganizerRepository.flush();
        Assert.assertTrue("Version for 'VitalSignOrganizer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateVitalSignOrganizerUpdate() {
        VitalSignOrganizer obj = dod.getRandomVitalSignOrganizer();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to provide an identifier", id);
        obj = vitalSignOrganizerService.findVitalSignOrganizer(id);
        boolean modified =  dod.modifyVitalSignOrganizer(obj);
        Integer currentVersion = obj.getVersion();
        VitalSignOrganizer merged = vitalSignOrganizerService.updateVitalSignOrganizer(obj);
        vitalSignOrganizerRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'VitalSignOrganizer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveVitalSignOrganizer() {
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", dod.getRandomVitalSignOrganizer());
        VitalSignOrganizer obj = dod.getNewTransientVitalSignOrganizer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'VitalSignOrganizer' identifier to be null", obj.getId());
        try {
            vitalSignOrganizerService.saveVitalSignOrganizer(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        vitalSignOrganizerRepository.flush();
        Assert.assertNotNull("Expected 'VitalSignOrganizer' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteVitalSignOrganizer() {
        VitalSignOrganizer obj = dod.getRandomVitalSignOrganizer();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignOrganizer' failed to provide an identifier", id);
        obj = vitalSignOrganizerService.findVitalSignOrganizer(id);
        vitalSignOrganizerService.deleteVitalSignOrganizer(obj);
        vitalSignOrganizerRepository.flush();
        Assert.assertNull("Failed to remove 'VitalSignOrganizer' with identifier '" + id + "'", vitalSignOrganizerService.findVitalSignOrganizer(id));
    }
}
