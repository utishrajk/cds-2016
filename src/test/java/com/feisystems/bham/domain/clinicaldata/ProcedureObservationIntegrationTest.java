package com.feisystems.bham.domain.clinicaldata;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class ProcedureObservationIntegrationTest {

	@Autowired
	ProcedureObservationDataOnDemand dod;

	@Autowired
	ProcedureObservationService procedureObservationService;

	@Autowired
	ProcedureObservationRepository procedureObservationRepository;

	@Test
	public void testFindProcedureObservation() {
		ProcedureObservation ProcedureObservation = dod.getRandomProcedureObservation();
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to initialize correctly", ProcedureObservation);
		Long id = ProcedureObservation.getId();
		System.out.println("id : " + id);
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to provide an identifier", id);
		ProcedureObservation = procedureObservationRepository.findOne(id);
		Assert.assertNotNull("Find method for 'ProcedureObservation' illegally returned null for id '" + id + "'", ProcedureObservation);
		Assert.assertEquals("Find method for 'ProcedureObservation' returned the incorrect identifier", id, ProcedureObservation.getId());
	}
	
	@Test
	public void testFindAllProcedureObservations() {
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to initialize correctly", dod.getRandomProcedureObservation());
		List<ProcedureObservationDto> result = procedureObservationService.findAllProcedureObservations();
		Assert.assertNotNull("Find all method for 'ProcedureObservation' illegally returned null", result);
		Assert.assertTrue("Find all method for 'ProcedureObservation' failed to return any data", result.size() > 0);
	}
	
	@Test
	public void testUpdateProcedureObservation() {
		ProcedureObservation obj = dod.getRandomProcedureObservation();
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to provide an identifier", id);
		obj = procedureObservationRepository.findOne(id);
		ProcedureObservation merged = procedureObservationRepository.save(obj);
		procedureObservationRepository.flush();
		Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
	}
	
	@Test
	public void testSaveProcedureObservation() {
		ProcedureObservation obj = dod.getNewTransientProcedureObservation(Integer.MAX_VALUE);
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to provide a new transient entity", obj);
		Assert.assertNull("Expected 'ProcedureObservation' identifier to be null", obj.getId());
		procedureObservationRepository.save(obj);
		procedureObservationRepository.flush();
		Assert.assertNotNull("Expected 'ProcedureObservation' identifier to no longer be null", obj.getId());
	}
	
	@Test
	public void testDeleteProcedureObservation() {
		ProcedureObservation obj = dod.getRandomProcedureObservation();
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'ProcedureObservation' failed to provide an identifier", id);
		obj = procedureObservationRepository.findOne(id);
		procedureObservationRepository.delete(obj);
		procedureObservationRepository.flush();
		Assert.assertNull("Failed to remove 'ProcedureObservation' with identifier '" + id + "'", procedureObservationRepository.findOne(id));
	}

}
