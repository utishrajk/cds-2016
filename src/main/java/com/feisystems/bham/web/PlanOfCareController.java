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

import com.feisystems.bham.domain.clinicaldata.PlanOfCare;
import com.feisystems.bham.service.clinicaldata.PatientGoalService;
import com.feisystems.bham.service.clinicaldata.PlanOfCareService;
import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;
import com.feisystems.bham.service.patient.PatientService;

@Controller
@RequestMapping("/planofcares")
public class PlanOfCareController {

	@Autowired
    PlanOfCareService planOfCareService;

	@Autowired
    PatientGoalService patientGoalService;

	@Autowired
    ProblemService problemService;

	@Autowired
    ProcedureObservationService procedureObservationService;

	@Autowired
    PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public PlanOfCare showJson(@PathVariable("id") Long id) {
        PlanOfCare planOfCare = planOfCareService.findPlanOfCare(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return planOfCare;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<PlanOfCare> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PlanOfCare> result = planOfCareService.findAllPlanOfCares();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody PlanOfCare planOfCare) {
        planOfCareService.savePlanOfCare(planOfCare);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody PlanOfCare planOfCare, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (planOfCareService.updatePlanOfCare(planOfCare) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PlanOfCare planOfCare = planOfCareService.findPlanOfCare(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (planOfCare == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        planOfCareService.deletePlanOfCare(planOfCare);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
