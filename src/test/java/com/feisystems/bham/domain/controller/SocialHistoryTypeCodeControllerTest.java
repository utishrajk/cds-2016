package com.feisystems.bham.domain.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.feisystems.bham.domain.clinicaldata.SocialHistoryTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.reference.SocialHistoryTypeCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SocialHistoryTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class SocialHistoryTypeCodeControllerTest {

	@Autowired
	SocialHistoryTypeCodeService socialHistoryTypeCodeService;

	private MockMvc mockMvc;

	// Mocking only two procedures
	LookupDto socialhistory1, socialhistory2;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private final String CODE_1 = "1";
	private final String NAME_1 = "SocialHistory1";

	private final String CODE_2 = "2";
	private final String NAME_2 = "SocialHistory2";

	@Before
	public void setUp() {

		Mockito.reset(socialHistoryTypeCodeService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		socialhistory1 = new LookupDtoBuilder().displayName(NAME_1).code(CODE_1).build();

		socialhistory2 = new LookupDtoBuilder().displayName(NAME_2).code(CODE_2).build();
	}

	@Test
	public void testFindAll_ShouldReturnFoundStateEntries() throws Exception {

		when(socialHistoryTypeCodeService.findAllSocialHistoryTypeCodes()).thenReturn(Arrays.asList(socialhistory1, socialhistory2));

		mockMvc.perform(get("/socialhistorytypecodes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].code", is(CODE_1)))
				.andExpect(jsonPath("$[0].displayName", is(NAME_1))).andExpect(jsonPath("$[1].code", is(CODE_2)))
				.andExpect(jsonPath("$[1].displayName", is(NAME_2)));

		verify(socialHistoryTypeCodeService, times(1)).findAllSocialHistoryTypeCodes();
		verifyNoMoreInteractions(socialHistoryTypeCodeService);

	}

}
