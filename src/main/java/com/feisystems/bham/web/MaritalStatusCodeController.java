package com.feisystems.bham.web;

import com.feisystems.bham.domain.reference.MaritalStatusCode;
import com.feisystems.bham.service.reference.MaritalStatusCodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/maritalstatuscodes")
public class MaritalStatusCodeController {

	@Autowired
    MaritalStatusCodeService maritalStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public MaritalStatusCode showJson(@PathVariable("id") Long id) {
        MaritalStatusCode maritalStatusCode = maritalStatusCodeService.findMaritalStatusCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return maritalStatusCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<MaritalStatusCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<MaritalStatusCode> result = maritalStatusCodeService.findAllMaritalStatusCodes();
        return result;
    }

}
