package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.FacilityTypeCode;
import com.feisystems.bham.service.reference.FacilityTypeCodeService;

@Controller
@RequestMapping("/facilitytypecodes")
public class FacilityTypeCodeController {

	@Autowired
    FacilityTypeCodeService facilityTypeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public FacilityTypeCode showJson(@PathVariable("id") Long id) {
        FacilityTypeCode facilityTypeCode = facilityTypeCodeService.findFacilityTypeCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return facilityTypeCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<FacilityTypeCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<FacilityTypeCode> result = facilityTypeCodeService.findAllFacilityTypeCodes();
        return result;
    }

}
