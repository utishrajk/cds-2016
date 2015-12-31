package com.feisystems.bham.service.reference;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.reference.CgiSCode;
import com.feisystems.bham.domain.reference.CgiSCodeRepository;
import com.feisystems.bham.service.dto.LookupDto;

@Service
@Transactional
public class CgiSCodeServiceImpl implements CgiSCodeService {

	@Autowired
	CgiSCodeRepository cgiSRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<LookupDto> findAllCgiSCodes() {
		List<LookupDto> lookups = new ArrayList<>();

		List<CgiSCode> cgiSCodeList = cgiSRepository.findAll();

		for (CgiSCode entity : cgiSCodeList) {
			lookups.add(modelMapper.map(entity, LookupDto.class));
		}

		return lookups;
	}

}
