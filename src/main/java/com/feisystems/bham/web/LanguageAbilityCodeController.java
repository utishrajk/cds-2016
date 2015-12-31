package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.LanguageAbilityCode;
import com.feisystems.bham.service.reference.LanguageAbilityCodeService;

@Controller
@RequestMapping("/languageabilitycodes")
public class LanguageAbilityCodeController {

	@Autowired
    LanguageAbilityCodeService languageAbilityCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public LanguageAbilityCode showJson(@PathVariable("id") Long id) {
        LanguageAbilityCode languageAbilityCode = languageAbilityCodeService.findLanguageAbilityCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return languageAbilityCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<LanguageAbilityCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<LanguageAbilityCode> result = languageAbilityCodeService.findAllLanguageAbilityCodes();
        return result;
    }

}
