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

import com.feisystems.bham.service.clinicaldata.SocialHistoryService;
import com.feisystems.bham.service.dto.SocialHistoryDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class SocialHistoryIntegrationTest {
	
	@Autowired
	SocialHistoryDataOnDemand dod;

	@Autowired
	SocialHistoryService socialHistoryService;

	@Autowired
	SocialHistoryRepository socialHistoryRepository;

	@Test
	public void testFindSocialHistory() {
		SocialHistory socialHistory = dod.getRandomSocialHistory();
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to initialize correctly", socialHistory);
		Long id = socialHistory.getId();
		System.out.println("id : " + id);
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to provide an identifier", id);
		socialHistory = socialHistoryRepository.findOne(id);
		Assert.assertNotNull("Find method for 'SocialHistory' illegally returned null for id '" + id + "'", socialHistory);
		Assert.assertEquals("Find method for 'SocialHistory' returned the incorrect identifier", id, socialHistory.getId());
	}
	
	@Test
	public void testFindAllSocialHistorys() {
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to initialize correctly", dod.getRandomSocialHistory());
		List<SocialHistoryDto> result = socialHistoryService.findAllSocialHistorys();
		Assert.assertNotNull("Find all method for 'SocialHistory' illegally returned null", result);
		Assert.assertTrue("Find all method for 'SocialHistory' failed to return any data", result.size() > 0);
	}
	
	@Test
	public void testUpdateSocialHistory() {
		SocialHistory obj = dod.getRandomSocialHistory();
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to provide an identifier", id);
		obj = socialHistoryRepository.findOne(id);
		SocialHistory merged = socialHistoryRepository.save(obj);
		socialHistoryRepository.flush();
		Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
	}
	
	@Test
	public void testSaveSocialHistory() {
		SocialHistory obj = dod.getNewTransientSocialHistory(Integer.MAX_VALUE);
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to provide a new transient entity", obj);
		Assert.assertNull("Expected 'SocialHistory' identifier to be null", obj.getId());
		socialHistoryRepository.save(obj);
		socialHistoryRepository.flush();
		Assert.assertNotNull("Expected 'SocialHistory' identifier to no longer be null", obj.getId());
	}
	
	@Test
	public void testDeleteSocialHistory() {
		SocialHistory obj = dod.getRandomSocialHistory();
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull("Data on demand for 'SocialHistory' failed to provide an identifier", id);
		obj = socialHistoryRepository.findOne(id);
		socialHistoryRepository.delete(obj);
		socialHistoryRepository.flush();
		Assert.assertNull("Failed to remove 'SocialHistory' with identifier '" + id + "'", socialHistoryRepository.findOne(id));
	}
}
