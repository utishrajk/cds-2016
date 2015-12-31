package com.feisystems.bham.communication;


public interface VerificationTokenService {
	
	public VerificationToken sendEmailRegistrationToken(String userName);
	
	public VerificationToken verify(String base64EncodedToken);
	
}