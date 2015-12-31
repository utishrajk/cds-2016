package com.feisystems.bham.web;

import java.util.Collections;
import java.util.Comparator;
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

import com.feisystems.bham.service.clinicaldata.OutcomeService;
import com.feisystems.bham.service.dto.OutcomeDto;
import com.feisystems.bham.service.patient.PatientService;

@Controller
@RequestMapping("/outcomes")
public class OutcomeController {

	@Autowired
	private OutcomeService outcomeService;

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public OutcomeDto findOutcome(@PathVariable("id") Long id) {
		OutcomeDto outcome = outcomeService.findOutcome(id);
		return outcome;
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<OutcomeDto> findAllOutcomesByPatientId(@PathVariable("id") Long patientId) {
		List<OutcomeDto> result = outcomeService.findAllOutcomesByPatientId(patientId);
		Collections.sort(result, new Comparator<OutcomeDto>() {
			public int compare(OutcomeDto dto1, OutcomeDto dto2) {
				return dto1.getStartDate().compareTo(dto2.getStartDate());
			}
		});
		return result;
	}

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public List<OutcomeDto> findAllOutcomes() {
		List<OutcomeDto> result = outcomeService.findAllOutcomes();
		return result;
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> saveOutcome(@RequestBody OutcomeDto dto, @PathVariable("id") Long patientId) {
		dto.setPatientId(patientId);
		outcomeService.saveOutcome(dto);
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateOutcome(@RequestBody OutcomeDto dto, @PathVariable("id") Long id) {
		if (outcomeService.updateOutcome(dto) == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteOutcome(@PathVariable("id") Long id) {
		OutcomeDto dto = outcomeService.findOutcome(id);

		if (dto == null) {
			return new ResponseEntity<String>(setUpHeaders(), HttpStatus.NOT_FOUND);
		}

		outcomeService.deleteOutcome(dto);
		return new ResponseEntity<String>(setUpHeaders(), HttpStatus.OK);
	}

	private HttpHeaders setUpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return headers;
	}

}
