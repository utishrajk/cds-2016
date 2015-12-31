package com.feisystems.bham.domain.clinicaldata;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.BodySiteCodeService;
import com.feisystems.bham.service.reference.ProcedureTypeCodeService;
import com.feisystems.bham.web.ActStatusCodeController;
import com.feisystems.bham.web.BodySiteCodeController;
import com.feisystems.bham.web.ProcedureObservationController;
import com.feisystems.bham.web.ProcedureTypeCodeController;

@Configuration
@EnableWebMvc
public class ProcedureTestContext extends WebMvcConfigurerAdapter {
	
	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
	}

	@Bean
	public ProcedureObservationController procedureObservationController() {
		return new ProcedureObservationController();
	}

	@Bean
	public ProcedureTypeCodeController procedureTypeCodeController() {
		return new ProcedureTypeCodeController();
	}

	@Bean
	public ActStatusCodeController actStatusCodeController() {
		return new ActStatusCodeController();
	}
	
	@Bean
	public BodySiteCodeController bodySiteCodeController() {
		return new BodySiteCodeController();
	}

	@Bean
	public ProcedureObservationService procedureObservationService() {
		return Mockito.mock(ProcedureObservationService.class);
	}

	@Bean
	public ProcedureTypeCodeService procedureCodeService() {
		return Mockito.mock(ProcedureTypeCodeService.class);
	}

	@Bean
	public ActStatusCodeService actStatusCodeService() {
		return Mockito.mock(ActStatusCodeService.class);
	}
	
	@Bean
	public BodySiteCodeService bodySiteCodeService() {
		return Mockito.mock(BodySiteCodeService.class);
	}

}
