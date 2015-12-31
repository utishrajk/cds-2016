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

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusProblemObservation;
import com.feisystems.bham.service.clinicaldata.AssessmentScaleObservationService;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusProblemObservationService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

@Controller
@RequestMapping("/functionalstatusproblemobservations")
public class FunctionalStatusProblemObservationController {
	
	@Autowired
    FunctionalStatusProblemObservationService functionalStatusProblemObservationService;

	@Autowired
    AssessmentScaleObservationService assessmentScaleObservationService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public FunctionalStatusProblemObservation showJson(@PathVariable("id") Long id) {
        FunctionalStatusProblemObservation functionalStatusProblemObservation = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return functionalStatusProblemObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<FunctionalStatusProblemObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<FunctionalStatusProblemObservation> result = functionalStatusProblemObservationService.findAllFunctionalStatusProblemObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody FunctionalStatusProblemObservation functionalStatusProblemObservation) {
        functionalStatusProblemObservationService.saveFunctionalStatusProblemObservation(functionalStatusProblemObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody FunctionalStatusProblemObservation functionalStatusProblemObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusProblemObservationService.updateFunctionalStatusProblemObservation(functionalStatusProblemObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        FunctionalStatusProblemObservation functionalStatusProblemObservation = functionalStatusProblemObservationService.findFunctionalStatusProblemObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusProblemObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        functionalStatusProblemObservationService.deleteFunctionalStatusProblemObservation(functionalStatusProblemObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
