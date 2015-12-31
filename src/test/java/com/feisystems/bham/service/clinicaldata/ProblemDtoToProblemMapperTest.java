package com.feisystems.bham.service.clinicaldata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProblemRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.domain.reference.ActStatusCodeRepository;
import com.feisystems.bham.domain.reference.ProblemCodeRepository;
import com.feisystems.bham.service.dto.ProblemDto;

public class ProblemDtoToProblemMapperTest {

	private ProblemDtoToProblemMapper sut;

	private ProblemRepository problemRepository;

	private ProblemCodeRepository problemCodeRepository;

	private ActStatusCodeRepository actStatusCodeRepository;

	private PatientRepository patientRepository;

	@Before
	public void setUp() {
		problemRepository = mock(ProblemRepository.class);
		problemCodeRepository = mock(ProblemCodeRepository.class);
		actStatusCodeRepository = mock(ActStatusCodeRepository.class);
		patientRepository = mock(PatientRepository.class);

		sut = new ProblemDtoToProblemMapper(problemRepository, problemCodeRepository, actStatusCodeRepository, patientRepository);
	}

	// Update Path (when id is not null)
	@Test
	public void testMap() {
		ProblemDto dto = mock(ProblemDto.class);
		Problem problem = mock(Problem.class);

		when(dto.getId()).thenReturn(1L);
		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getProblemCode()).thenReturn("1");
		when(dto.getProblemDisplayName()).thenReturn("displayName1");
		when(dto.getProblemStatusCode()).thenReturn("1");
		when(dto.getProblemName()).thenReturn("problemName1");

		when(problemRepository.findOne(dto.getId())).thenReturn(problem);

		Problem actual = sut.map(dto);

		assertEquals(problem, actual);
	}

	// Create Path (when id is null)
	@Test
	public void testMapUnlucky() {
		ProblemDto dto = mock(ProblemDto.class);
		Patient patient = mock(Patient.class);
		when(patient.getId()).thenReturn(1L);

		when(dto.getPatientId()).thenReturn(1L);
		when(dto.getProblemCode()).thenReturn("1");
		when(dto.getProblemDisplayName()).thenReturn("displayName1");
		when(dto.getProblemStatusCode()).thenReturn("1");
		when(dto.getProblemName()).thenReturn("problemName1");

		when(dto.getId()).thenReturn(null);
		when(patientRepository.findOne(dto.getPatientId())).thenReturn(patient);

		Problem actual = sut.map(dto);

		Assert.assertNotNull(actual);
		assertEquals(dto.getPatientId(), actual.getPatient().getId());
	}
}
