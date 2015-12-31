package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.MedicationDeliveryMethodCodeService;
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
public class MedicationDeliveryMethodCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MedicationDeliveryMethodCodeDataOnDemand dod;

	@Autowired
    MedicationDeliveryMethodCodeService medicationDeliveryMethodCodeService;

	@Autowired
    MedicationDeliveryMethodCodeRepository medicationDeliveryMethodCodeRepository;

	@Test
    public void testCountAllMedicationDeliveryMethodCodes() {
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", dod.getRandomMedicationDeliveryMethodCode());
        long count = medicationDeliveryMethodCodeService.countAllMedicationDeliveryMethodCodes();
        Assert.assertTrue("Counter for 'MedicationDeliveryMethodCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMedicationDeliveryMethodCode() {
        MedicationDeliveryMethodCode obj = dod.getRandomMedicationDeliveryMethodCode();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to provide an identifier", id);
        obj = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id);
        Assert.assertNotNull("Find method for 'MedicationDeliveryMethodCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'MedicationDeliveryMethodCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMedicationDeliveryMethodCodes() {
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", dod.getRandomMedicationDeliveryMethodCode());
        long count = medicationDeliveryMethodCodeService.countAllMedicationDeliveryMethodCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'MedicationDeliveryMethodCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<MedicationDeliveryMethodCode> result = medicationDeliveryMethodCodeService.findAllMedicationDeliveryMethodCodes();
        Assert.assertNotNull("Find all method for 'MedicationDeliveryMethodCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'MedicationDeliveryMethodCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMedicationDeliveryMethodCodeEntries() {
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", dod.getRandomMedicationDeliveryMethodCode());
        long count = medicationDeliveryMethodCodeService.countAllMedicationDeliveryMethodCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<MedicationDeliveryMethodCode> result = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'MedicationDeliveryMethodCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'MedicationDeliveryMethodCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        MedicationDeliveryMethodCode obj = dod.getRandomMedicationDeliveryMethodCode();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to provide an identifier", id);
        obj = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id);
        Assert.assertNotNull("Find method for 'MedicationDeliveryMethodCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMedicationDeliveryMethodCode(obj);
        Integer currentVersion = obj.getVersion();
        medicationDeliveryMethodCodeRepository.flush();
        Assert.assertTrue("Version for 'MedicationDeliveryMethodCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMedicationDeliveryMethodCodeUpdate() {
        MedicationDeliveryMethodCode obj = dod.getRandomMedicationDeliveryMethodCode();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to provide an identifier", id);
        obj = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id);
        boolean modified =  dod.modifyMedicationDeliveryMethodCode(obj);
        Integer currentVersion = obj.getVersion();
        MedicationDeliveryMethodCode merged = (MedicationDeliveryMethodCode)medicationDeliveryMethodCodeService.updateMedicationDeliveryMethodCode(obj);
        medicationDeliveryMethodCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'MedicationDeliveryMethodCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMedicationDeliveryMethodCode() {
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", dod.getRandomMedicationDeliveryMethodCode());
        MedicationDeliveryMethodCode obj = dod.getNewTransientMedicationDeliveryMethodCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'MedicationDeliveryMethodCode' identifier to be null", obj.getId());
        try {
            medicationDeliveryMethodCodeService.saveMedicationDeliveryMethodCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        medicationDeliveryMethodCodeRepository.flush();
        Assert.assertNotNull("Expected 'MedicationDeliveryMethodCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMedicationDeliveryMethodCode() {
        MedicationDeliveryMethodCode obj = dod.getRandomMedicationDeliveryMethodCode();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MedicationDeliveryMethodCode' failed to provide an identifier", id);
        obj = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id);
        medicationDeliveryMethodCodeService.deleteMedicationDeliveryMethodCode(obj);
        medicationDeliveryMethodCodeRepository.flush();
        Assert.assertNull("Failed to remove 'MedicationDeliveryMethodCode' with identifier '" + id + "'", medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id));
    }
}
