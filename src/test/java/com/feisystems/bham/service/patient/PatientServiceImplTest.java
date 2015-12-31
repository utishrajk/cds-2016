package com.feisystems.bham.service.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.PatientProfileDto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

	@InjectMocks
	private PatientService sut = new PatientServiceImpl();
	

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private ModelMapper modelMapper;


	@Mock
	private DtoToDomainEntityMapper<PatientProfileDto, Patient> patientProfileDtoToPatientMapper;
	
	@Test
	public void testFindPatient() {
		// Arrange
		final Patient patient = mock(Patient.class);
		when(patientRepository.findOne(anyLong())).thenReturn(patient);
		final PatientProfileDto expectedPatientProfileDto = mock(PatientProfileDto.class);
		when(modelMapper.map(patient, PatientProfileDto.class)).thenReturn(
				expectedPatientProfileDto);

		// Act
		PatientProfileDto result = sut.findPatient(anyLong());

		// Assert
		assertEquals(expectedPatientProfileDto, result);
	}

	@Test
	public void testFindPatientEntries() {
		// Arrange
		final Patient patient1 = mock(Patient.class);
		final Patient patient2 = mock(Patient.class);
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patient1);
		patientList.add(patient2);

		int numberOfPatients = patientList.size();
		
		when(patientRepository.findAll())
		.thenReturn(patientList);
		
		final PatientProfileDto expectedPatientProfileDto1 = mock(PatientProfileDto.class);
		when(modelMapper.map(patient1, PatientProfileDto.class)).thenReturn(
				expectedPatientProfileDto1);

		final PatientProfileDto expectedPatientProfileDto2 = mock(PatientProfileDto.class);
		when(modelMapper.map(patient2, PatientProfileDto.class)).thenReturn(
				expectedPatientProfileDto2);

		// Act
		List<PatientProfileDto> result = sut.findAllPatients();

		// Assert
		assertEquals(numberOfPatients, result.size());
		for (PatientProfileDto patientProfileDto : result) {
			assertTrue(patientProfileDto == expectedPatientProfileDto1
					|| patientProfileDto == expectedPatientProfileDto2);
		}

		assertTrue(result.get(0) != result.get(1));
	}

	@Test
	public void testSavePatient() {
		// Arrange
		PatientProfileDto patientProfileDtoInput = mock(PatientProfileDto.class);
		Patient patient = mock(Patient.class);
		when(patientProfileDtoToPatientMapper.map(patientProfileDtoInput))
				.thenReturn(patient);
		PatientProfileDto expectedPatientProfileDto = mock(PatientProfileDto.class);
		when(modelMapper.map(patient, PatientProfileDto.class)).thenReturn(
				expectedPatientProfileDto);

		// Act
		sut.savePatient(patientProfileDtoInput);

		// Assert
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	public void testUpdatePatient() {
		
		// Arrange
		PatientProfileDto patientProfileDtoInput = mock(PatientProfileDto.class);
		Patient patient = mock(Patient.class);
		when(patientProfileDtoInput.getFirstName()).thenReturn("albert");
		when(patientProfileDtoInput.getLastName()).thenReturn("Smith");
		when(patientProfileDtoToPatientMapper.map(patientProfileDtoInput))
				.thenReturn(patient);

		// Act
		sut.updatePatient(patientProfileDtoInput);

		// Assert
		verify(patientRepository, times(1)).save(patient);
	}
	

}
