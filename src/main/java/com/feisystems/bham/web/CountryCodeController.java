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

import com.feisystems.bham.domain.reference.CountryCode;
import com.feisystems.bham.service.reference.CountryCodeService;

@Controller
@RequestMapping("/countrycodes")
public class CountryCodeController {

	@Autowired
    CountryCodeService countryCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public CountryCode showJson(@PathVariable("id") Long id) {
        CountryCode countryCode = countryCodeService.findCountryCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return countryCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<CountryCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<CountryCode> result = countryCodeService.findAllCountryCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody CountryCode countryCode) {
        countryCodeService.saveCountryCode(countryCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody CountryCode countryCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (countryCodeService.updateCountryCode(countryCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        CountryCode countryCode = countryCodeService.findCountryCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (countryCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        countryCodeService.deleteCountryCode(countryCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
