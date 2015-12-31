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

import com.feisystems.bham.domain.clinicaldata.Medication;
import com.feisystems.bham.service.clinicaldata.MedicationService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.BodySiteCodeService;
import com.feisystems.bham.service.reference.MedicationDeliveryMethodCodeService;
import com.feisystems.bham.service.reference.ProductFormCodeService;
import com.feisystems.bham.service.reference.RouteCodeService;

@Controller
@RequestMapping("/medications")
public class MedicationController {
	
	@Autowired
    MedicationService medicationService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@Autowired
    BodySiteCodeService bodySiteCodeService;

	@Autowired
    MedicationDeliveryMethodCodeService medicationDeliveryMethodCodeService;

	@Autowired
    ProductFormCodeService productFormCodeService;

	@Autowired
    RouteCodeService routeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Medication showJson(@PathVariable("id") Long id) {
        Medication medication = medicationService.findMedication(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return medication;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<Medication> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Medication> result = medicationService.findAllMedications();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody Medication medication) {
        medicationService.saveMedication(medication);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody Medication medication, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (medicationService.updateMedication(medication) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Medication medication = medicationService.findMedication(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (medication == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        medicationService.deleteMedication(medication);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
