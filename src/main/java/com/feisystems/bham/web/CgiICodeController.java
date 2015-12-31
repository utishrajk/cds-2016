package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.reference.CgiICodeService;

@Controller
@RequestMapping("/cgiicodes")
public class CgiICodeController {

	@Autowired
	CgiICodeService cgiICodeService;

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public List<LookupDto> findAllCgiICodes() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		List<LookupDto> result = cgiICodeService.findAllCgiICodes();
		return result;

	}

}
