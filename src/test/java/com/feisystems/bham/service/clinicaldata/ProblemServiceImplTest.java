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

import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProblemRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.clinicaldata.ProblemServiceImpl;
import com.feisystems.bham.service.dto.ProblemDto;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

	@InjectMocks
	private ProblemService service = new ProblemServiceImpl();

	@Mock
	private ProblemRepository problemRepository;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private DtoToDomainEntityMapper<ProblemDto, Problem> problemDtoToProblemMapper;

	@Test
	public void testFindProblem() {
		// Arrange
		final Problem problem = mock(Problem.class);
		when(problemRepository.findOne(anyLong())).thenReturn(problem);
		final ProblemDto expected = mock(ProblemDto.class);
		when(modelMapper.map(problem, ProblemDto.class)).thenReturn(expected);

		// Act
		ProblemDto actual = service.findProblem(anyLong());

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void testFindAllProblems() {
		// Arrange
		final Problem problem1 = mock(Problem.class);
		final Problem problem2 = mock(Problem.class);

		List<Problem> list = new ArrayList<>();
		list.add(problem1);
		list.add(problem2);

		when(problemRepository.findAll()).thenReturn(list);

		final ProblemDto expected1 = mock(ProblemDto.class);
		final ProblemDto expected2 = mock(ProblemDto.class);
		when(modelMapper.map(problem1, ProblemDto.class)).thenReturn(expected1);
		when(modelMapper.map(problem2, ProblemDto.class)).thenReturn(expected2);

		// Act
		List<ProblemDto> actual = service.findAllProblems();

		// Assert
		assertEquals(list.size(), actual.size());

		for (ProblemDto dto : actual) {
			assertTrue(dto == expected1 || dto == expected2);
		}

		assertTrue(actual.get(0) != actual.get(1));
	}

	@Test
	public void testSaveProblem() {
		// Arrange
		final ProblemDto dto = mock(ProblemDto.class);
		final Problem problem = mock(Problem.class);
		final Problem problem2 = mock(Problem.class);

		when(problemDtoToProblemMapper.map(dto)).thenReturn(problem);

		// Act
		service.saveProblem(dto);

		// Assert
		verify(problemRepository, times(1)).save(problem);
		verify(problemRepository, times(0)).save(problem2);
	}

	@Test
	public void testUpdateProblem() {
		// Arrange
		final ProblemDto dto = mock(ProblemDto.class);
		final Problem problem = mock(Problem.class);
		when(problemDtoToProblemMapper.map(dto)).thenReturn(problem);

		// Act
		service.updateProblem(dto);

		// Assert
		verify(problemRepository, times(1)).save(problem);
	}

	@Test
	public void testDeleteProblem() {
		// Arrange
		final ProblemDto dto = mock(ProblemDto.class);

		// Act
		service.deleteProblem(dto);

		// Assert
		verify(problemRepository, times(1)).delete(dto.getId());
	}

	@Test
	public void testFindAllProblemsByPatientId() {
		// Arrange
		Patient patient = mock(Patient.class);

		Set<Problem> problems = new HashSet<>();

		Problem problem1 = mock(Problem.class);
		Problem problem2 = mock(Problem.class);

		problems.add(problem1);
		problems.add(problem2);

		when(patient.getProblems()).thenReturn(problems);

		when(patientRepository.findOne(patient.getId())).thenReturn(patient);

		ProblemDto dto1 = mock(ProblemDto.class);
		ProblemDto dto2 = mock(ProblemDto.class);

		when(modelMapper.map(problem1, ProblemDto.class)).thenReturn(dto1);
		when(modelMapper.map(problem2, ProblemDto.class)).thenReturn(dto2);

		// Act
		List<ProblemDto> actual = service.findAllProblemsByPatientId(patient.getId());

		// Assert
		assertEquals(problems.size(), actual.size());

		for (ProblemDto dto : actual) {
			assertTrue(dto == dto1 || dto == dto2);
		}
	}

}
