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

import com.feisystems.bham.domain.clinicaldata.PatientClinicalDocument;
import com.feisystems.bham.service.clinicaldata.PatientClinicalDocumentService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.PatientClinicalDocumentTypeCodeService;

@Controller
@RequestMapping("/patientclinicaldocuments")
public class PatientClinicalDocumentController {

	@Autowired
	PatientClinicalDocumentService patientClinicalDocumentService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	PatientClinicalDocumentTypeCodeService patientClinicalDocumentTypeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public PatientClinicalDocument showJson(@PathVariable("id") Long id) {
        PatientClinicalDocument patientClinicalDocument = patientClinicalDocumentService.findPatientClinicalDocument(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return patientClinicalDocument;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<PatientClinicalDocument> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PatientClinicalDocument> result = patientClinicalDocumentService.findAllPatientClinicalDocuments();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody PatientClinicalDocument patientClinicalDocument) {
        patientClinicalDocumentService.savePatientClinicalDocument(patientClinicalDocument);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody PatientClinicalDocument patientClinicalDocument, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (patientClinicalDocumentService.updatePatientClinicalDocument(patientClinicalDocument) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PatientClinicalDocument patientClinicalDocument = patientClinicalDocumentService.findPatientClinicalDocument(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (patientClinicalDocument == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        patientClinicalDocumentService.deletePatientClinicalDocument(patientClinicalDocument);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
