package com.feisystems.bham.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feisystems.bham.domain.commondomainservices.SmsService;
import com.feisystems.bham.service.dto.SmsDto;

@Controller("sendSmsController")
@RequestMapping("/sms")
public class SendSmsControllerImpl {
	    
    @Autowired(required = true)
    @Qualifier("smsServiceImpl")
    private SmsService smsService;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody SmsDto smsDto) {
		smsService.sendSms(smsDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
 
    }

	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

}
