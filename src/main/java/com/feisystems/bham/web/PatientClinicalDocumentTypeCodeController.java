package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCode;
import com.feisystems.bham.service.reference.PatientClinicalDocumentTypeCodeService;

@Controller
@RequestMapping("/patientclinicaldocumenttypecodes")
public class PatientClinicalDocumentTypeCodeController {

	@Autowired
    PatientClinicalDocumentTypeCodeService patientClinicalDocumentTypeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public PatientClinicalDocumentTypeCode showJson(@PathVariable("id") Long id) {
        PatientClinicalDocumentTypeCode patientClinicalDocumentTypeCode = patientClinicalDocumentTypeCodeService.findPatientClinicalDocumentTypeCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return patientClinicalDocumentTypeCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<PatientClinicalDocumentTypeCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PatientClinicalDocumentTypeCode> result = patientClinicalDocumentTypeCodeService.findAllPatientClinicalDocumentTypeCodes();
        return result;
    }

}
