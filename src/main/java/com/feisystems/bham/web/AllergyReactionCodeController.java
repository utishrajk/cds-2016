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

import com.feisystems.bham.domain.reference.AllergyReactionCode;
import com.feisystems.bham.service.reference.AllergyReactionCodeService;

@Controller
@RequestMapping("/allergyreactioncodes")
public class AllergyReactionCodeController {

	@Autowired
    AllergyReactionCodeService allergyReactionCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AllergyReactionCode showJson(@PathVariable("id") Long id) {
        AllergyReactionCode allergyReactionCode = allergyReactionCodeService.findAllergyReactionCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return allergyReactionCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AllergyReactionCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AllergyReactionCode> result = allergyReactionCodeService.findAllAllergyReactionCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody AllergyReactionCode allergyReactionCode) {
        allergyReactionCodeService.saveAllergyReactionCode(allergyReactionCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody AllergyReactionCode allergyReactionCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergyReactionCodeService.updateAllergyReactionCode(allergyReactionCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AllergyReactionCode allergyReactionCode = allergyReactionCodeService.findAllergyReactionCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (allergyReactionCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        allergyReactionCodeService.deleteAllergyReactionCode(allergyReactionCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
