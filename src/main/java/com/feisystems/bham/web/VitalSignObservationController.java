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

import com.feisystems.bham.domain.clinicaldata.VitalSignObservation;
import com.feisystems.bham.service.clinicaldata.VitalSignObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.ResultInterpretationCodeService;

@Controller
@RequestMapping("/vitalsignobservations")
public class VitalSignObservationController {

	@Autowired
    VitalSignObservationService vitalSignObservationService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    ResultInterpretationCodeService resultInterpretationCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public VitalSignObservation showJson(@PathVariable("id") Long id) {
        VitalSignObservation vitalSignObservation = vitalSignObservationService.findVitalSignObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return vitalSignObservation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<VitalSignObservation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<VitalSignObservation> result = vitalSignObservationService.findAllVitalSignObservations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody VitalSignObservation vitalSignObservation) {
        vitalSignObservationService.saveVitalSignObservation(vitalSignObservation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody VitalSignObservation vitalSignObservation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (vitalSignObservationService.updateVitalSignObservation(vitalSignObservation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        VitalSignObservation vitalSignObservation = vitalSignObservationService.findVitalSignObservation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (vitalSignObservation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        vitalSignObservationService.deleteVitalSignObservation(vitalSignObservation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
