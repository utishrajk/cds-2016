package com.feisystems.bham.domain.commondomainservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.feisystems.bham.service.dto.SmsDto;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

@Service("smsServiceImpl")
public class SmsServiceImpl implements SmsService {

	 /* Find your sid and token at twilio.com/user/account */
    public static final String ACCOUNT_SID = "AC626223b21d98ded4002a96a277c9169a";
    public static final String AUTH_TOKEN = "780ecf212bc58f4cfe1bc06fd7ecff3f";
    public static final String SMS_BODY = "Please ensure that the patient's blood pressure is measured and controlled.";
    public static final String FROM_PHONE = "+14432750062";
    
    private static Logger log = LoggerFactory
            .getLogger(SmsServiceImpl.class);

	@Override
	public void sendSms(SmsDto smsDto) {

	    try {
	    	
	    	TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	   	 
	        Account account = client.getAccount();
	 
	        MessageFactory messageFactory = account.getMessageFactory();
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("To",smsDto.getTo())); 
	        params.add(new BasicNameValuePair("From", FROM_PHONE)); 
	        params.add(new BasicNameValuePair("Body", SMS_BODY));
	        Message sms = messageFactory.create(params);

	        log.debug("Sent SMS: " + sms.getBody() + "to: " + sms.getTo());
	    	
	    } catch (TwilioRestException ex) {
	    	log.debug(ex.toString());
	    }
		
	}

	
	
}
