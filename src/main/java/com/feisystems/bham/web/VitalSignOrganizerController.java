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

import com.feisystems.bham.domain.clinicaldata.VitalSignOrganizer;
import com.feisystems.bham.service.clinicaldata.VitalSignObservationService;
import com.feisystems.bham.service.clinicaldata.VitalSignOrganizerService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;

@Controller
@RequestMapping("/vitalsignorganizers")
public class VitalSignOrganizerController {

	@Autowired
    VitalSignOrganizerService vitalSignOrganizerService;

	@Autowired
    VitalSignObservationService vitalSignObservationService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public VitalSignOrganizer showJson(@PathVariable("id") Long id) {
        VitalSignOrganizer vitalSignOrganizer = vitalSignOrganizerService.findVitalSignOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return vitalSignOrganizer;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<VitalSignOrganizer> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<VitalSignOrganizer> result = vitalSignOrganizerService.findAllVitalSignOrganizers();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody VitalSignOrganizer vitalSignOrganizer) {
        vitalSignOrganizerService.saveVitalSignOrganizer(vitalSignOrganizer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody VitalSignOrganizer vitalSignOrganizer, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (vitalSignOrganizerService.updateVitalSignOrganizer(vitalSignOrganizer) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        VitalSignOrganizer vitalSignOrganizer = vitalSignOrganizerService.findVitalSignOrganizer(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (vitalSignOrganizer == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        vitalSignOrganizerService.deleteVitalSignOrganizer(vitalSignOrganizer);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
