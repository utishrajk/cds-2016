package com.feisystems.bham.web;

import com.feisystems.bham.domain.clinicaldata.AssessmentScaleObservation;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleObservationService;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleSupportingObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assessmentscaleobservations")
public class AssessmentScaleObservationController {

	@Autowired
    AssessmentScaleObservationService assessmentScaleObservationService;

	@Autowired
    AssessmentScaleSupportingObservationService assessmentScaleSupportingObservationService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AssessmentScaleObservation showJson(@PathVariable("id") Long id) {
        AssessmentScaleObservation assessmentScaleObservation = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return assessmentScaleObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AssessmentScaleObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AssessmentScaleObservation> result = assessmentScaleObservationService.findAllAssessmentScaleObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody AssessmentScaleObservation assessmentScaleObservation) {
        assessmentScaleObservationService.saveAssessmentScaleObservation(assessmentScaleObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody AssessmentScaleObservation assessmentScaleObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (assessmentScaleObservationService.updateAssessmentScaleObservation(assessmentScaleObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AssessmentScaleObservation assessmentScaleObservation = assessmentScaleObservationService.findAssessmentScaleObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (assessmentScaleObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        assessmentScaleObservationService.deleteAssessmentScaleObservation(assessmentScaleObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
