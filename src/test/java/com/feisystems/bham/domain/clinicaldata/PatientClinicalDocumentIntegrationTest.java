package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.PatientClinicalDocumentService;

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
public class PatientClinicalDocumentIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PatientClinicalDocumentDataOnDemand dod;

	@Autowired
    PatientClinicalDocumentService patientClinicalDocumentService;

	@Autowired
    PatientClinicalDocumentRepository patientClinicalDocumentRepository;

	@Test
    public void testCountAllPatientClinicalDocuments() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", dod.getRandomPatientClinicalDocument());
        long count = patientClinicalDocumentService.countAllPatientClinicalDocuments();
        Assert.assertTrue("Counter for 'PatientClinicalDocument' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindPatientClinicalDocument() {
        PatientClinicalDocument obj = dod.getRandomPatientClinicalDocument();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to provide an identifier", id);
        obj = patientClinicalDocumentService.findPatientClinicalDocument(id);
        Assert.assertNotNull("Find method for 'PatientClinicalDocument' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'PatientClinicalDocument' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllPatientClinicalDocuments() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", dod.getRandomPatientClinicalDocument());
        long count = patientClinicalDocumentService.countAllPatientClinicalDocuments();
        Assert.assertTrue("Too expensive to perform a find all test for 'PatientClinicalDocument', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<PatientClinicalDocument> result = patientClinicalDocumentService.findAllPatientClinicalDocuments();
        Assert.assertNotNull("Find all method for 'PatientClinicalDocument' illegally returned null", result);
        Assert.assertTrue("Find all method for 'PatientClinicalDocument' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindPatientClinicalDocumentEntries() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", dod.getRandomPatientClinicalDocument());
        long count = patientClinicalDocumentService.countAllPatientClinicalDocuments();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<PatientClinicalDocument> result = patientClinicalDocumentService.findPatientClinicalDocumentEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'PatientClinicalDocument' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'PatientClinicalDocument' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        PatientClinicalDocument obj = dod.getRandomPatientClinicalDocument();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to provide an identifier", id);
        obj = patientClinicalDocumentService.findPatientClinicalDocument(id);
        Assert.assertNotNull("Find method for 'PatientClinicalDocument' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPatientClinicalDocument(obj);
        Integer currentVersion = obj.getVersion();
        patientClinicalDocumentRepository.flush();
        Assert.assertTrue("Version for 'PatientClinicalDocument' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdatePatientClinicalDocumentUpdate() {
        PatientClinicalDocument obj = dod.getRandomPatientClinicalDocument();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to provide an identifier", id);
        obj = patientClinicalDocumentService.findPatientClinicalDocument(id);
        boolean modified =  dod.modifyPatientClinicalDocument(obj);
        Integer currentVersion = obj.getVersion();
        PatientClinicalDocument merged = patientClinicalDocumentService.updatePatientClinicalDocument(obj);
        patientClinicalDocumentRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'PatientClinicalDocument' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePatientClinicalDocument() {
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", dod.getRandomPatientClinicalDocument());
        PatientClinicalDocument obj = dod.getNewTransientPatientClinicalDocument(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'PatientClinicalDocument' identifier to be null", obj.getId());
        try {
            patientClinicalDocumentService.savePatientClinicalDocument(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        patientClinicalDocumentRepository.flush();
        Assert.assertNotNull("Expected 'PatientClinicalDocument' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePatientClinicalDocument() {
        PatientClinicalDocument obj = dod.getRandomPatientClinicalDocument();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'PatientClinicalDocument' failed to provide an identifier", id);
        obj = patientClinicalDocumentService.findPatientClinicalDocument(id);
        patientClinicalDocumentService.deletePatientClinicalDocument(obj);
        patientClinicalDocumentRepository.flush();
        Assert.assertNull("Failed to remove 'PatientClinicalDocument' with identifier '" + id + "'", patientClinicalDocumentService.findPatientClinicalDocument(id));
    }
}
