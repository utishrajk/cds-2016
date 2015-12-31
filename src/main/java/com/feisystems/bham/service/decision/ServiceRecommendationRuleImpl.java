package com.feisystems.bham.service.decision;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.runtime.StatefulKnowledgeSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.feisystems.bham.domain.decisionfacts.Patient;
import com.feisystems.bham.domain.decisionfacts.ProblemCode;
import com.feisystems.bham.domain.decisionfacts.RuleExecutionContainer;
import com.feisystems.bham.domain.decisionfacts.SocialHistoryCode;


import com.feisystems.bham.decision.util.DroolsResource;
import com.feisystems.bham.decision.util.DroolsUtil;
import com.feisystems.bham.decision.util.ResourcePathType;
import com.feisystems.bham.decision.util.TrackingAgendaEventListener;
import com.feisystems.bham.decision.util.TrackingWorkingMemoryEventListener;

/**
 * ServiceRecommendationRuleImpl
 */
@Service("ruleBasedRecommendationImpl")
public class ServiceRecommendationRuleImpl implements ServiceRecommendationRule {

    private static Logger log = LoggerFactory.getLogger(ServiceRecommendationRuleImpl.class);
    
    private KnowledgeBase kbase;
    
    public ServiceRecommendationRuleImpl() {
        this.kbase = DroolsUtil.createKnowledgeBase(
                new DroolsResource[]{ 
                        new DroolsResource("weka/rules/RecommendationRules.drl", 
                                ResourcePathType.CLASSPATH, 
                                ResourceType.DRL)
                }, 
                EventProcessingOption.CLOUD);
    }
        

	@Override
	public RuleExecutionContainer recommendService(String problemCode,
			String problemCodeSystem, String socialHistoryCode, String age, String race,
			String genderCode, String zipCode) {


    	StatefulKnowledgeSession session = null;
    	
	    log.debug("Evaluating Goals to Recommend \n");

	    try {
	    	
			    // BUILD KNOWLEDGEBASE, ADD SESSIONS AND SET GLOBAL CONTAINER
			    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
						.newKnowledgeBuilder();
		
				KnowledgeBase knowledgeBase = KnowledgeBaseFactory
						.newKnowledgeBase();
				knowledgeBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
			    session = kbase.newStatefulKnowledgeSession();
			    session.setGlobal("ruleExecutionContainer",
						new RuleExecutionContainer());
		
			    // ADD RULE EVENT LISTENER
			    TrackingAgendaEventListener agendaEventListener = 
			            new TrackingAgendaEventListener();
			    TrackingWorkingMemoryEventListener workingMemoryEventListener = 
			            new TrackingWorkingMemoryEventListener();
			    session.addEventListener(agendaEventListener);
			    session.addEventListener(workingMemoryEventListener);
		
			    // UPDATE AND INSERT FACT MODEL
			    ProblemCode pCode = new ProblemCode();
			    pCode.setCode(problemCode);
			    pCode.setCodeSystem(problemCodeSystem);
			    
			    SocialHistoryCode sCode = new SocialHistoryCode();
			    sCode.setCode(socialHistoryCode);

			    Patient patient = new Patient(genderCode, age, race, zipCode); 
			    
			    session.insert(pCode);
			    session.insert(sCode);
			    session.insert(patient);
			    
			    // FIRE RULES
			    session.fireAllRules();
				
			    // REMOVE RULE EVENT LISTENER
				session.removeEventListener(agendaEventListener);
				session.removeEventListener(workingMemoryEventListener);
				log.debug("Evaluating Goals Complete for");
				
				
	    }  catch (Exception e) {
			log.error(e.toString(), e);
		} finally {
			if (session != null) {
//				session.dispose();
			}
		}

	    // GET AND RETURN GLOBAL CONTAINER
		RuleExecutionContainer executionResponseContainer = (RuleExecutionContainer) session.getGlobal("ruleExecutionContainer");
	    return executionResponseContainer;
		
	}
	
}
