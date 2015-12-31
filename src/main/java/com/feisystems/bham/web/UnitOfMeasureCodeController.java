package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.service.reference.UnitOfMeasureCodeService;

@Controller
@RequestMapping("/unitofmeasurecodes")
public class UnitOfMeasureCodeController {

	@Autowired
    UnitOfMeasureCodeService unitOfMeasureCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public UnitOfMeasureCode showJson(@PathVariable("id") Long id) {
        UnitOfMeasureCode unitOfMeasureCode = unitOfMeasureCodeService.findUnitOfMeasureCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return unitOfMeasureCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<UnitOfMeasureCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<UnitOfMeasureCode> result = unitOfMeasureCodeService.findAllUnitOfMeasureCodes();
        return result;
    }
}
