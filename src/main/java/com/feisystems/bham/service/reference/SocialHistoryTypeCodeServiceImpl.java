package com.feisystems.bham.service.reference;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.reference.SocialHistoryTypeCode;
import com.feisystems.bham.domain.reference.SocialHistoryTypeCodeRepository;
import com.feisystems.bham.service.dto.LookupDto;

@Service
@Transactional
public class SocialHistoryTypeCodeServiceImpl implements SocialHistoryTypeCodeService {

	@Autowired
    private SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<LookupDto> findAllSocialHistoryTypeCodes() {
		List<LookupDto> lookups = new ArrayList<>();
		List<SocialHistoryTypeCode> socialHistoryTypeCodeList = socialHistoryTypeCodeRepository.findAll();
		
		for(SocialHistoryTypeCode entity : socialHistoryTypeCodeList) {
			lookups.add(modelMapper.map(entity, LookupDto.class));
			
		}
		
		return lookups;
	}

}
