package com.feisystems.bham.weka;

import java.util.ArrayList;
import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AttTest {

	 /* Find your sid and token at twilio.com/user/account */
    public static final String ACCOUNT_SID = "AC626223b21d98ded4002a96a277c9169a";
    public static final String AUTH_TOKEN = "780ecf212bc58f4cfe1bc06fd7ecff3f";
 
    public static void main(String[] args) throws TwilioRestException {
 
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
 
        Account account = client.getAccount();
 
        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+14437912189")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("From", "+14432750062")); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("Body", "Testing Twilio"));
        Message sms = messageFactory.create(params);
    }
}
