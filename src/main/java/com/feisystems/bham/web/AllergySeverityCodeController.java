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

import com.feisystems.bham.domain.reference.AllergySeverityCode;
import com.feisystems.bham.service.reference.AllergySeverityCodeService;

@Controller
@RequestMapping("/allergyseveritycodes")
public class AllergySeverityCodeController {

	@Autowired
    AllergySeverityCodeService allergySeverityCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AllergySeverityCode showJson(@PathVariable("id") Long id) {
        AllergySeverityCode allergySeverityCode = allergySeverityCodeService.findAllergySeverityCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return allergySeverityCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AllergySeverityCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AllergySeverityCode> result = allergySeverityCodeService.findAllAllergySeverityCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody AllergySeverityCode allergySeverityCode) {
        allergySeverityCodeService.saveAllergySeverityCode(allergySeverityCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody AllergySeverityCode allergySeverityCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergySeverityCodeService.updateAllergySeverityCode(allergySeverityCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AllergySeverityCode allergySeverityCode = allergySeverityCodeService.findAllergySeverityCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergySeverityCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        allergySeverityCodeService.deleteAllergySeverityCode(allergySeverityCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
