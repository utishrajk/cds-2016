package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.AdministrativeGenderCodeService;

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
public class AdministrativeGenderCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AdministrativeGenderCodeDataOnDemand dod;

	@Autowired
    AdministrativeGenderCodeService administrativeGenderCodeService;

	@Autowired
    AdministrativeGenderCodeRepository administrativeGenderCodeRepository;

	@Test
    public void testFindAllAdministrativeGenderCodes() {
        Assert.assertNotNull("Data on demand for 'AdministrativeGenderCode' failed to initialize correctly", dod.getRandomAdministrativeGenderCode());
        List<AdministrativeGenderCode> result = administrativeGenderCodeService.findAllAdministrativeGenderCodes();
        Assert.assertNotNull("Find all method for 'AdministrativeGenderCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AdministrativeGenderCode' failed to return any data", result.size() > 0);
    }

}
