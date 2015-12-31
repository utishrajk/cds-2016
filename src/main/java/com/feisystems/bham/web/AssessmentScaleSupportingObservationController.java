package com.feisystems.bham.web;

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

import com.feisystems.bham.domain.clinicaldata.AssessmentScaleSupportingObservation;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleSupportingObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

@Controller
@RequestMapping("/assessmentscalesupportingobservations")
public class AssessmentScaleSupportingObservationController {

	@Autowired
    AssessmentScaleSupportingObservationService assessmentScaleSupportingObservationService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AssessmentScaleSupportingObservation showJson(@PathVariable("id") Long id) {
        AssessmentScaleSupportingObservation assessmentScaleSupportingObservation = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return assessmentScaleSupportingObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AssessmentScaleSupportingObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AssessmentScaleSupportingObservation> result = assessmentScaleSupportingObservationService.findAllAssessmentScaleSupportingObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody AssessmentScaleSupportingObservation assessmentScaleSupportingObservation) {
        assessmentScaleSupportingObservationService.saveAssessmentScaleSupportingObservation(assessmentScaleSupportingObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody AssessmentScaleSupportingObservation assessmentScaleSupportingObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (assessmentScaleSupportingObservationService.updateAssessmentScaleSupportingObservation(assessmentScaleSupportingObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AssessmentScaleSupportingObservation assessmentScaleSupportingObservation = assessmentScaleSupportingObservationService.findAssessmentScaleSupportingObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (assessmentScaleSupportingObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        assessmentScaleSupportingObservationService.deleteAssessmentScaleSupportingObservation(assessmentScaleSupportingObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
