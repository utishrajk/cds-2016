package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.EthnicGroupCode;
import com.feisystems.bham.service.reference.EthnicGroupCodeService;

@Controller
@RequestMapping("/ethnicgroupcodes")
public class EthnicGroupCodeController {

	@Autowired
    EthnicGroupCodeService ethnicGroupCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public EthnicGroupCode showJson(@PathVariable("id") Long id) {
        EthnicGroupCode ethnicGroupCode = ethnicGroupCodeService.findEthnicGroupCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return ethnicGroupCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<EthnicGroupCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<EthnicGroupCode> result = ethnicGroupCodeService.findAllEthnicGroupCodes();
        return result;
    }

}
