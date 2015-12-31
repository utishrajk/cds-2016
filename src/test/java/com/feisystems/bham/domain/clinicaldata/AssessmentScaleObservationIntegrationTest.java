package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleObservationService;
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
public class AssessmentScaleObservationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AssessmentScaleObservationDataOnDemand dod;

	@Autowired
    AssessmentScaleObservationService assessmentScaleObservationService;

	@Autowired
    AssessmentScaleObservationRepository assessmentScaleObservationRepository;

	@Test
    public void testCountAllAssessmentScaleObservations() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", dod.getRandomAssessmentScaleObservation());
        long count = assessmentScaleObservationService.countAllAssessmentScaleObservations();
        Assert.assertTrue("Counter for 'AssessmentScaleObservation' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAssessmentScaleObservation() {
        AssessmentScaleObservation obj = dod.getRandomAssessmentScaleObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to provide an identifier", id);
        obj = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        Assert.assertNotNull("Find method for 'AssessmentScaleObservation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AssessmentScaleObservation' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAssessmentScaleObservations() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", dod.getRandomAssessmentScaleObservation());
        long count = assessmentScaleObservationService.countAllAssessmentScaleObservations();
        Assert.assertTrue("Too expensive to perform a find all test for 'AssessmentScaleObservation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AssessmentScaleObservation> result = assessmentScaleObservationService.findAllAssessmentScaleObservations();
        Assert.assertNotNull("Find all method for 'AssessmentScaleObservation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AssessmentScaleObservation' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAssessmentScaleObservationEntries() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", dod.getRandomAssessmentScaleObservation());
        long count = assessmentScaleObservationService.countAllAssessmentScaleObservations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AssessmentScaleObservation> result = assessmentScaleObservationService.findAssessmentScaleObservationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AssessmentScaleObservation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AssessmentScaleObservation' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        AssessmentScaleObservation obj = dod.getRandomAssessmentScaleObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to provide an identifier", id);
        obj = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        Assert.assertNotNull("Find method for 'AssessmentScaleObservation' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAssessmentScaleObservation(obj);
        Integer currentVersion = obj.getVersion();
        assessmentScaleObservationRepository.flush();
        Assert.assertTrue("Version for 'AssessmentScaleObservation' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateAssessmentScaleObservationUpdate() {
        AssessmentScaleObservation obj = dod.getRandomAssessmentScaleObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to provide an identifier", id);
        obj = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        boolean modified =  dod.modifyAssessmentScaleObservation(obj);
        Integer currentVersion = obj.getVersion();
        AssessmentScaleObservation merged = assessmentScaleObservationService.updateAssessmentScaleObservation(obj);
        assessmentScaleObservationRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AssessmentScaleObservation' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveAssessmentScaleObservation() {
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", dod.getRandomAssessmentScaleObservation());
        AssessmentScaleObservation obj = dod.getNewTransientAssessmentScaleObservation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AssessmentScaleObservation' identifier to be null", obj.getId());
        try {
            assessmentScaleObservationService.saveAssessmentScaleObservation(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        assessmentScaleObservationRepository.flush();
        Assert.assertNotNull("Expected 'AssessmentScaleObservation' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteAssessmentScaleObservation() {
        AssessmentScaleObservation obj = dod.getRandomAssessmentScaleObservation();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AssessmentScaleObservation' failed to provide an identifier", id);
        obj = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        assessmentScaleObservationService.deleteAssessmentScaleObservation(obj);
        assessmentScaleObservationRepository.flush();
        Assert.assertNull("Failed to remove 'AssessmentScaleObservation' with identifier '" + id + "'", assessmentScaleObservationService.findAssessmentScaleObservation(id));
    }
}
