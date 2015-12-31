package com.feisystems.bham.domain.reference;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.service.reference.SocialHistoryTypeCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class SocialHistoryTypeIntegrationTest {
	
	@Autowired
    SocialHistoryTypeCodeDataOnDemand dod;

	@Autowired
    SocialHistoryTypeCodeService service;

	@Autowired
    SocialHistoryTypeCodeRepository repository;
	
	@Test
    public void testFindAllSocialHistoryTypeCodes() {
        Assert.assertNotNull("Data on demand for 'SocialHistoryTypeCode' failed to initialize correctly", dod.getRandomSocialHistoryTypeCode());
        long count = repository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'SocialHistoryTypeCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SocialHistoryTypeCode> result = repository.findAll();
        Assert.assertNotNull("Find all method for 'SocialHistoryTypeCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SocialHistoryTypeCode' failed to return any data", result.size() > 0);
    }

}
