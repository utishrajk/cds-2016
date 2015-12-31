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
import com.feisystems.bham.service.reference.ProcedureTypeCodeService;
import com.feisystems.bham.service.reference.ProcedureTypeCodeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureTypeCodeServiceImplTest {

	@InjectMocks
	private ProcedureTypeCodeService sut = new ProcedureTypeCodeServiceImpl();

	@Mock
	ProcedureTypeCodeRepository procedureTypeCodeRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testFindProcedureTypeCodeEntries() {
		// Arrange
		final ProcedureTypeCode procedureTypeCode1 = mock(ProcedureTypeCode.class);
		final ProcedureTypeCode procedureTypeCode2 = mock(ProcedureTypeCode.class);
		List<ProcedureTypeCode> procedureTypeCodeList = new ArrayList<ProcedureTypeCode>();

		procedureTypeCodeList.add(procedureTypeCode1);
		procedureTypeCodeList.add(procedureTypeCode2);

		int procedureTypeCodeCount = procedureTypeCodeList.size();

		when(procedureTypeCodeRepository.findAll()).thenReturn(procedureTypeCodeList);

		final LookupDto expectedLookupDto1 = mock(LookupDto.class);
		when(modelMapper.map(procedureTypeCode1, LookupDto.class)).thenReturn(expectedLookupDto1);

		final LookupDto expectedLookupDto2 = mock(LookupDto.class);
		when(modelMapper.map(procedureTypeCode2, LookupDto.class)).thenReturn(expectedLookupDto2);

		// Act
		List<LookupDto> result = sut.findAllProcedureTypeCodes();

		// Assert
		assertEquals(procedureTypeCodeCount, result.size());
		for (LookupDto lookupDto : result) {
			assertTrue(lookupDto == expectedLookupDto1 || lookupDto == expectedLookupDto2);
		}

		assertTrue(result.get(0) != result.get(1));
	}

}
