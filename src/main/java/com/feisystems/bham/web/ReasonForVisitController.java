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

import com.feisystems.bham.domain.clinicaldata.ReasonForVisit;
import com.feisystems.bham.service.clinicaldata.ReasonForVisitService;
import com.feisystems.bham.service.patient.PatientService;

@Controller
@RequestMapping("/reasonforvisits")
public class ReasonForVisitController {

	@Autowired
    ReasonForVisitService reasonForVisitService;

	@Autowired
    PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ReasonForVisit showJson(@PathVariable("id") Long id) {
        ReasonForVisit reasonForVisit = reasonForVisitService.findReasonForVisit(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return reasonForVisit;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ReasonForVisit> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ReasonForVisit> result = reasonForVisitService.findAllReasonForVisits();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ReasonForVisit reasonForVisit) {
        reasonForVisitService.saveReasonForVisit(reasonForVisit);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ReasonForVisit reasonForVisit, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (reasonForVisitService.updateReasonForVisit(reasonForVisit) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        ReasonForVisit reasonForVisit = reasonForVisitService.findReasonForVisit(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (reasonForVisit == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        reasonForVisitService.deleteReasonForVisit(reasonForVisit);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
