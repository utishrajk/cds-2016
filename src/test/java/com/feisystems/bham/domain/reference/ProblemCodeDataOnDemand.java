package com.feisystems.bham.domain.reference;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class ProblemCodeDataOnDemand {
	private Random rnd = new SecureRandom();
	
	private List<ProblemCode> data;
	
	@Autowired
	ProblemCodeRepository problemCodeRepository;
	
	public ProblemCode getRandomProblemCode() {
		init();
        ProblemCode obj = data.get(rnd.nextInt(data.size()));
        String code = obj.getCode();
        return problemCodeRepository.findByCode(code);
    }
	
	public void init() {
        data = problemCodeRepository.findAll();
        
        if(data.size() == 0) {
        	ProblemCode code = new ProblemCode();
			code.setCode("code");
			code.setDisplayName("displayName");
			
			data.add(code);
			problemCodeRepository.save(code);
		}
    }
}
