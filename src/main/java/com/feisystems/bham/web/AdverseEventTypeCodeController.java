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

import com.feisystems.bham.domain.reference.AdverseEventTypeCode;
import com.feisystems.bham.service.reference.AdverseEventTypeCodeService;

@Controller
@RequestMapping("/adverseeventtypecodes")
public class AdverseEventTypeCodeController {

	@Autowired
    AdverseEventTypeCodeService adverseEventTypeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public AdverseEventTypeCode showJson(@PathVariable("id") Long id) {
        AdverseEventTypeCode adverseEventTypeCode = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return adverseEventTypeCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AdverseEventTypeCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AdverseEventTypeCode> result = adverseEventTypeCodeService.findAllAdverseEventTypeCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody AdverseEventTypeCode adverseEventTypeCode) {
        adverseEventTypeCodeService.saveAdverseEventTypeCode(adverseEventTypeCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody AdverseEventTypeCode adverseEventTypeCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (adverseEventTypeCodeService.updateAdverseEventTypeCode(adverseEventTypeCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AdverseEventTypeCode adverseEventTypeCode = adverseEventTypeCodeService.findAdverseEventTypeCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (adverseEventTypeCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        adverseEventTypeCodeService.deleteAdverseEventTypeCode(adverseEventTypeCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
