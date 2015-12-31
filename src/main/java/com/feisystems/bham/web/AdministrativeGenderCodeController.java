package com.feisystems.bham.web;

import com.feisystems.bham.domain.reference.AdministrativeGenderCode;
import com.feisystems.bham.service.reference.AdministrativeGenderCodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/administrativegendercodes")
public class AdministrativeGenderCodeController {

	@Autowired
    AdministrativeGenderCodeService administrativeGenderCodeService;

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<AdministrativeGenderCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AdministrativeGenderCode> result = administrativeGenderCodeService.findAllAdministrativeGenderCodes();
        return result;
    }

}
