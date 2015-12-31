package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.VitalSignObservationService;
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
public class VitalSignObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    VitalSignObservationDataOnDemand dod;

	@Autowired
    VitalSignObservationService vitalSignObservationService;

	@Autowired
    VitalObservationRepository vitalObservationRepository;

	@Test
    public void testCountAllVitalSignObservations() {
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", dod.getRandomVitalSignObservation());
        long count = vitalSignObservationService.countAllVitalSignObservations();
        Assert.assertTrue("Counter for 'VitalSignObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindVitalSignObservation() {
        VitalSignObservation obj = dod.getRandomVitalSignObservation();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to provide an identifier", id);
        obj = vitalSignObservationService.findVitalSignObservation(id);
        Assert.assertNotNull("Find method for 'VitalSignObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'VitalSignObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllVitalSignObservations() {
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", dod.getRandomVitalSignObservation());
        long count = vitalSignObservationService.countAllVitalSignObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'VitalSignObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<VitalSignObservation> result = vitalSignObservationService.findAllVitalSignObservations();
        Assert.assertNotNull("Find all method for 'VitalSignObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'VitalSignObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindVitalSignObservationEntries() {
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", dod.getRandomVitalSignObservation());
        long count = vitalSignObservationService.countAllVitalSignObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<VitalSignObservation> result = vitalSignObservationService.findVitalSignObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'VitalSignObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'VitalSignObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        VitalSignObservation obj = dod.getRandomVitalSignObservation();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to provide an identifier", id);
        obj = vitalSignObservationService.findVitalSignObservation(id);
        Assert.assertNotNull("Find method for 'VitalSignObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyVitalSignObservation(obj);
        Integer currentVersion = obj.getVersion();
        vitalObservationRepository.flush();
        Assert.assertTrue("Version for 'VitalSignObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateVitalSignObservationUpdate() {
        VitalSignObservation obj = dod.getRandomVitalSignObservation();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to provide an identifier", id);
        obj = vitalSignObservationService.findVitalSignObservation(id);
        boolean modified =  dod.modifyVitalSignObservation(obj);
        Integer currentVersion = obj.getVersion();
        VitalSignObservation merged = vitalSignObservationService.updateVitalSignObservation(obj);
        vitalObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'VitalSignObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveVitalSignObservation() {
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", dod.getRandomVitalSignObservation());
        VitalSignObservation obj = dod.getNewTransientVitalSignObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'VitalSignObservation' identifier to be null", obj.getId());
        try {
            vitalSignObservationService.saveVitalSignObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        vitalObservationRepository.flush();
        Assert.assertNotNull("Expected 'VitalSignObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteVitalSignObservation() {
        VitalSignObservation obj = dod.getRandomVitalSignObservation();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'VitalSignObservation' failed to provide an identifier", id);
        obj = vitalSignObservationService.findVitalSignObservation(id);
        vitalSignObservationService.deleteVitalSignObservation(obj);
        vitalObservationRepository.flush();
        Assert.assertNull("Failed to remove 'VitalSignObservation' with identifier '" + id + "'", vitalSignObservationService.findVitalSignObservation(id));
    }
}
