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

import com.feisystems.bham.domain.clinicaldata.PatientGoal;
import com.feisystems.bham.service.clinicaldata.PatientGoalService;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.BodySiteCodeService;

@Controller
@RequestMapping("/patientgoals")
public class PatientGoalController {

	@Autowired
    PatientGoalService patientGoalService;

	@Autowired
    PatientService patientService;

	@Autowired
    BodySiteCodeService bodySiteCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public PatientGoal showJson(@PathVariable("id") Long id) {
        PatientGoal patientGoal = patientGoalService.findPatientGoal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return patientGoal;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<PatientGoal> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PatientGoal> result = patientGoalService.findAllPatientGoals();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody PatientGoal patientGoal) {
        patientGoalService.savePatientGoal(patientGoal);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody  PatientGoal patientGoal, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (patientGoalService.updatePatientGoal(patientGoal) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PatientGoal patientGoal = patientGoalService.findPatientGoal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (patientGoal == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        patientGoalService.deletePatientGoal(patientGoal);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
