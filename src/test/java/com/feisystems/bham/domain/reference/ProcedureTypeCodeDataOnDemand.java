package com.feisystems.bham.domain.reference;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class ProcedureTypeCodeDataOnDemand {
	
	private Random rnd = new SecureRandom();
	
	private List<ProcedureTypeCode> data;
	
	@Autowired
	ProcedureTypeCodeRepository procedureTypeCodeRepository;
	
	public ProcedureTypeCode getRandomProcedureTypeCode() {
		init();
		ProcedureTypeCode obj = data.get(rnd.nextInt(data.size()));
		String code = obj.getCode();
		return procedureTypeCodeRepository.findByCode(code);
	}
	
	public void init() {
		data = procedureTypeCodeRepository.findAll();
		
		if(data.size() == 0) {
			ProcedureTypeCode code = new ProcedureTypeCode();
			code.setCode("code");
			code.setDisplayName("displayName");
			
			data.add(code);
			procedureTypeCodeRepository.save(code);
		}
	}

}
