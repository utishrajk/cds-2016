package com.feisystems.bham.service.reference;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.reference.ProcedureTypeCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCodeRepository;
import com.feisystems.bham.service.dto.LookupDto;

@Service
@Transactional
public class ProcedureTypeCodeServiceImpl implements ProcedureTypeCodeService {

	@Autowired
	private ProcedureTypeCodeRepository procedureTypeCodeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<LookupDto> findAllProcedureTypeCodes() {
		List<LookupDto> lookups = new ArrayList<>();
		List<ProcedureTypeCode> procedureTypeCodeList = procedureTypeCodeRepository.findAll();
		
		for(ProcedureTypeCode entity : procedureTypeCodeList) {
			lookups.add(modelMapper.map(entity, LookupDto.class));
		}
		
		return lookups;
	}

}
