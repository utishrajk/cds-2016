package com.feisystems.bham.web;

import com.feisystems.bham.domain.decisionfacts.RuleExecutionContainer;


public interface ServiceRecommendationController {

	// Todo: Make dto
	RuleExecutionContainer recommendService(String problemCode,
			String problemCodeSystem, String socialHistoryCode, String age, String race,
			String genderCode, String zipCode); 

}
