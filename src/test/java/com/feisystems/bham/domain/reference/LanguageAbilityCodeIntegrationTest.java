package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.LanguageAbilityCodeService;
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
public class LanguageAbilityCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    LanguageAbilityCodeDataOnDemand dod;

	@Autowired
    LanguageAbilityCodeService languageAbilityCodeService;

	@Autowired
    LanguageAbilityCodeRepository languageAbilityCodeRepository;

	@Test
    public void testCountAllLanguageAbilityCodes() {
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", dod.getRandomLanguageAbilityCode());
        long count = languageAbilityCodeService.countAllLanguageAbilityCodes();
        Assert.assertTrue("Counter for 'LanguageAbilityCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindLanguageAbilityCode() {
        LanguageAbilityCode obj = dod.getRandomLanguageAbilityCode();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to provide an identifier", id);
        obj = languageAbilityCodeService.findLanguageAbilityCode(id);
        Assert.assertNotNull("Find method for 'LanguageAbilityCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'LanguageAbilityCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllLanguageAbilityCodes() {
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", dod.getRandomLanguageAbilityCode());
        long count = languageAbilityCodeService.countAllLanguageAbilityCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'LanguageAbilityCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<LanguageAbilityCode> result = languageAbilityCodeService.findAllLanguageAbilityCodes();
        Assert.assertNotNull("Find all method for 'LanguageAbilityCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'LanguageAbilityCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindLanguageAbilityCodeEntries() {
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", dod.getRandomLanguageAbilityCode());
        long count = languageAbilityCodeService.countAllLanguageAbilityCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<LanguageAbilityCode> result = languageAbilityCodeService.findLanguageAbilityCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'LanguageAbilityCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'LanguageAbilityCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        LanguageAbilityCode obj = dod.getRandomLanguageAbilityCode();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to provide an identifier", id);
        obj = languageAbilityCodeService.findLanguageAbilityCode(id);
        Assert.assertNotNull("Find method for 'LanguageAbilityCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLanguageAbilityCode(obj);
        Integer currentVersion = obj.getVersion();
        languageAbilityCodeRepository.flush();
        Assert.assertTrue("Version for 'LanguageAbilityCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateLanguageAbilityCodeUpdate() {
        LanguageAbilityCode obj = dod.getRandomLanguageAbilityCode();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to provide an identifier", id);
        obj = languageAbilityCodeService.findLanguageAbilityCode(id);
        boolean modified =  dod.modifyLanguageAbilityCode(obj);
        Integer currentVersion = obj.getVersion();
        LanguageAbilityCode merged = (LanguageAbilityCode)languageAbilityCodeService.updateLanguageAbilityCode(obj);
        languageAbilityCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'LanguageAbilityCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveLanguageAbilityCode() {
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", dod.getRandomLanguageAbilityCode());
        LanguageAbilityCode obj = dod.getNewTransientLanguageAbilityCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'LanguageAbilityCode' identifier to be null", obj.getId());
        try {
            languageAbilityCodeService.saveLanguageAbilityCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        languageAbilityCodeRepository.flush();
        Assert.assertNotNull("Expected 'LanguageAbilityCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteLanguageAbilityCode() {
        LanguageAbilityCode obj = dod.getRandomLanguageAbilityCode();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageAbilityCode' failed to provide an identifier", id);
        obj = languageAbilityCodeService.findLanguageAbilityCode(id);
        languageAbilityCodeService.deleteLanguageAbilityCode(obj);
        languageAbilityCodeRepository.flush();
        Assert.assertNull("Failed to remove 'LanguageAbilityCode' with identifier '" + id + "'", languageAbilityCodeService.findLanguageAbilityCode(id));
    }
}
