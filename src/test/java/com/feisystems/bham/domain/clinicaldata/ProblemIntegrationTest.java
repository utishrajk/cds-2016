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

import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.dto.ProblemDto;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class ProblemIntegrationTest {

	@Autowired
	ProblemDataOnDemand dod;

	@Autowired
	ProblemService problemService;

	@Autowired
	ProblemRepository problemRepository;

	@Test
	public void testFindProblem() {
		Problem problem = dod.getRandomProblem();
		Assert.assertNotNull("Data on demand for 'Problem' failed to initialize correctly", problem);
		Long id = problem.getId();
		System.out.println("id : " + id);
		Assert.assertNotNull("Data on demand for 'Problem' failed to provide an identifier", id);
		problem = problemRepository.findOne(id);
		Assert.assertNotNull("Find method for 'Problem' illegally returned null for id '" + id + "'", problem);
		Assert.assertEquals("Find method for 'Problem' returned the incorrect identifier", id, problem.getId());
	}

	@Test
	public void testFindAllProblems() {
		Assert.assertNotNull("Data on demand for 'Problem' failed to initialize correctly", dod.getRandomProblem());
		List<ProblemDto> result = problemService.findAllProblems();
		Assert.assertNotNull("Find all method for 'Problem' illegally returned null", result);
		Assert.assertTrue("Find all method for 'Problem' failed to return any data", result.size() > 0);
	}

	@Test
	public void testUpdateProblem() {
		Problem obj = dod.getRandomProblem();
		Assert.assertNotNull("Data on demand for 'Problem' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'Problem' failed to provide an identifier", id);
		obj = problemRepository.findOne(id);
		Problem merged = problemRepository.save(obj);
		problemRepository.flush();
		Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
	}

	@Test
	public void testSaveProblem() {
		Problem obj = dod.getNewTransientProblem(Integer.MAX_VALUE);
		Assert.assertNotNull("Data on demand for 'Problem' failed to provide a new transient entity", obj);
		Assert.assertNull("Expected 'Problem' identifier to be null", obj.getId());
		problemRepository.save(obj);
		problemRepository.flush();
		Assert.assertNotNull("Expected 'Problem' identifier to no longer be null", obj.getId());
	}

	@Test
	public void testDeleteProblem() {
		Problem obj = dod.getRandomProblem();
		Assert.assertNotNull("Data on demand for 'Problem' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'Problem' failed to provide an identifier", id);
		obj = problemRepository.findOne(id);
		problemRepository.delete(obj);
		problemRepository.flush();
		Assert.assertNull("Failed to remove 'Problem' with identifier '" + id + "'", problemRepository.findOne(id));
	}

}
