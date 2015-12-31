package com.feisystems.bham.domain.reference;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class SocialHistoryTypeCodeDataOnDemand {
	
private Random rnd = new SecureRandom();
	
	private List<SocialHistoryTypeCode> data;
	
	@Autowired
	SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository;
	
	public SocialHistoryTypeCode getRandomSocialHistoryTypeCode() {
		init();
        SocialHistoryTypeCode obj = data.get(rnd.nextInt(data.size()));
        String code = obj.getCode();
        return socialHistoryTypeCodeRepository.findByCode(code);
    }
	
	public void init() {
        data = socialHistoryTypeCodeRepository.findAll();
        
        if(data.size() == 0) {
        	SocialHistoryTypeCode code = new SocialHistoryTypeCode();
			code.setCode("code");
			code.setDisplayName("displayName");
			
			data.add(code);
			socialHistoryTypeCodeRepository.save(code);
		}
    }

}
