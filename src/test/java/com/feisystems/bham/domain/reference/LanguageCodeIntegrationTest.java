package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.LanguageCodeService;
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
public class LanguageCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    LanguageCodeDataOnDemand dod;

	@Autowired
    LanguageCodeService languageCodeService;

	@Autowired
    LanguageCodeRepository languageCodeRepository;

	@Test
    public void testCountAllLanguageCodes() {
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", dod.getRandomLanguageCode());
        long count = languageCodeService.countAllLanguageCodes();
        Assert.assertTrue("Counter for 'LanguageCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindLanguageCode() {
        LanguageCode obj = dod.getRandomLanguageCode();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to provide an identifier", id);
        obj = languageCodeService.findLanguageCode(id);
        Assert.assertNotNull("Find method for 'LanguageCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'LanguageCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllLanguageCodes() {
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", dod.getRandomLanguageCode());
        long count = languageCodeService.countAllLanguageCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'LanguageCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<LanguageCode> result = languageCodeService.findAllLanguageCodes();
        Assert.assertNotNull("Find all method for 'LanguageCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'LanguageCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindLanguageCodeEntries() {
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", dod.getRandomLanguageCode());
        long count = languageCodeService.countAllLanguageCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<LanguageCode> result = languageCodeService.findLanguageCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'LanguageCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'LanguageCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        LanguageCode obj = dod.getRandomLanguageCode();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to provide an identifier", id);
        obj = languageCodeService.findLanguageCode(id);
        Assert.assertNotNull("Find method for 'LanguageCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLanguageCode(obj);
        Integer currentVersion = obj.getVersion();
        languageCodeRepository.flush();
        Assert.assertTrue("Version for 'LanguageCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateLanguageCodeUpdate() {
        LanguageCode obj = dod.getRandomLanguageCode();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to provide an identifier", id);
        obj = languageCodeService.findLanguageCode(id);
        boolean modified =  dod.modifyLanguageCode(obj);
        Integer currentVersion = obj.getVersion();
        LanguageCode merged = (LanguageCode)languageCodeService.updateLanguageCode(obj);
        languageCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'LanguageCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveLanguageCode() {
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", dod.getRandomLanguageCode());
        LanguageCode obj = dod.getNewTransientLanguageCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'LanguageCode' identifier to be null", obj.getId());
        try {
            languageCodeService.saveLanguageCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        languageCodeRepository.flush();
        Assert.assertNotNull("Expected 'LanguageCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteLanguageCode() {
        LanguageCode obj = dod.getRandomLanguageCode();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LanguageCode' failed to provide an identifier", id);
        obj = languageCodeService.findLanguageCode(id);
        languageCodeService.deleteLanguageCode(obj);
        languageCodeRepository.flush();
        Assert.assertNull("Failed to remove 'LanguageCode' with identifier '" + id + "'", languageCodeService.findLanguageCode(id));
    }
}
