package com.feisystems.bham.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.decisionfacts.RuleExecutionContainer;
import com.feisystems.bham.service.decision.ServiceRecommendationRule;


@Controller("serviceRecommendationController")
@RequestMapping("/recommendation")
public class ServiceRecommendationControllerImpl implements
ServiceRecommendationController {

    private static Logger log = LoggerFactory
            .getLogger(ServiceRecommendationControllerImpl.class);

    @Autowired(required = true)
    @Qualifier("ruleBasedRecommendationImpl")
    private ServiceRecommendationRule recommend;

    @Override
    @RequestMapping(value = "/{problemCode}/{problemCodeSystem}/{age}/{raceCode}/{genderCode}/{socialHistoryCode}/{zipCode}", 
				method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    RuleExecutionContainer recommendService(@PathVariable String problemCode, @PathVariable String problemCodeSystem,
    										@PathVariable String socialHistoryCode,
    										@PathVariable String age, @PathVariable String raceCode, @PathVariable String genderCode, @PathVariable String zipCode) {
    	
    	
    	RuleExecutionContainer result = recommend.recommendService(problemCode, problemCodeSystem,socialHistoryCode,
    															   age, raceCode, genderCode, zipCode);
        
    	log.debug("Recommending Service: " + result.toString());
        return result;
    }

    
    public ServiceRecommendationRule getRecommend() {
        return recommend;
    }

    public void setRecommend(ServiceRecommendationRule recommend) {
        this.recommend = recommend;
    }

}
