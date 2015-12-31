package com.feisystems.bham.domain.reference;
import com.feisystems.bham.service.reference.RouteCodeService;
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
public class RouteCodeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    RouteCodeDataOnDemand dod;

	@Autowired
    RouteCodeService routeCodeService;

	@Autowired
    RouteCodeRepository routeCodeRepository;

	@Test
    public void testCountAllRouteCodes() {
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", dod.getRandomRouteCode());
        long count = routeCodeService.countAllRouteCodes();
        Assert.assertTrue("Counter for 'RouteCode' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindRouteCode() {
        RouteCode obj = dod.getRandomRouteCode();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to provide an identifier", id);
        obj = routeCodeService.findRouteCode(id);
        Assert.assertNotNull("Find method for 'RouteCode' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'RouteCode' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllRouteCodes() {
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", dod.getRandomRouteCode());
        long count = routeCodeService.countAllRouteCodes();
        Assert.assertTrue("Too expensive to perform a find all test for 'RouteCode', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<RouteCode> result = routeCodeService.findAllRouteCodes();
        Assert.assertNotNull("Find all method for 'RouteCode' illegally returned null", result);
        Assert.assertTrue("Find all method for 'RouteCode' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindRouteCodeEntries() {
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", dod.getRandomRouteCode());
        long count = routeCodeService.countAllRouteCodes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<RouteCode> result = routeCodeService.findRouteCodeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'RouteCode' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'RouteCode' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        RouteCode obj = dod.getRandomRouteCode();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to provide an identifier", id);
        obj = routeCodeService.findRouteCode(id);
        Assert.assertNotNull("Find method for 'RouteCode' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyRouteCode(obj);
        Integer currentVersion = obj.getVersion();
        routeCodeRepository.flush();
        Assert.assertTrue("Version for 'RouteCode' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateRouteCodeUpdate() {
        RouteCode obj = dod.getRandomRouteCode();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to provide an identifier", id);
        obj = routeCodeService.findRouteCode(id);
        boolean modified =  dod.modifyRouteCode(obj);
        Integer currentVersion = obj.getVersion();
        RouteCode merged = (RouteCode)routeCodeService.updateRouteCode(obj);
        routeCodeRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'RouteCode' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveRouteCode() {
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", dod.getRandomRouteCode());
        RouteCode obj = dod.getNewTransientRouteCode(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'RouteCode' identifier to be null", obj.getId());
        try {
            routeCodeService.saveRouteCode(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        routeCodeRepository.flush();
        Assert.assertNotNull("Expected 'RouteCode' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteRouteCode() {
        RouteCode obj = dod.getRandomRouteCode();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RouteCode' failed to provide an identifier", id);
        obj = routeCodeService.findRouteCode(id);
        routeCodeService.deleteRouteCode(obj);
        routeCodeRepository.flush();
        Assert.assertNull("Failed to remove 'RouteCode' with identifier '" + id + "'", routeCodeService.findRouteCode(id));
    }
}
