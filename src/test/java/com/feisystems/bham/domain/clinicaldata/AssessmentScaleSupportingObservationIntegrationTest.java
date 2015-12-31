package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleSupportingObservationService;
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
public class AssessmentScaleSupportingObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AssessmentScaleSupportingObservationDataOnDemand dod;

	@Autowired
    AssessmentScaleSupportingObservationService assessmentScaleSupportingObservationService;

	@Autowired
    AssessmentScaleSupportingObservationRepository assessmentScaleSupportingObservationRepository;

	@Test
    public void testCountAllAssessmentScaleSupportingObservations() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", dod.getRandomAssessmentScaleSupportingObservation());
        long count = assessmentScaleSupportingObservationService.countAllAssessmentScaleSupportingObservations();
        Assert.assertTrue("Counter for 'AssessmentScaleSupportingObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAssessmentScaleSupportingObservation() {
        AssessmentScaleSupportingObservation obj = dod.getRandomAssessmentScaleSupportingObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to provide an identifier", id);
        obj = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        Assert.assertNotNull("Find method for 'AssessmentScaleSupportingObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AssessmentScaleSupportingObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAssessmentScaleSupportingObservations() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", dod.getRandomAssessmentScaleSupportingObservation());
        long count = assessmentScaleSupportingObservationService.countAllAssessmentScaleSupportingObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'AssessmentScaleSupportingObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AssessmentScaleSupportingObservation> result = assessmentScaleSupportingObservationService.findAllAssessmentScaleSupportingObservations();
        Assert.assertNotNull("Find all method for 'AssessmentScaleSupportingObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AssessmentScaleSupportingObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAssessmentScaleSupportingObservationEntries() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", dod.getRandomAssessmentScaleSupportingObservation());
        long count = assessmentScaleSupportingObservationService.countAllAssessmentScaleSupportingObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AssessmentScaleSupportingObservation> result = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AssessmentScaleSupportingObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AssessmentScaleSupportingObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        AssessmentScaleSupportingObservation obj = dod.getRandomAssessmentScaleSupportingObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to provide an identifier", id);
        obj = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        Assert.assertNotNull("Find method for 'AssessmentScaleSupportingObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAssessmentScaleSupportingObservation(obj);
        Integer currentVersion = obj.getVersion();
        assessmentScaleSupportingObservationRepository.flush();
        Assert.assertTrue("Version for 'AssessmentScaleSupportingObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateAssessmentScaleSupportingObservationUpdate() {
        AssessmentScaleSupportingObservation obj = dod.getRandomAssessmentScaleSupportingObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to provide an identifier", id);
        obj = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        boolean modified =  dod.modifyAssessmentScaleSupportingObservation(obj);
        Integer currentVersion = obj.getVersion();
        AssessmentScaleSupportingObservation merged = assessmentScaleSupportingObservationService.updateAssessmentScaleSupportingObservation(obj);
        assessmentScaleSupportingObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AssessmentScaleSupportingObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveAssessmentScaleSupportingObservation() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", dod.getRandomAssessmentScaleSupportingObservation());
        AssessmentScaleSupportingObservation obj = dod.getNewTransientAssessmentScaleSupportingObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AssessmentScaleSupportingObservation' identifier to be null", obj.getId());
        try {
            assessmentScaleSupportingObservationService.saveAssessmentScaleSupportingObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        assessmentScaleSupportingObservationRepository.flush();
        Assert.assertNotNull("Expected 'AssessmentScaleSupportingObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteAssessmentScaleSupportingObservation() {
        AssessmentScaleSupportingObservation obj = dod.getRandomAssessmentScaleSupportingObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleSupportingObservation' failed to provide an identifier", id);
        obj = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        assessmentScaleSupportingObservationService.deleteAssessmentScaleSupportingObservation(obj);
        assessmentScaleSupportingObservationRepository.flush();
        Assert.assertNull("Failed to remove 'AssessmentScaleSupportingObservation' with identifier '" + id + "'", assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id));
    }
}
