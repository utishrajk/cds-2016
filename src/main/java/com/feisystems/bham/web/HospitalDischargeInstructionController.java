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

import com.feisystems.bham.domain.clinicaldata.HospitalDischargeInstruction;
import com.feisystems.bham.service.clinicaldata.HospitalDischargeInstructionService;
import com.feisystems.bham.service.patient.PatientService;

@Controller
@RequestMapping("/hospitaldischargeinstructions")
public class HospitalDischargeInstructionController {

	@Autowired
    HospitalDischargeInstructionService hospitalDischargeInstructionService;

	@Autowired
    PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public HospitalDischargeInstruction showJson(@PathVariable("id") Long id) {
        HospitalDischargeInstruction hospitalDischargeInstruction = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return hospitalDischargeInstruction;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<HospitalDischargeInstruction> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<HospitalDischargeInstruction> result = hospitalDischargeInstructionService.findAllHospitalDischargeInstructions();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody HospitalDischargeInstruction hospitalDischargeInstruction) {
        hospitalDischargeInstructionService.saveHospitalDischargeInstruction(hospitalDischargeInstruction);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody HospitalDischargeInstruction hospitalDischargeInstruction, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (hospitalDischargeInstructionService.updateHospitalDischargeInstruction(hospitalDischargeInstruction) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        HospitalDischargeInstruction hospitalDischargeInstruction = hospitalDischargeInstructionService.findHospitalDischargeInstruction(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (hospitalDischargeInstruction == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        hospitalDischargeInstructionService.deleteHospitalDischargeInstruction(hospitalDischargeInstruction);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
