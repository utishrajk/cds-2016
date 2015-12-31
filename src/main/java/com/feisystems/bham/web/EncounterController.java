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

import com.feisystems.bham.domain.clinicaldata.Encounter;
import com.feisystems.bham.service.clinicaldata.EncounterService;
import com.feisystems.bham.service.patient.PatientService;

@Controller
@RequestMapping("/encounters")
public class EncounterController {
	
	@Autowired
    EncounterService encounterService;

	@Autowired
    PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Encounter showJson(@PathVariable("id") Long id) {
        Encounter encounter = encounterService.findEncounter(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return encounter;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<Encounter> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Encounter> result = encounterService.findAllEncounters();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody Encounter encounter) {
        encounterService.saveEncounter(encounter);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody Encounter encounter, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (encounterService.updateEncounter(encounter) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Encounter encounter = encounterService.findEncounter(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (encounter == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        encounterService.deleteEncounter(encounter);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
