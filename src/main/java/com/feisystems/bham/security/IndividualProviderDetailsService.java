package com.feisystems.bham.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.communication.UserNotVerifiedException;
import com.feisystems.bham.domain.provider.Authority;
import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.domain.provider.IndividualProviderRepository;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class IndividualProviderDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(IndividualProviderDetailsService.class);
   public static final String ROLE_USER = "ROLE_USER";

    @Inject
    private IndividualProviderRepository individualProviderRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        try {

        	log.debug("Authenticating {}", login);
        	String lowercaseLogin = login.toLowerCase();

        	IndividualProvider userFromDatabase = individualProviderRepository.findByUserName(login);
        
			if (userFromDatabase == null) {
	            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
	        } else if(!userFromDatabase.isVerified()) {
	        	throw new UserNotVerifiedException("User has not been verified. Please check your email your email to verify your email.");
	        }
	
	        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	        for (Authority authority : userFromDatabase.getAuthorities()) {
	            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
	            grantedAuthorities.add(grantedAuthority);
	        }

	        return new User(lowercaseLogin, userFromDatabase.getCredential(),
	                grantedAuthorities);

        } catch (Exception e) {
        	e.printStackTrace();
        }

        return null;

    }
    
    
}
