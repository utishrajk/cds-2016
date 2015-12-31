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

import com.feisystems.bham.service.clinicaldata.SocialHistoryService;
import com.feisystems.bham.service.dto.SocialHistoryDto;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.SocialHistoryTypeCodeService;

@Controller
@RequestMapping("/socialhistorys")
public class SocialHistoryController {
	
	@Autowired
    private SocialHistoryService socialHistoryService;

	@Autowired
    private PatientService patientService;

	@Autowired
    private ActStatusCodeService actStatusCodeService;

	@Autowired
    private SocialHistoryTypeCodeService socialHistoryTypeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public SocialHistoryDto showJson(@PathVariable("id") Long id) {
		SocialHistoryDto result = socialHistoryService.findSocialHistory(id);
        return result;
    }
	
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<SocialHistoryDto> showJsonForPatient(@PathVariable("id") Long patientId) {
        List<SocialHistoryDto> result = socialHistoryService.findAllSocialHistorysByPatientId(patientId);
		return result;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<SocialHistoryDto> listJson() {
        List<SocialHistoryDto> result = socialHistoryService.findAllSocialHistorys();
		return result;
    }

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody SocialHistoryDto dto, @PathVariable("id") Long patientId) {
		dto.setPatientId(patientId);
		socialHistoryService.saveSocialHistory(dto);
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody SocialHistoryDto dto, @PathVariable("id") Long id) {
		if(socialHistoryService.updateSocialHistory(dto) == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		SocialHistoryDto dto = socialHistoryService.findSocialHistory(id);
		if(dto == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}
		socialHistoryService.deleteSocialHistory(dto);
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
    }
	
	private HttpHeaders setUpHeaders() {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
		return headers;
	}

}
