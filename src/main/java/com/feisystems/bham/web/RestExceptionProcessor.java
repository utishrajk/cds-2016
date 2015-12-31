package com.feisystems.bham.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.feisystems.bham.service.clinicaldata.ClinicalDataNotFoundException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@ControllerAdvice
public class RestExceptionProcessor {

	@Autowired
	private MessageSource messageSource;

	Locale locale = LocaleContextHolder.getLocale();
	
	@ExceptionHandler(ClinicalDataNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo patientNotFound(HttpServletRequest req, ClinicalDataNotFoundException ex) {
		
		String errorMessage = messageSource.getMessage("error.clinicaldatanotfound", null, locale);

		errorMessage += ex.getPatientId();
		String errorURL = req.getRequestURL().toString();
		
		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
        return errorInfo;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo requestMethodNotSupported(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {

		String errorMessage = messageSource.getMessage("error.bad.url", null, locale);
		String errorURL = req.getRequestURL().toString();
		
		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
        return errorInfo;
	}

	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo requestHandlingMethodNotSupported(HttpServletRequest req, NoSuchRequestHandlingMethodException ex) {
	
		String errorMessage = messageSource.getMessage("error.bad.url", null, locale);
		String errorURL = req.getRequestURL().toString();
		
		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
		return errorInfo;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.url", null, locale);

		String errorURL = req.getRequestURL().toString();
		
		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
        return errorInfo;
	}
	
	@ExceptionHandler(JpaSystemException.class)
	@ResponseStatus(value=HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorInfo integrityConstraintViolated(HttpServletRequest req, JpaSystemException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("exception.jpasystemexception", null, locale);
		
		if(ex.getMostSpecificCause().getClass().equals(MySQLIntegrityConstraintViolationException.class)) {
			errorMessage = messageSource.getMessage("exception.duplicate.entry", null, locale);
		} 

		String errorURL = req.getRequestURL().toString();
		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
		
        return errorInfo;
	}
	
}
;