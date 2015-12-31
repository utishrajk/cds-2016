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

import com.feisystems.bham.domain.clinicaldata.ResultOrganizer;
import com.feisystems.bham.service.clinicaldata.ResultObservationService;
import com.feisystems.bham.service.clinicaldata.ResultOrganizerService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

@Controller
@RequestMapping("/resultorganizers")
public class ResultOrganizerController {

	@Autowired
    ResultOrganizerService resultOrganizerService;

	@Autowired
    ResultObservationService resultObservationService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResultOrganizer showJson(@PathVariable("id") Long id) {
        ResultOrganizer resultOrganizer = resultOrganizerService.findResultOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return resultOrganizer;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ResultOrganizer> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ResultOrganizer> result = resultOrganizerService.findAllResultOrganizers();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ResultOrganizer resultOrganizer) {
        resultOrganizerService.saveResultOrganizer(resultOrganizer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ResultOrganizer resultOrganizer, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (resultOrganizerService.updateResultOrganizer(resultOrganizer) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        ResultOrganizer resultOrganizer = resultOrganizerService.findResultOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (resultOrganizer == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        resultOrganizerService.deleteResultOrganizer(resultOrganizer);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
