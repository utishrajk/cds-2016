package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.LanguageProficiencyCode;
import com.feisystems.bham.service.reference.LanguageProficiencyCodeService;

@Controller
@RequestMapping("/languageproficiencycodes")
public class LanguageProficiencyCodeController {

	@Autowired
    LanguageProficiencyCodeService languageProficiencyCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public LanguageProficiencyCode showJson(@PathVariable("id") Long id) {
        LanguageProficiencyCode languageProficiencyCode = languageProficiencyCodeService.findLanguageProficiencyCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return languageProficiencyCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public  List<LanguageProficiencyCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<LanguageProficiencyCode> result = languageProficiencyCodeService.findAllLanguageProficiencyCodes();
        return result;
    }

}
