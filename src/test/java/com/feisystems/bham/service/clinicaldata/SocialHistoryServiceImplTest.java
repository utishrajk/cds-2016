package com.feisystems.bham.service.clinicaldata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.SocialHistoryRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.SocialHistoryDto;

@RunWith(MockitoJUnitRunner.class)
public class SocialHistoryServiceImplTest {

	@InjectMocks
	private SocialHistoryService service = new SocialHistoryServiceImpl();
	
	@Mock
	private SocialHistoryRepository socialHistoryRepository;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private DtoToDomainEntityMapper<SocialHistoryDto, SocialHistory> socialHistoryDtoToSocialHistoryMapper;
	
	@Test
	public void testFindProcedure() {
		// Arrange
		final SocialHistory socialHistory = mock(SocialHistory.class);
		when(socialHistoryRepository.findOne(anyLong())).thenReturn(socialHistory);
		final SocialHistoryDto expected = mock(SocialHistoryDto.class);
		when(modelMapper.map(socialHistory, SocialHistoryDto.class)).thenReturn(expected);

		// Act
		SocialHistoryDto actual = service.findSocialHistory(anyLong());

		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindAllSocialHistories() {
		// Arrange
		final SocialHistory socialHistory1 = mock(SocialHistory.class);
		final SocialHistory socialHistory2 = mock(SocialHistory.class);

		List<SocialHistory> list = new ArrayList<>();
		list.add(socialHistory1);
		list.add(socialHistory2);

		when(socialHistoryRepository.findAll()).thenReturn(list);

		final SocialHistoryDto expected1 = mock(SocialHistoryDto.class);
		final SocialHistoryDto expected2 = mock(SocialHistoryDto.class);
		when(modelMapper.map(socialHistory1, SocialHistoryDto.class)).thenReturn(expected1);
		when(modelMapper.map(socialHistory2, SocialHistoryDto.class)).thenReturn(expected2);

		// Act
		List<SocialHistoryDto> actual = service.findAllSocialHistorys();

		// Assert
		assertEquals(list.size(), actual.size());

		for (SocialHistoryDto dto : actual) {
			assertTrue(dto == expected1 || dto == expected2);
		}

		assertTrue(actual.get(0) != actual.get(1));
	}
	
	@Test
	public void testSaveSocialHistory() {
		// Arrange
		final SocialHistoryDto dto = mock(SocialHistoryDto.class);
		final SocialHistory SocialHistory = mock(SocialHistory.class);
		final SocialHistory SocialHistory2 = mock(SocialHistory.class);

		when(socialHistoryDtoToSocialHistoryMapper.map(dto)).thenReturn(SocialHistory);

		// Act
		service.saveSocialHistory(dto);

		// Assert
		verify(socialHistoryRepository, times(1)).save(SocialHistory);
		verify(socialHistoryRepository, times(0)).save(SocialHistory2);
	}

	@Test
	public void testUpdateSocialHistory() {
		// Arrange
		final SocialHistoryDto dto = mock(SocialHistoryDto.class);
		final SocialHistory SocialHistory = mock(SocialHistory.class);
		when(socialHistoryDtoToSocialHistoryMapper.map(dto)).thenReturn(SocialHistory);

		// Act
		service.updateSocialHistory(dto);

		// Assert
		verify(socialHistoryRepository, times(1)).save(SocialHistory);
	}
	
	@Test
	public void testDeleteSocialHistory() {
		// Arrange
		final SocialHistoryDto dto = mock(SocialHistoryDto.class);

		// Act
		service.deleteSocialHistory(dto);

		// Assert
		verify(socialHistoryRepository, times(1)).delete(dto.getId());
	}
	
	@Test
	public void testFindAllProblemsByPatientId() {
		// Arrange
		Patient patient = mock(Patient.class);

		Set<SocialHistory> SocialHistorys = new HashSet<>();

		SocialHistory socialHistory1 = mock(SocialHistory.class);
		SocialHistory socialHistory2 = mock(SocialHistory.class);

		SocialHistorys.add(socialHistory1);
		SocialHistorys.add(socialHistory2);

		when(patient.getSocialHistories()).thenReturn(SocialHistorys);

		when(patientRepository.findOne(patient.getId())).thenReturn(patient);

		SocialHistoryDto dto1 = mock(SocialHistoryDto.class);
		SocialHistoryDto dto2 = mock(SocialHistoryDto.class);

		when(modelMapper.map(socialHistory1, SocialHistoryDto.class)).thenReturn(dto1);
		when(modelMapper.map(socialHistory2, SocialHistoryDto.class)).thenReturn(dto2);

		// Act
		List<SocialHistoryDto> actual = service.findAllSocialHistorysByPatientId(patient.getId());

		// Assert
		assertEquals(SocialHistorys.size(), actual.size());

		for (SocialHistoryDto dto : actual) {
			assertTrue(dto == dto1 || dto == dto2);
		}
	}
	
}
