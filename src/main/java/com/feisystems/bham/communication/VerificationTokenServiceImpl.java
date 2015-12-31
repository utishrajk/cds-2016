package com.feisystems.bham.communication;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.domain.provider.IndividualProviderRepository;


@Service("verificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	
	IndividualProviderRepository individualProviderRepository;
	
	VerificationTokenRepository tokenRepository;
	
	EmailServicesGateway emailServicesGateway;
	
	ApplicationConfig config;
	
	@Autowired
	public VerificationTokenServiceImpl(IndividualProviderRepository individualProviderRepository, EmailServicesGateway emailServicesGateway, VerificationTokenRepository tokenRepository) {
		this.individualProviderRepository = individualProviderRepository;
		this.emailServicesGateway = emailServicesGateway;
		this.tokenRepository = tokenRepository;
	}
	
	@Transactional
	public VerificationToken sendEmailRegistrationToken(String userName) {
	    IndividualProvider user = ensureUserIsLoaded(userName);
	    
	    VerificationToken token = new VerificationToken(user, VerificationToken.VerificationTokenType.emailRegistration);
	    user.addVerificationToken(token);
	    individualProviderRepository.save(user);
	    //TODO: Retrieve url from a properties file
	    emailServicesGateway.sendVerificationToken(new EmailServiceTokenModel(user, token, "qa-cds.cfapps.io"));
	    return token;
	}
	
	@Transactional
    public VerificationToken verify(String base64EncodedToken) {
        VerificationToken token = loadToken(base64EncodedToken);
        
        token.setVerified(true);
        token.getUser().setVerified(true);
        individualProviderRepository.save(token.getUser());
        return token;
    }
	
	private VerificationToken loadToken(String base64EncodedToken) {
        String rawToken = new String(Base64.decodeBase64(base64EncodedToken));
        VerificationToken token = tokenRepository.findByToken(rawToken);
        
        if (token == null) {
            throw new TokenNotFoundException("Token Not Found");
        }
        
        return token;
    }
	
	IndividualProvider ensureUserIsLoaded(String userName) {
		IndividualProvider user = individualProviderRepository.findByUserName(userName);
		
        if (user == null) {
        	throw new UsernameNotFoundException("Username Not Found");
        }
        
        return user;
    }
	
	

}
