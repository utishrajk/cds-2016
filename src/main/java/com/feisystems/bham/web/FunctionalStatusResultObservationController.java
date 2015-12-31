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

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultObservation;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleObservationService;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

@Controller
@RequestMapping("/functionalstatusresultobservations")
public class FunctionalStatusResultObservationController {

	@Autowired
    FunctionalStatusResultObservationService functionalStatusResultObservationService;

	@Autowired
    AssessmentScaleObservationService assessmentScaleObservationService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public FunctionalStatusResultObservation showJson(@PathVariable("id") Long id) {
        FunctionalStatusResultObservation functionalStatusResultObservation = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return functionalStatusResultObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<FunctionalStatusResultObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<FunctionalStatusResultObservation> result = functionalStatusResultObservationService.findAllFunctionalStatusResultObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody FunctionalStatusResultObservation functionalStatusResultObservation) {
        functionalStatusResultObservationService.saveFunctionalStatusResultObservation(functionalStatusResultObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody FunctionalStatusResultObservation functionalStatusResultObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusResultObservationService.updateFunctionalStatusResultObservation(functionalStatusResultObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        FunctionalStatusResultObservation functionalStatusResultObservation = functionalStatusResultObservationService.findFunctionalStatusResultObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusResultObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        functionalStatusResultObservationService.deleteFunctionalStatusResultObservation(functionalStatusResultObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
