package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.LanguageCode;
import com.feisystems.bham.service.reference.LanguageCodeService;

@Controller
@RequestMapping("/languagecodes")
public class LanguageCodeController {

	@Autowired
    LanguageCodeService languageCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public LanguageCode showJson(@PathVariable("id") Long id) {
        LanguageCode languageCode = languageCodeService.findLanguageCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return languageCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<LanguageCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<LanguageCode> result = languageCodeService.findAllLanguageCodes();
        return result;
    }

}
