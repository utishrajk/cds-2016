package com.feisystems.bham.service.clinicaldata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ProcedureObservationRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.BodySiteCodeRepository;
import com.feisystems.bham.domain.reference.ProcedureTypeCodeRepository;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

public class ProcedureDtoToProcedureMapperTest {

	private ProcedureDtoToProcedureMapper sut;

	private ProcedureObservationRepository procedureObservationRepository;

	private ProcedureTypeCodeRepository procedureTypeCodeRepository;

	private ActStatusCodeRepository actStatusCodeRepository;

	private PatientRepository patientRepository;

	private BodySiteCodeRepository bodySiteCodeRepository;

	@Before
	public void setUp() {
		procedureObservationRepository = mock(ProcedureObservationRepository.class);
		procedureTypeCodeRepository = mock(ProcedureTypeCodeRepository.class);
		actStatusCodeRepository = mock(ActStatusCodeRepository.class);
		patientRepository = mock(PatientRepository.class);
		bodySiteCodeRepository = mock(BodySiteCodeRepository.class);

		sut = new ProcedureDtoToProcedureMapper(procedureObservationRepository, procedureTypeCodeRepository, patientRepository, actStatusCodeRepository,
				bodySiteCodeRepository);
	}

	// Update Path (when id is not null)
	@Test
	public void testMap() {
		ProcedureObservationDto dto = mock(ProcedureObservationDto.class);
		ProcedureObservation procedure = mock(ProcedureObservation.class);

		when(dto.getId()).thenReturn(1L);
		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getProcedureTypeCode()).thenReturn("1");
		when(dto.getProcedureTypeName()).thenReturn("typeName1");
		when(dto.getProcedureStatusCode()).thenReturn("1");

		when(procedureObservationRepository.findOne(dto.getId())).thenReturn(procedure);

		ProcedureObservation actual = sut.map(dto);

		assertEquals(procedure, actual);
	}

	// Create Path (when id is null)
	@Test
	public void testMapUnlucky() {
		ProcedureObservationDto dto = mock(ProcedureObservationDto.class);
		Patient patient = mock(Patient.class);
		when(patient.getId()).thenReturn(1L);

		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getProcedureTypeCode()).thenReturn("1");
		when(dto.getProcedureTypeName()).thenReturn("typeName1");
		when(dto.getProcedureStatusCode()).thenReturn("1");
		when(dto.getBodySiteCode()).thenReturn("1");

		when(dto.getId()).thenReturn(null);
		when(patientRepository.findOne(dto.getPatientId())).thenReturn(patient);

		ProcedureObservation actual = sut.map(dto);

		Assert.assertNotNull(actual);
		assertEquals(dto.getPatientId(), actual.getPatient().getId());
	}
}
