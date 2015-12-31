package com.feisystems.bham.web;

public interface SendSmsController {
	
	void sendSms(String individualProviderNumber, String messageBody);

}
