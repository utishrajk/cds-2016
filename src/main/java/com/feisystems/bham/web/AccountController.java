package com.feisystems.bham.web;

import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.service.dto.IndividualProviderDto;
import com.feisystems.bham.service.provider.IndividualProviderService;
import com.feisystems.bham.domain.provider.Authority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

	/**
	 * REST controller for managing the current user's account.
	 */
	@RestController
	@RequestMapping("/app")
	public class AccountController {

	    private final Logger log = LoggerFactory.getLogger(AccountController.class);

		@Autowired
		private MessageSource messageSource;
		
	    @Inject
	    private IndividualProviderService individualProviderService;

	    /**
	     * GET  /rest/authenticate -> check if the user is authenticated, and return its login.
	     */
	    @RequestMapping(value = "/rest/authenticate",
	            method = RequestMethod.GET,
	            produces = "application/json")
	    public String isAuthenticated(HttpServletRequest request) {
	        log.debug("REST request to check if the current user is authenticated");
	        return request.getRemoteUser();
	    }

	    /**
	     * GET  /rest/account -> get the current user.
	     */
	    @RequestMapping(value = "/rest/account",
	            method = RequestMethod.GET,
	            produces = "application/json")
	    public IndividualProviderDto getAccount(HttpServletResponse response) {
	    	
	    	IndividualProvider individualProvider = individualProviderService.getUserWithAuthorities();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	    	
	        if (individualProvider == null) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            return null;
	        }
	    	
	    	List<String> roles = individualProvider.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
			IndividualProviderDto individualProviderDto = new IndividualProviderDto(individualProvider.getId(), 
																					individualProvider.getUserName(), 
																					individualProvider.getFirstName(), 
																					individualProvider.getLastName(),
																					individualProvider.getEmail(), roles);
			return individualProviderDto;
	    }

	    /**
	     * POST  /rest/account -> update the current user information.
	     */
	    @RequestMapping(value = "/rest/account",
	            method = RequestMethod.POST,
	            produces = "application/json")
	    public void saveAccount(@RequestBody IndividualProviderDto individualProviderDto) throws IOException {
	        individualProviderService.updateUserInformation(individualProviderDto.getFirstName(), individualProviderDto.getLastName(), individualProviderDto.getEmail());
	    }

	    /**
	     * POST  /rest/change_password -> changes the current user's password
	     */
	    @RequestMapping(value = "/rest/account/change_password",
	            method = RequestMethod.POST,
	            produces = "application/json")
	    public void changePassword(@RequestBody String password, HttpServletResponse response) throws IOException {
	        if (password == null || password.equals("")) {
	            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password should not be empty");
	        } else {
	            individualProviderService.changePassword(password);
	        }
	    }
	    
	    
	    /**
	     * GET sends back a json error message if the user is not authorized
	     */
	    @RequestMapping(value = "/403",
	            method = RequestMethod.GET,
	            produces = "application/json")
	    public ErrorInfo accessDeniedMessageHandler(HttpServletRequest request) {

	    	Locale locale = LocaleContextHolder.getLocale();
			String errorMessage = messageSource.getMessage("error.bad.authorization", null, locale);
			String errorURL = request.getRequestURL().toString();

			ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
	        return errorInfo;
	    }


	}
