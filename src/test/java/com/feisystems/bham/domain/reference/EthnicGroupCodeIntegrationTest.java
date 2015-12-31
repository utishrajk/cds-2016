package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.EthnicGroupCodeService;
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
public class EthnicGroupCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    EthnicGroupCodeDataOnDemand dod;

	@Autowired
    EthnicGroupCodeService ethnicGroupCodeService;

	@Autowired
    EthnicGroupCodeRepository ethnicGroupCodeRepository;

	@Test
    public void testCountAllEthnicGroupCodes() {
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", dod.getRandomEthnicGroupCode());
        long count = ethnicGroupCodeService.countAllEthnicGroupCodes();
        Assert.assertTrue("Counter for 'EthnicGroupCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindEthnicGroupCode() {
        EthnicGroupCode obj = dod.getRandomEthnicGroupCode();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to provide an identifier", id);
        obj = ethnicGroupCodeService.findEthnicGroupCode(id);
        Assert.assertNotNull("Find method for 'EthnicGroupCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'EthnicGroupCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllEthnicGroupCodes() {
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", dod.getRandomEthnicGroupCode());
        long count = ethnicGroupCodeService.countAllEthnicGroupCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'EthnicGroupCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<EthnicGroupCode> result = ethnicGroupCodeService.findAllEthnicGroupCodes();
        Assert.assertNotNull("Find all method for 'EthnicGroupCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'EthnicGroupCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEthnicGroupCodeEntries() {
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", dod.getRandomEthnicGroupCode());
        long count = ethnicGroupCodeService.countAllEthnicGroupCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<EthnicGroupCode> result = ethnicGroupCodeService.findEthnicGroupCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'EthnicGroupCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'EthnicGroupCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        EthnicGroupCode obj = dod.getRandomEthnicGroupCode();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to provide an identifier", id);
        obj = ethnicGroupCodeService.findEthnicGroupCode(id);
        Assert.assertNotNull("Find method for 'EthnicGroupCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEthnicGroupCode(obj);
        Integer currentVersion = obj.getVersion();
        ethnicGroupCodeRepository.flush();
        Assert.assertTrue("Version for 'EthnicGroupCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateEthnicGroupCodeUpdate() {
        EthnicGroupCode obj = dod.getRandomEthnicGroupCode();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to provide an identifier", id);
        obj = ethnicGroupCodeService.findEthnicGroupCode(id);
        boolean modified =  dod.modifyEthnicGroupCode(obj);
        Integer currentVersion = obj.getVersion();
        EthnicGroupCode merged = (EthnicGroupCode)ethnicGroupCodeService.updateEthnicGroupCode(obj);
        ethnicGroupCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'EthnicGroupCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveEthnicGroupCode() {
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", dod.getRandomEthnicGroupCode());
        EthnicGroupCode obj = dod.getNewTransientEthnicGroupCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'EthnicGroupCode' identifier to be null", obj.getId());
        try {
            ethnicGroupCodeService.saveEthnicGroupCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        ethnicGroupCodeRepository.flush();
        Assert.assertNotNull("Expected 'EthnicGroupCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteEthnicGroupCode() {
        EthnicGroupCode obj = dod.getRandomEthnicGroupCode();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EthnicGroupCode' failed to provide an identifier", id);
        obj = ethnicGroupCodeService.findEthnicGroupCode(id);
        ethnicGroupCodeService.deleteEthnicGroupCode(obj);
        ethnicGroupCodeRepository.flush();
        Assert.assertNull("Failed to remove 'EthnicGroupCode' with identifier '" + id + "'", ethnicGroupCodeService.findEthnicGroupCode(id));
    }
}
