package com.feisystems.bham.service.reference;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.reference.CgiICode;
import com.feisystems.bham.domain.reference.CgiICodeRepository;
import com.feisystems.bham.service.dto.LookupDto;

@Service
@Transactional
public class CgiICodeServiceImpl implements CgiICodeService {

	@Autowired
	CgiICodeRepository cgiIRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<LookupDto> findAllCgiICodes() {
		List<LookupDto> lookups = new ArrayList<>();

		List<CgiICode> cgiICodeList = cgiIRepository.findAll();

		for (CgiICode entity : cgiICodeList) {
			lookups.add(modelMapper.map(entity, LookupDto.class));
		}

		return lookups;
	}

}
