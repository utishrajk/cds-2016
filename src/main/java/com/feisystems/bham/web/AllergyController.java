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

import com.feisystems.bham.domain.clinicaldata.Allergy;
import com.feisystems.bham.service.clinicaldata.AllergyService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.AdverseEventTypeCodeService;
import com.feisystems.bham.service.reference.AllergyReactionCodeService;
import com.feisystems.bham.service.reference.AllergySeverityCodeService;

@Controller
@RequestMapping("/allergys")
public class AllergyController {

	@Autowired
    AllergyService allergyService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    AdverseEventTypeCodeService adverseEventTypeCodeService;

	@Autowired
    AllergyReactionCodeService allergyReactionCodeService;

	@Autowired
    AllergySeverityCodeService allergySeverityCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Allergy showJson(@PathVariable("id") Long id) {
        Allergy allergy = allergyService.findAllergy(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return allergy;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<Allergy> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Allergy> result = allergyService.findAllAllergys();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody Allergy allergy) {
        allergyService.saveAllergy(allergy);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody Allergy allergy, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergyService.updateAllergy(allergy) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Allergy allergy = allergyService.findAllergy(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergy == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        allergyService.deleteAllergy(allergy);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
