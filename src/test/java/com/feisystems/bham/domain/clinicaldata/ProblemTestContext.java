package com.feisystems.bham.domain.clinicaldata;


import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.reference.ActStatusCodeService;
import com.feisystems.bham.service.reference.ProblemCodeService;
import com.feisystems.bham.web.ActStatusCodeController;
import com.feisystems.bham.web.ProblemCodeController;
import com.feisystems.bham.web.ProblemController;

@Configuration
@EnableWebMvc
public class ProblemTestContext extends WebMvcConfigurerAdapter {
	
private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public ProblemController problemController() {
        return new ProblemController();
    }

    @Bean
    public ProblemCodeController problemCodeController() {
        return new ProblemCodeController();
    }
    
    @Bean
    public ActStatusCodeController actStatusCodeController() {
        return new ActStatusCodeController();
    }
    
    @Bean
    public ProblemService problemService() {
    	return Mockito.mock(ProblemService.class);
    }
    
    @Bean
    public ProblemCodeService problemCodeService() {
        return Mockito.mock(ProblemCodeService.class);
    }
    
    @Bean
    public ActStatusCodeService actStatusCodeService() {
        return Mockito.mock(ActStatusCodeService.class);
    }

}
