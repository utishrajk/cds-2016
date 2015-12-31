package com.feisystems.bham.service.clinicaldata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.SocialHistoryRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.SocialHistoryDto;

@Service
@Transactional(rollbackFor = { ClinicalDataNotFoundException.class })
public class SocialHistoryServiceImpl implements SocialHistoryService {

	@Autowired
    private SocialHistoryRepository socialHistoryRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DtoToDomainEntityMapper<SocialHistoryDto, SocialHistory> socialHistoryDtoToSocialHistoryMapper;
	
	public SocialHistoryDto findSocialHistory(Long id) {
        SocialHistory socialHistory = socialHistoryRepository.findOne(id);
        
        if(socialHistory != null) {
        	return modelMapper.map(socialHistory, SocialHistoryDto.class);
        }
        throw new ClinicalDataNotFoundException(id);
    }
	
	@Override
	public List<SocialHistoryDto> findAllSocialHistorys() {
		List<SocialHistoryDto> socialHistoryDtoList = new ArrayList<>();
		List<SocialHistory> socialHistoryList = socialHistoryRepository.findAll();
		
		if(socialHistoryList.isEmpty()) {
			throw new IllegalArgumentException("No Social History Found");
		}
		
		for(SocialHistory socialHistory : socialHistoryList) {
			SocialHistoryDto socialHistoryDto = modelMapper.map(socialHistory, SocialHistoryDto.class);
			socialHistoryDtoList.add(socialHistoryDto);
		}
		
		return socialHistoryDtoList;
	}
	
	@Override
	public void saveSocialHistory(SocialHistoryDto dto) {
		SocialHistory socialHistory = socialHistoryDtoToSocialHistoryMapper.map(dto);
		socialHistoryRepository.save(socialHistory);
	}
	
	@Override
	public SocialHistory updateSocialHistory(SocialHistoryDto dto) {
		SocialHistory socialHistory = socialHistoryDtoToSocialHistoryMapper.map(dto);
		
		if(socialHistory == null) {
			throw new ClinicalDataNotFoundException(dto.getId());
		}
		
		return socialHistoryRepository.save(socialHistory);
	}


	@Override
	public void deleteSocialHistory(SocialHistoryDto dto) {
		socialHistoryRepository.delete(dto.getId());
	}

	@Override
	public List<SocialHistoryDto> findAllSocialHistorysByPatientId(Long patientId) {
		List<SocialHistoryDto> socialHistoryDtoList = new ArrayList<>();
		Patient patient = patientRepository.findOne(patientId);
		
		Set<SocialHistory> socialHistoryList = patient.getSocialHistories();
		
		for(SocialHistory socialHistory : socialHistoryList) {
			SocialHistoryDto socialHistoryDto = modelMapper.map(socialHistory, SocialHistoryDto.class);
			socialHistoryDtoList.add(socialHistoryDto);
		}
		
		return socialHistoryDtoList;
	}
	
}
