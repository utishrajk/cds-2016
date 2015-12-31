package com.feisystems.bham.domain.controller;

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

import com.feisystems.bham.domain.clinicaldata.ProblemTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.reference.ProblemCodeService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProblemTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class ProblemCodeControllerTest {

	@Autowired
	ProblemCodeService problemCodeService;

	private MockMvc mockMvc;

	// Mocking only two procedures
	LookupDto problemCode1, problemCode2;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private final String CODE_1 = "1";
	private final String NAME_1 = "ProblemCode1";

	private final String CODE_2 = "2";
	private final String NAME_2 = "ProblemCode2";

	@Before
	public void setUp() {

		Mockito.reset(problemCodeService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		problemCode1 = new LookupDtoBuilder().displayName(NAME_1).code(CODE_1).build();

		problemCode2 = new LookupDtoBuilder().displayName(NAME_2).code(CODE_2).build();
	}

	@Test
	public void testFindAll_ShouldReturnFoundStateEntries() throws Exception {

		when(problemCodeService.findAllProblemCodes()).thenReturn(Arrays.asList(problemCode1, problemCode2));

		mockMvc.perform(get("/problemcodes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].code", is(CODE_1)))
				.andExpect(jsonPath("$[0].displayName", is(NAME_1))).andExpect(jsonPath("$[1].code", is(CODE_2)))
				.andExpect(jsonPath("$[1].displayName", is(NAME_2)));

		verify(problemCodeService, times(1)).findAllProblemCodes();
		verifyNoMoreInteractions(problemCodeService);
	}

}
