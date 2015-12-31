package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.HospitalDischargeInstructionService;
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
public class HospitalDischargeInstructionIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    HospitalDischargeInstructionDataOnDemand dod;

	@Autowired
    HospitalDischargeInstructionService hospitalDischargeInstructionService;

	@Autowired
    HospitalDischargeInstructionRepository hospitalDischargeInstructionRepository;

	@Test
    public void testCountAllHospitalDischargeInstructions() {
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", dod.getRandomHospitalDischargeInstruction());
        long count = hospitalDischargeInstructionService.countAllHospitalDischargeInstructions();
        Assert.assertTrue("Counter for 'HospitalDischargeInstruction' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindHospitalDischargeInstruction() {
        HospitalDischargeInstruction obj = dod.getRandomHospitalDischargeInstruction();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to provide an identifier", id);
        obj = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        Assert.assertNotNull("Find method for 'HospitalDischargeInstruction' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'HospitalDischargeInstruction' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllHospitalDischargeInstructions() {
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", dod.getRandomHospitalDischargeInstruction());
        long count = hospitalDischargeInstructionService.countAllHospitalDischargeInstructions();
        Assert.assertTrue("Too expensive to perform a find all test for 'HospitalDischargeInstruction', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<HospitalDischargeInstruction> result = hospitalDischargeInstructionService.findAllHospitalDischargeInstructions();
        Assert.assertNotNull("Find all method for 'HospitalDischargeInstruction' illegally returned null", result);
        Assert.assertTrue("Find all method for 'HospitalDischargeInstruction' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindHospitalDischargeInstructionEntries() {
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", dod.getRandomHospitalDischargeInstruction());
        long count = hospitalDischargeInstructionService.countAllHospitalDischargeInstructions();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<HospitalDischargeInstruction> result = hospitalDischargeInstructionService.findHospitalDischargeInstructionEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'HospitalDischargeInstruction' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'HospitalDischargeInstruction' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        HospitalDischargeInstruction obj = dod.getRandomHospitalDischargeInstruction();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to provide an identifier", id);
        obj = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        Assert.assertNotNull("Find method for 'HospitalDischargeInstruction' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyHospitalDischargeInstruction(obj);
        Integer currentVersion = obj.getVersion();
        hospitalDischargeInstructionRepository.flush();
        Assert.assertTrue("Version for 'HospitalDischargeInstruction' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateHospitalDischargeInstructionUpdate() {
        HospitalDischargeInstruction obj = dod.getRandomHospitalDischargeInstruction();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to provide an identifier", id);
        obj = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        boolean modified =  dod.modifyHospitalDischargeInstruction(obj);
        Integer currentVersion = obj.getVersion();
        HospitalDischargeInstruction merged = hospitalDischargeInstructionService.updateHospitalDischargeInstruction(obj);
        hospitalDischargeInstructionRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'HospitalDischargeInstruction' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveHospitalDischargeInstruction() {
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", dod.getRandomHospitalDischargeInstruction());
        HospitalDischargeInstruction obj = dod.getNewTransientHospitalDischargeInstruction(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'HospitalDischargeInstruction' identifier to be null", obj.getId());
        try {
            hospitalDischargeInstructionService.saveHospitalDischargeInstruction(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        hospitalDischargeInstructionRepository.flush();
        Assert.assertNotNull("Expected 'HospitalDischargeInstruction' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteHospitalDischargeInstruction() {
        HospitalDischargeInstruction obj = dod.getRandomHospitalDischargeInstruction();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'HospitalDischargeInstruction' failed to provide an identifier", id);
        obj = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        hospitalDischargeInstructionService.deleteHospitalDischargeInstruction(obj);
        hospitalDischargeInstructionRepository.flush();
        Assert.assertNull("Failed to remove 'HospitalDischargeInstruction' with identifier '" + id + "'", hospitalDischargeInstructionService.findHospitalDischargeInstruction(id));
    }
}
