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

import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;
import com.feisystems.bham.service.dto.ProcedureObservationDto;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.BodySiteCodeService;

@Controller
@RequestMapping("/procedureobservations")
public class ProcedureObservationController {
	
	@Autowired
    private ProcedureObservationService procedureObservationService;

	@Autowired
    private PatientService patientService;

	@Autowired
    private ActStatusCodeService actStatusCodeService;

	@Autowired
    private BodySiteCodeService bodySiteCodeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ProcedureObservationDto showJson(@PathVariable("id") Long id) {
		ProcedureObservationDto result = procedureObservationService.findProcedureObservation(id);
		return result;
    }

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<ProcedureObservationDto> showJsonForPatient(@PathVariable("id") Long patientId) {
		List<ProcedureObservationDto> result = procedureObservationService.findAllProcedureObservationsByPatientId(patientId);
		return result;
    }
	
	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ProcedureObservationDto> listJson() {
        List<ProcedureObservationDto> result = procedureObservationService.findAllProcedureObservations();
		return result;
    }

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ProcedureObservationDto dto, @PathVariable("id") Long patientId) {
		dto.setPatientId(patientId);
		procedureObservationService.saveProcedureObservation(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ProcedureObservationDto dto, @PathVariable("id") Long id) {
		if(procedureObservationService.updateProcedureObservation(dto) == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		ProcedureObservationDto dto = procedureObservationService.findProcedureObservation(id);
		
		if(dto == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}
		
		procedureObservationService.deleteProcedureObservation(dto);
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
    }
	
	private HttpHeaders setUpHeaders() {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
		return headers;
	}


}
