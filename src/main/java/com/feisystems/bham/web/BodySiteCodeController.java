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

import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.service.reference.BodySiteCodeService;

@Controller
@RequestMapping("/bodysitecodes")
public class BodySiteCodeController {

	@Autowired
    BodySiteCodeService bodySiteCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public BodySiteCode showJson(@PathVariable("id") Long id) {
        BodySiteCode bodySiteCode = bodySiteCodeService.findBodySiteCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return bodySiteCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<BodySiteCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<BodySiteCode> result = bodySiteCodeService.findAllBodySiteCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody BodySiteCode bodySiteCode) {
        bodySiteCodeService.saveBodySiteCode(bodySiteCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody  BodySiteCode bodySiteCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (bodySiteCodeService.updateBodySiteCode(bodySiteCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        BodySiteCode bodySiteCode = bodySiteCodeService.findBodySiteCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (bodySiteCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        bodySiteCodeService.deleteBodySiteCode(bodySiteCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
