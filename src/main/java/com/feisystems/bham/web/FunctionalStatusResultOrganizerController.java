package com.feisystems.bham.web;

import com.feisystems.bham.domain.clinicaldata.FunctionalStatusResultOrganizer;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultObservationService;
import com.feisystems.bham.service.clinicaldata.FunctionalStatusResultOrganizerService;
import com.feisystems.bham.service.patient.PatientService;
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
@RequestMapping("/functionalstatusresultorganizers")
public class FunctionalStatusResultOrganizerController {

	@Autowired
    FunctionalStatusResultOrganizerService functionalStatusResultOrganizerService;

	@Autowired
    FunctionalStatusResultObservationService functionalStatusResultObservationService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public FunctionalStatusResultOrganizer showJson(@PathVariable("id") Long id) {
        FunctionalStatusResultOrganizer functionalStatusResultOrganizer = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return functionalStatusResultOrganizer;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<FunctionalStatusResultOrganizer> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<FunctionalStatusResultOrganizer> result = functionalStatusResultOrganizerService.findAllFunctionalStatusResultOrganizers();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody FunctionalStatusResultOrganizer functionalStatusResultOrganizer) {
        functionalStatusResultOrganizerService.saveFunctionalStatusResultOrganizer(functionalStatusResultOrganizer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody FunctionalStatusResultOrganizer functionalStatusResultOrganizer, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusResultOrganizerService.updateFunctionalStatusResultOrganizer(functionalStatusResultOrganizer) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        FunctionalStatusResultOrganizer functionalStatusResultOrganizer = functionalStatusResultOrganizerService.findFunctionalStatusResultOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (functionalStatusResultOrganizer == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        functionalStatusResultOrganizerService.deleteFunctionalStatusResultOrganizer(functionalStatusResultOrganizer);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
