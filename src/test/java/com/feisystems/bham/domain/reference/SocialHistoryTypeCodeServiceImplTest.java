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
import com.feisystems.bham.service.reference.SocialHistoryTypeCodeService;
import com.feisystems.bham.service.reference.SocialHistoryTypeCodeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SocialHistoryTypeCodeServiceImplTest {

	@InjectMocks
	private SocialHistoryTypeCodeService sut = new SocialHistoryTypeCodeServiceImpl();

	@Mock
	SocialHistoryTypeCodeRepository socialHistoryTypeCodeRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testFindAllSocialHistories() {
		final SocialHistoryTypeCode socialHistoryTypeCode1 = mock(SocialHistoryTypeCode.class);
		final SocialHistoryTypeCode socialHistoryTypeCode2 = mock(SocialHistoryTypeCode.class);
		List<SocialHistoryTypeCode> socialHistoryTypeCodeList = new ArrayList<SocialHistoryTypeCode>();

		socialHistoryTypeCodeList.add(socialHistoryTypeCode1);
		socialHistoryTypeCodeList.add(socialHistoryTypeCode2);

		int socialHistoryTypeCodeCount = socialHistoryTypeCodeList.size();

		when(socialHistoryTypeCodeRepository.findAll()).thenReturn(socialHistoryTypeCodeList); 

		final LookupDto expectedLookupDto1 = mock(LookupDto.class);
		when(modelMapper.map(socialHistoryTypeCode1, LookupDto.class)).thenReturn(expectedLookupDto1);

		final LookupDto expectedLookupDto2 = mock(LookupDto.class);
		when(modelMapper.map(socialHistoryTypeCode2, LookupDto.class)).thenReturn(expectedLookupDto2);

		// Act
		List<LookupDto> result = sut.findAllSocialHistoryTypeCodes();

		// Assert
		assertEquals(socialHistoryTypeCodeCount, result.size());
		for (LookupDto lookupDto : result) {
			assertTrue(lookupDto == expectedLookupDto1 || lookupDto == expectedLookupDto2);
		}

		assertTrue(result.get(0) != result.get(1));
	}

}
