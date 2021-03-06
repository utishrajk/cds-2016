package com.feisystems.bham.domain.patient;
import com.feisystems.bham.service.dto.PatientProfileDto;
import com.feisystems.bham.service.patient.PatientService;

import java.util.List;

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
public class PatientIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    PatientDataOnDemand dod;

	@Autowired
    PatientService patientService;

	@Autowired
    PatientRepository patientRepository;

	@Test
    public void testFindPatient() {
        Patient obj = dod.getRandomPatient();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly", 
        		obj);
        Long id = obj.getId();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to provide an identifier", 
        		id);
        obj = patientRepository.findOne(id);
        Assert.assertNotNull(
        		"Find method for 'Patient' illegally returned null for id '"
        				+ id + "'", obj);
        Assert.assertEquals(
        		"Find method for 'Patient' returned the incorrect identifier", 
        		id, obj.getId());
    }

	@Test
    public void testFindAllPatients() {
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly", dod.getRandomPatient());
        List<PatientProfileDto> result = patientService.findAllPatients();
        Assert.assertNotNull(
        		"Find all method for 'Patient' illegally returned null", 
        		result);
        Assert.assertTrue(
        		"Find all method for 'Patient' failed to return any data", 
        		result.size() > 0);
    }


	@Test
    public void testFlush() {
        Patient obj = dod.getRandomPatient();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to provide an identifier", id);
        obj = patientRepository.findOne(id);
        Assert.assertNotNull(
        		"Find method for 'Patient' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPatient(obj);
        Integer currentVersion = obj.getVersion();
        patientRepository.flush();
        Assert.assertTrue(
        		"Version for 'Patient' failed to increment on flush directive", 
        		(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdatePatientUpdate() {
        Patient obj = dod.getRandomPatient();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to provide an identifier", id);
        obj = patientRepository.findOne(id);
        boolean modified =  dod.modifyPatient(obj);
        Integer currentVersion = obj.getVersion();
        Patient merged = patientRepository.save(obj);
        patientRepository.flush();
        Assert.assertEquals(
        		"Identifier of merged object not the same as identifier of original object", 
        		merged.getId(), id);
        Assert.assertTrue(
        		"Version for 'Patient' failed to increment on merge and flush directive", 
        		(currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePatient() {
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly", dod.getRandomPatient());
        Patient obj = dod.getNewTransientPatient(Integer.MAX_VALUE);
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to provide a new transient entity", 
        		obj);
        Assert.assertNull(
        		"Expected 'Patient' identifier to be null", 
        		obj.getId());
		patientRepository.save(obj);
        patientRepository.flush();
        Assert.assertNotNull("Expected 'Patient' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePatient() {
        Patient obj = dod.getRandomPatient();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to initialize correctly",
        		obj);
        Long id = obj.getId();
        Assert.assertNotNull(
        		"Data on demand for 'Patient' failed to provide an identifier",
        		id);
        obj = patientRepository.findOne(id);
        patientRepository.delete(obj);
        patientRepository.flush();
		Assert.assertNull("Failed to remove 'Patient' with identifier '" + id
				+ "'", patientRepository.findOne(id));
    }
}
