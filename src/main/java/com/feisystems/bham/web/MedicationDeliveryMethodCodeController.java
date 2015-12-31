package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCode;
import com.feisystems.bham.service.reference.MedicationDeliveryMethodCodeService;

@Controller
@RequestMapping("/medicationdeliverymethodcodes")
public class MedicationDeliveryMethodCodeController {

	@Autowired
    MedicationDeliveryMethodCodeService medicationDeliveryMethodCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public MedicationDeliveryMethodCode showJson(@PathVariable("id") Long id) {
        MedicationDeliveryMethodCode medicationDeliveryMethodCode = medicationDeliveryMethodCodeService.findMedicationDeliveryMethodCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return medicationDeliveryMethodCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<MedicationDeliveryMethodCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<MedicationDeliveryMethodCode> result = medicationDeliveryMethodCodeService.findAllMedicationDeliveryMethodCodes();
        return result;
    }

}
