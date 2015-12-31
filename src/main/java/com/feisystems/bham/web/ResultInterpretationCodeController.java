package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.ResultInterpretationCode;
import com.feisystems.bham.service.reference.ResultInterpretationCodeService;

@Controller
@RequestMapping("/resultinterpretationcodes")
public class ResultInterpretationCodeController {

	@Autowired
    ResultInterpretationCodeService resultInterpretationCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResultInterpretationCode showJson(@PathVariable("id") Long id) {
        ResultInterpretationCode resultInterpretationCode = resultInterpretationCodeService.findResultInterpretationCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return resultInterpretationCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ResultInterpretationCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ResultInterpretationCode> result = resultInterpretationCodeService.findAllResultInterpretationCodes();
        return result;
    }

}
