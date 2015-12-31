package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.PatientGoalService;
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
public class PatientGoalIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PatientGoalDataOnDemand dod;

	@Autowired
    PatientGoalService patientGoalService;

	@Autowired
    PatientGoalRepository patientGoalRepository;

	@Test
    public void testCountAllPatientGoals() {
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", dod.getRandomPatientGoal());
        long count = patientGoalService.countAllPatientGoals();
        Assert.assertTrue("Counter for 'PatientGoal' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindPatientGoal() {
        PatientGoal obj = dod.getRandomPatientGoal();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to provide an identifier", id);
        obj = patientGoalService.findPatientGoal(id);
        Assert.assertNotNull("Find method for 'PatientGoal' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'PatientGoal' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllPatientGoals() {
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", dod.getRandomPatientGoal());
        long count = patientGoalService.countAllPatientGoals();
        Assert.assertTrue("Too expensive to perform a find all test for 'PatientGoal', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<PatientGoal> result = patientGoalService.findAllPatientGoals();
        Assert.assertNotNull("Find all method for 'PatientGoal' illegally returned null", result);
        Assert.assertTrue("Find all method for 'PatientGoal' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindPatientGoalEntries() {
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", dod.getRandomPatientGoal());
        long count = patientGoalService.countAllPatientGoals();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<PatientGoal> result = patientGoalService.findPatientGoalEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'PatientGoal' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'PatientGoal' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        PatientGoal obj = dod.getRandomPatientGoal();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to provide an identifier", id);
        obj = patientGoalService.findPatientGoal(id);
        Assert.assertNotNull("Find method for 'PatientGoal' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPatientGoal(obj);
        Integer currentVersion = obj.getVersion();
        patientGoalRepository.flush();
        Assert.assertTrue("Version for 'PatientGoal' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdatePatientGoalUpdate() {
        PatientGoal obj = dod.getRandomPatientGoal();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to provide an identifier", id);
        obj = patientGoalService.findPatientGoal(id);
        boolean modified =  dod.modifyPatientGoal(obj);
        Integer currentVersion = obj.getVersion();
        PatientGoal merged = patientGoalService.updatePatientGoal(obj);
        patientGoalRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'PatientGoal' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePatientGoal() {
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", dod.getRandomPatientGoal());
        PatientGoal obj = dod.getNewTransientPatientGoal(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'PatientGoal' identifier to be null", obj.getId());
        try {
            patientGoalService.savePatientGoal(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        patientGoalRepository.flush();
        Assert.assertNotNull("Expected 'PatientGoal' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePatientGoal() {
        PatientGoal obj = dod.getRandomPatientGoal();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientGoal' failed to provide an identifier", id);
        obj = patientGoalService.findPatientGoal(id);
        patientGoalService.deletePatientGoal(obj);
        patientGoalRepository.flush();
        Assert.assertNull("Failed to remove 'PatientGoal' with identifier '" + id + "'", patientGoalService.findPatientGoal(id));
    }
}
