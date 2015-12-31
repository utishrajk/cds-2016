package com.feisystems.bham.domain.commondomainservices;

import com.feisystems.bham.service.dto.SmsDto;

public interface SmsService {
	
	public void sendSms(SmsDto smsDto);

}
