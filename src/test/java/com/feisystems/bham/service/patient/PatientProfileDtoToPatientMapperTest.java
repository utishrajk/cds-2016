package com.feisystems.bham.service.patient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.AdministrativeGenderCodeRepository;
import com.feisystems.bham.domain.reference.CountryCodeRepository;
import com.feisystems.bham.domain.reference.MaritalStatusCodeRepository;
import com.feisystems.bham.domain.reference.RaceCodeRepository;
import com.feisystems.bham.domain.reference.ReligiousAffiliationCodeRepository;
import com.feisystems.bham.domain.reference.StateCodeRepository;
import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.dto.PatientProfileDto;

import org.junit.Before;
import org.junit.Test;

public class PatientProfileDtoToPatientMapperTest {

	private PatientProfileDtoToPatientMapper sut;

	private PatientRepository patientRepository;

	private StateCodeRepository stateCodeRepository;

	private CountryCodeRepository countryCodeRepository;

	private AdministrativeGenderCodeRepository administrativeGenderCodeRepository;

	private MaritalStatusCodeRepository maritalStatusCodeRepository;

	private RaceCodeRepository raceCodeRepository;

	private ReligiousAffiliationCodeRepository religiousAffiliationCodeRepository;

	@Before
	public void setUp() {
		patientRepository = mock(PatientRepository.class);
		stateCodeRepository = mock(StateCodeRepository.class);
		countryCodeRepository = mock(CountryCodeRepository.class);
		administrativeGenderCodeRepository = mock(AdministrativeGenderCodeRepository.class);
		maritalStatusCodeRepository = mock(MaritalStatusCodeRepository.class);
		raceCodeRepository = mock(RaceCodeRepository.class);
		religiousAffiliationCodeRepository = mock(ReligiousAffiliationCodeRepository.class);

		sut = new PatientProfileDtoToPatientMapper(patientRepository,
				stateCodeRepository, countryCodeRepository,
				administrativeGenderCodeRepository,
				maritalStatusCodeRepository, raceCodeRepository,
				religiousAffiliationCodeRepository);

	}

	// Happy Path
	@Test
	public void testMap() {
		PatientProfileDto patientDto = mock(PatientProfileDto.class);
		Patient patient = mock(Patient.class);
		
		when(patientDto.getId()).thenReturn(1L);
		when(patientDto.getFirstName()).thenReturn("Firstname");
		when(patientDto.getLastName()).thenReturn("Lastname");
		when(patientDto.getAddressStateCode()).thenReturn("NY");
		when(patientDto.getAddressPostalCode()).thenReturn("11111");
		when(patientDto.getAdministrativeGenderCode()).thenReturn("M");

		when(patientRepository.findOne(patientDto.getId())).thenReturn(patient);

		LookupDto languageCode = mock(LookupDto.class);
		when(languageCode.getCode()).thenReturn("US");
		
		LookupDto maritalStatusCode = mock(LookupDto.class);
		when(maritalStatusCode.getCode()).thenReturn("single");
		
		when(patientDto.getRaceCode()).thenReturn("Asian");
				
		Patient result = sut.map(patientDto);
		assertEquals(patient, result);
	}

	// Unhappy Path
	@Test
	public void testMap_Null_Patient_Id(){
		// Arrange
		PatientProfileDto patientDto = mock(PatientProfileDto.class);
		when(patientDto.getId()).thenReturn(null);
		
		final String firstName = "Firstname";
		when(patientDto.getFirstName()).thenReturn(firstName);
		
		// Act
		Patient patient = sut.map(patientDto);
		
		// Assert
		assertEquals(firstName, patient.getFirstName());
	}
	
}
