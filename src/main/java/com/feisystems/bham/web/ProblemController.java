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

import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.dto.ProblemDto;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.ActStatusCodeService;


@Controller
@RequestMapping("/problems")
public class ProblemController {

	@Autowired
    ProblemService problemService;

	@Autowired
    PatientService patientService;

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ProblemDto showJson(@PathVariable("id") Long id) {
		ProblemDto problemDto = problemService.findProblem(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return problemDto;
    }
	
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<ProblemDto> retrieveJsonForPatient(@PathVariable("id") Long patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
		List<ProblemDto> result = problemService.findAllProblemsByPatientId(patientId);
		return result;
	}
	
	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ProblemDto> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ProblemDto> result = problemService.findAllProblems();
        return result;
    }
	
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ProblemDto problemDto, @PathVariable("id") Long patientId) {
		problemDto.setPatientId(patientId);
        problemService.saveProblem(problemDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ProblemDto problem, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (problemService.updateProblem(problem) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		ProblemDto problem = problemService.findProblem(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (problem == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        problemService.deleteProblem(problem);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
