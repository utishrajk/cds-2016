package com.feisystems.bham.service.clinicaldata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ProcedureObservationRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureObservationServiceImplTest {

	@InjectMocks
	private ProcedureObservationService service = new ProcedureObservationServiceImpl();

	@Mock
	private ProcedureObservationRepository procedureObservationRepository;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private DtoToDomainEntityMapper<ProcedureObservationDto, ProcedureObservation> procedureDtoToProcedureMapper;

	@Test
	public void testFindProcedure() {
		// Arrange
		final ProcedureObservation procedureObservation = mock(ProcedureObservation.class);
		when(procedureObservationRepository.findOne(anyLong())).thenReturn(procedureObservation);
		final ProcedureObservationDto expected = mock(ProcedureObservationDto.class);
		when(modelMapper.map(procedureObservation, ProcedureObservationDto.class)).thenReturn(expected);

		// Act
		ProcedureObservationDto actual = service.findProcedureObservation(anyLong());

		// Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindAllProcedures() {
		// Arrange
		final ProcedureObservation procedureObservation1 = mock(ProcedureObservation.class);
		final ProcedureObservation procedureObservation2 = mock(ProcedureObservation.class);

		List<ProcedureObservation> list = new ArrayList<>();
		list.add(procedureObservation1);
		list.add(procedureObservation2);

		when(procedureObservationRepository.findAll()).thenReturn(list);

		final ProcedureObservationDto expected1 = mock(ProcedureObservationDto.class);
		final ProcedureObservationDto expected2 = mock(ProcedureObservationDto.class);
		when(modelMapper.map(procedureObservation1, ProcedureObservationDto.class)).thenReturn(expected1);
		when(modelMapper.map(procedureObservation2, ProcedureObservationDto.class)).thenReturn(expected2);

		// Act
		List<ProcedureObservationDto> actual = service.findAllProcedureObservations();

		// Assert
		assertEquals(list.size(), actual.size());

		for (ProcedureObservationDto dto : actual) {
			assertTrue(dto == expected1 || dto == expected2);
		}

		assertTrue(actual.get(0) != actual.get(1));
	}
	
	@Test
	public void testSaveProcedure() {
		// Arrange
		final ProcedureObservationDto dto = mock(ProcedureObservationDto.class);
		final ProcedureObservation procedureObservation = mock(ProcedureObservation.class);
		final ProcedureObservation procedureObservation2 = mock(ProcedureObservation.class);

		when(procedureDtoToProcedureMapper.map(dto)).thenReturn(procedureObservation);

		// Act
		service.saveProcedureObservation(dto);

		// Assert
		verify(procedureObservationRepository, times(1)).save(procedureObservation);
		verify(procedureObservationRepository, times(0)).save(procedureObservation2);
	}
	
	@Test
	public void testUpdateProcedure() {
		// Arrange
		final ProcedureObservationDto dto = mock(ProcedureObservationDto.class);
		final ProcedureObservation procedureObservation = mock(ProcedureObservation.class);
		when(procedureDtoToProcedureMapper.map(dto)).thenReturn(procedureObservation);

		// Act
		service.updateProcedureObservation(dto);

		// Assert
		verify(procedureObservationRepository, times(1)).save(procedureObservation);
	}
	
	@Test
	public void testDeleteProcedure() {
		// Arrange
		final ProcedureObservationDto dto = mock(ProcedureObservationDto.class);

		// Act
		service.deleteProcedureObservation(dto);

		// Assert
		verify(procedureObservationRepository, times(1)).delete(dto.getId());
	}
	
	
	@Test
	public void testFindAllProceduresByPatientId() {
		// Arrange
		Patient patient = mock(Patient.class);

		Set<ProcedureObservation> procedureObservations = new HashSet<>();

		ProcedureObservation procedureObservation1 = mock(ProcedureObservation.class);
		ProcedureObservation procedureObservation2 = mock(ProcedureObservation.class);

		procedureObservations.add(procedureObservation1);
		procedureObservations.add(procedureObservation2);

		when(patient.getProcedureObservations()).thenReturn(procedureObservations);

		when(patientRepository.findOne(patient.getId())).thenReturn(patient);

		ProcedureObservationDto dto1 = mock(ProcedureObservationDto.class);
		ProcedureObservationDto dto2 = mock(ProcedureObservationDto.class);

		when(modelMapper.map(procedureObservation1, ProcedureObservationDto.class)).thenReturn(dto1);
		when(modelMapper.map(procedureObservation2, ProcedureObservationDto.class)).thenReturn(dto2);

		// Act
		List<ProcedureObservationDto> actual = service.findAllProcedureObservationsByPatientId(patient.getId());

		// Assert
		assertEquals(procedureObservations.size(), actual.size());

		for (ProcedureObservationDto dto : actual) {
			assertTrue(dto == dto1 || dto == dto2);
		}
	}
	
	

}
