package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.PatientClinicalDocumentTypeCodeService;
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
public class PatientClinicalDocumentTypeCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PatientClinicalDocumentTypeCodeDataOnDemand dod;

	@Autowired
    PatientClinicalDocumentTypeCodeService patientClinicalDocumentTypeCodeService;

	@Test
    public void testCountAllPatientClinicalDocumentTypeCodes() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", dod.getRandomPatientClinicalDocumentTypeCode());
        long count = patientClinicalDocumentTypeCodeService.countAllPatientClinicalDocumentTypeCodes();
        Assert.assertTrue("Counter for 'PatientClinicalDocumentTypeCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindPatientClinicalDocumentTypeCode() {
        PatientClinicalDocumentTypeCode obj = dod.getRandomPatientClinicalDocumentTypeCode();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to provide an identifier", id);
        obj = patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCode(id);
        Assert.assertNotNull("Find method for 'PatientClinicalDocumentTypeCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'PatientClinicalDocumentTypeCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllPatientClinicalDocumentTypeCodes() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", dod.getRandomPatientClinicalDocumentTypeCode());
        long count = patientClinicalDocumentTypeCodeService.countAllPatientClinicalDocumentTypeCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'PatientClinicalDocumentTypeCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<PatientClinicalDocumentTypeCode> result = patientClinicalDocumentTypeCodeService.findAllPatientClinicalDocumentTypeCodes();
        Assert.assertNotNull("Find all method for 'PatientClinicalDocumentTypeCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'PatientClinicalDocumentTypeCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindPatientClinicalDocumentTypeCodeEntries() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", dod.getRandomPatientClinicalDocumentTypeCode());
        long count = patientClinicalDocumentTypeCodeService.countAllPatientClinicalDocumentTypeCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<PatientClinicalDocumentTypeCode> result = patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'PatientClinicalDocumentTypeCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'PatientClinicalDocumentTypeCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testUpdatePatientClinicalDocumentTypeCodeUpdate() {
        PatientClinicalDocumentTypeCode obj = dod.getRandomPatientClinicalDocumentTypeCode();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to provide an identifier", id);
        obj = patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCode(id);
        boolean modified =  dod.modifyPatientClinicalDocumentTypeCode(obj);
        Integer currentVersion = obj.getVersion();
        PatientClinicalDocumentTypeCode merged = (PatientClinicalDocumentTypeCode)patientClinicalDocumentTypeCodeService.updatePatientClinicalDocumentTypeCode(obj);
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'PatientClinicalDocumentTypeCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePatientClinicalDocumentTypeCode() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", dod.getRandomPatientClinicalDocumentTypeCode());
        PatientClinicalDocumentTypeCode obj = dod.getNewTransientPatientClinicalDocumentTypeCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'PatientClinicalDocumentTypeCode' identifier to be null", obj.getId());
        try {
            patientClinicalDocumentTypeCodeService.savePatientClinicalDocumentTypeCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        Assert.assertNotNull("Expected 'PatientClinicalDocumentTypeCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePatientClinicalDocumentTypeCode() {
        PatientClinicalDocumentTypeCode obj = dod.getRandomPatientClinicalDocumentTypeCode();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocumentTypeCode' failed to provide an identifier", id);
        obj = patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCode(id);
        patientClinicalDocumentTypeCodeService.deletePatientClinicalDocumentTypeCode(obj);
        Assert.assertNull("Failed to remove 'PatientClinicalDocumentTypeCode' with identifier '" + id + "'", patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCode(id));
    }
}
