package com.feisystems.bham.service.clinicaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.SocialHistoryRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.SocialHistoryTypeCode;
import com.feisystems.bham.domain.reference.SocialHistoryTypeCodeRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.SocialHistoryDto;

@Component
public class SocialHistoryDtoToSocialHistoryMapper implements DtoToDomainEntityMapper<SocialHistoryDto, SocialHistory> {

	private SocialHistoryRepository socialHistoryRepository;

	private SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository;

	private PatientRepository patientRepository;

	private ActStatusCodeRepository actStatusCodeRepository;

	@Autowired
	public SocialHistoryDtoToSocialHistoryMapper(SocialHistoryRepository socialHistoryRepository,
			SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository, PatientRepository patientRepository,
			ActStatusCodeRepository actStatusCodeRepository) {
		super();
		this.socialHistoryRepository = socialHistoryRepository;
		this.socialHistoryTypeCodeRepository = socialHistoryTypeCodeRepository;
		this.patientRepository = patientRepository;
		this.actStatusCodeRepository = actStatusCodeRepository;
	}

	@Override
	public SocialHistory map(SocialHistoryDto dto) {

		SocialHistory socialHistory = null;

		if (dto.getId() != null) {
			socialHistory = socialHistoryRepository.findOne(dto.getId());
		} else {
			socialHistory = new SocialHistory();
		}

		if (socialHistory == null) {
			return null;
		}

		socialHistory.setSocialHistoryStartDate(dto.getStartDate());
		socialHistory.setSocialHistoryEndDate(dto.getEndDate());

		if (StringUtils.hasText(dto.getSocialHistoryTypeCode())) {
			SocialHistoryTypeCode socialHistoryTypeCode = socialHistoryTypeCodeRepository.findByCode(dto.getSocialHistoryTypeCode());

			socialHistory.setSocialHistoryTypeCode(socialHistoryTypeCode);
		}

		if (StringUtils.hasText(dto.getSocialHistoryStatusCode())) {
			ActStatusCode socialHistoryStatusCode = actStatusCodeRepository.findByCode(dto.getSocialHistoryStatusCode());

			socialHistory.setSocialHistoryStatusCode(socialHistoryStatusCode);
		}

		Patient patient = patientRepository.findOne(dto.getPatientId());
		socialHistory.setPatient(patient);

		return socialHistory;

	}

}
