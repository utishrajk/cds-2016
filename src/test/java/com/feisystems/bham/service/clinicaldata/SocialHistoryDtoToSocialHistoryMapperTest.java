package com.feisystems.bham.service.clinicaldata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.SocialHistoryRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.SocialHistoryTypeCodeRepository;
import com.feisystems.bham.service.dto.SocialHistoryDto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SocialHistoryDtoToSocialHistoryMapperTest {

	private SocialHistoryDtoToSocialHistoryMapper sut;

	private SocialHistoryRepository socialHistoryRepository;

	private SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository;

	private ActStatusCodeRepository actStatusCodeRepository;

	private PatientRepository patientRepository;

	@Before
	public void setUp() {
		socialHistoryRepository = mock(SocialHistoryRepository.class);
		socialHistoryTypeCodeRepository = mock(SocialHistoryTypeCodeRepository.class);
		actStatusCodeRepository = mock(ActStatusCodeRepository.class);
		patientRepository = mock(PatientRepository.class);

		sut = new SocialHistoryDtoToSocialHistoryMapper(socialHistoryRepository, socialHistoryTypeCodeRepository, patientRepository, actStatusCodeRepository);
	}

	// Update Path (when id is not null)
	@Test
	public void testMap() {
		SocialHistoryDto dto = mock(SocialHistoryDto.class);
		SocialHistory socialHistory = mock(SocialHistory.class);

		when(dto.getId()).thenReturn(1L);
		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getSocialHistoryTypeCode()).thenReturn("1");
		when(dto.getSocialHistoryTypeName()).thenReturn("typeName1");
		when(dto.getSocialHistoryStatusCode()).thenReturn("1");
		when(dto.getSocialHistoryStatusName()).thenReturn("statusName1");

		when(socialHistoryRepository.findOne(dto.getId())).thenReturn(socialHistory);

		SocialHistory actual = sut.map(dto);

		assertEquals(socialHistory, actual);
	}

	// Create Path (when id is null)
	@Test
	public void testMapUnlucky() {
		SocialHistoryDto dto = mock(SocialHistoryDto.class);
		Patient patient = mock(Patient.class);
		when(patient.getId()).thenReturn(1L);

		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getSocialHistoryTypeCode()).thenReturn("1");
		when(dto.getSocialHistoryTypeName()).thenReturn("typeName1");
		when(dto.getSocialHistoryStatusCode()).thenReturn("1");
		when(dto.getSocialHistoryStatusName()).thenReturn("statusName1");

		when(dto.getId()).thenReturn(null);
		when(patientRepository.findOne(dto.getPatientId())).thenReturn(patient);

		SocialHistory actual = sut.map(dto);

		Assert.assertNotNull(actual);
		assertEquals(dto.getPatientId(), actual.getPatient().getId());
	}

}
