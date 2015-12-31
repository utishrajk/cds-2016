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

import com.feisystems.bham.domain.clinicaldata.ResultObservation;
import com.feisystems.bham.service.clinicaldata.ResultObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.ResultInterpretationCodeService;

@Controller
@RequestMapping("/resultobservations")
public class ResultObservationController {

	@Autowired
    ResultObservationService resultObservationService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    ResultInterpretationCodeService resultInterpretationCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResultObservation showJson(@PathVariable("id") Long id) {
        ResultObservation resultObservation = resultObservationService.findResultObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return resultObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ResultObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ResultObservation> result = resultObservationService.findAllResultObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ResultObservation resultObservation) {
        resultObservationService.saveResultObservation(resultObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ResultObservation resultObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (resultObservationService.updateResultObservation(resultObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        ResultObservation resultObservation = resultObservationService.findResultObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (resultObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        resultObservationService.deleteResultObservation(resultObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
