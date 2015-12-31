package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.ReligiousAffiliationCode;
import com.feisystems.bham.service.reference.ReligiousAffiliationCodeService;

@Controller
@RequestMapping("/religiousaffiliationcodes")
public class ReligiousAffiliationCodeController {

	@Autowired
    ReligiousAffiliationCodeService religiousAffiliationCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ReligiousAffiliationCode showJson(@PathVariable("id") Long id) {
        ReligiousAffiliationCode religiousAffiliationCode = religiousAffiliationCodeService.findReligiousAffiliationCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return religiousAffiliationCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ReligiousAffiliationCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ReligiousAffiliationCode> result = religiousAffiliationCodeService.findAllReligiousAffiliationCodes();
        return result;
    }

}
