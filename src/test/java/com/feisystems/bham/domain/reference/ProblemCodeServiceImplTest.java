package com.feisystems.bham.domain.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.reference.ProblemCodeService;
import com.feisystems.bham.service.reference.ProblemCodeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProblemCodeServiceImplTest {

	@InjectMocks
	private ProblemCodeService sut = new ProblemCodeServiceImpl();

	@Mock
	ProblemCodeRepository problemCodeRepository;

	@Mock
	private ModelMapper modelMappper;

	@Test
	public void testFindProblemCodeEntries() {
		// Arrange
		final ProblemCode problemCode1 = mock(ProblemCode.class);
		final ProblemCode problemCode2 = mock(ProblemCode.class);
		List<ProblemCode> problemCodeList = new ArrayList<ProblemCode>();

		problemCodeList.add(problemCode1);
		problemCodeList.add(problemCode2);

		int problemCodeCount = problemCodeList.size();

		when(problemCodeRepository.findAll()).thenReturn(problemCodeList);

		final LookupDto expectedLookupDto1 = mock(LookupDto.class);
		when(modelMappper.map(problemCode1, LookupDto.class)).thenReturn(expectedLookupDto1);

		final LookupDto expectedLookupDto2 = mock(LookupDto.class);
		when(modelMappper.map(problemCode2, LookupDto.class)).thenReturn(expectedLookupDto2);

		// Act
		List<LookupDto> result = sut.findAllProblemCodes();

		// Assert
		assertEquals(problemCodeCount, result.size());
		for (LookupDto lookupDto : result) {
			assertTrue(lookupDto == expectedLookupDto1 || lookupDto == expectedLookupDto2);
		}

		assertTrue(result.get(0) != result.get(1));
	}

}
