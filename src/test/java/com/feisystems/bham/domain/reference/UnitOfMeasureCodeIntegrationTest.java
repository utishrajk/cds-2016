package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.UnitOfMeasureCodeService;
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
public class UnitOfMeasureCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    UnitOfMeasureCodeDataOnDemand dod;

	@Autowired
    UnitOfMeasureCodeService unitOfMeasureCodeService;

	@Autowired
    UnitOfMeasureCodeRepository unitOfMeasureCodeRepository;

	@Test
    public void testCountAllUnitOfMeasureCodes() {
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", dod.getRandomUnitOfMeasureCode());
        long count = unitOfMeasureCodeService.countAllUnitOfMeasureCodes();
        Assert.assertTrue("Counter for 'UnitOfMeasureCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindUnitOfMeasureCode() {
        UnitOfMeasureCode obj = dod.getRandomUnitOfMeasureCode();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to provide an identifier", id);
        obj = unitOfMeasureCodeService.findUnitOfMeasureCode(id);
        Assert.assertNotNull("Find method for 'UnitOfMeasureCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UnitOfMeasureCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllUnitOfMeasureCodes() {
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", dod.getRandomUnitOfMeasureCode());
        long count = unitOfMeasureCodeService.countAllUnitOfMeasureCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'UnitOfMeasureCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UnitOfMeasureCode> result = unitOfMeasureCodeService.findAllUnitOfMeasureCodes();
        Assert.assertNotNull("Find all method for 'UnitOfMeasureCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UnitOfMeasureCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindUnitOfMeasureCodeEntries() {
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", dod.getRandomUnitOfMeasureCode());
        long count = unitOfMeasureCodeService.countAllUnitOfMeasureCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UnitOfMeasureCode> result = unitOfMeasureCodeService.findUnitOfMeasureCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UnitOfMeasureCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UnitOfMeasureCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        UnitOfMeasureCode obj = dod.getRandomUnitOfMeasureCode();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to provide an identifier", id);
        obj = unitOfMeasureCodeService.findUnitOfMeasureCode(id);
        Assert.assertNotNull("Find method for 'UnitOfMeasureCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUnitOfMeasureCode(obj);
        Integer currentVersion = obj.getVersion();
        unitOfMeasureCodeRepository.flush();
        Assert.assertTrue("Version for 'UnitOfMeasureCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateUnitOfMeasureCodeUpdate() {
        UnitOfMeasureCode obj = dod.getRandomUnitOfMeasureCode();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to provide an identifier", id);
        obj = unitOfMeasureCodeService.findUnitOfMeasureCode(id);
        boolean modified =  dod.modifyUnitOfMeasureCode(obj);
        Integer currentVersion = obj.getVersion();
        UnitOfMeasureCode merged = (UnitOfMeasureCode)unitOfMeasureCodeService.updateUnitOfMeasureCode(obj);
        unitOfMeasureCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'UnitOfMeasureCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveUnitOfMeasureCode() {
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", dod.getRandomUnitOfMeasureCode());
        UnitOfMeasureCode obj = dod.getNewTransientUnitOfMeasureCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UnitOfMeasureCode' identifier to be null", obj.getId());
        try {
            unitOfMeasureCodeService.saveUnitOfMeasureCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        unitOfMeasureCodeRepository.flush();
        Assert.assertNotNull("Expected 'UnitOfMeasureCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteUnitOfMeasureCode() {
        UnitOfMeasureCode obj = dod.getRandomUnitOfMeasureCode();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UnitOfMeasureCode' failed to provide an identifier", id);
        obj = unitOfMeasureCodeService.findUnitOfMeasureCode(id);
        unitOfMeasureCodeService.deleteUnitOfMeasureCode(obj);
        unitOfMeasureCodeRepository.flush();
        Assert.assertNull("Failed to remove 'UnitOfMeasureCode' with identifier '" + id + "'", unitOfMeasureCodeService.findUnitOfMeasureCode(id));
    }
}
