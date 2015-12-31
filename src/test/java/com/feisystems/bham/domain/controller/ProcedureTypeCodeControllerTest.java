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

import com.feisystems.bham.domain.clinicaldata.ProcedureTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.service.dto.LookupDto;
import com.feisystems.bham.service.reference.ProcedureTypeCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProcedureTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class ProcedureTypeCodeControllerTest {

	@Autowired
	ProcedureTypeCodeService procedureTypeCodeService;

	private MockMvc mockMvc;

	// Mocking only two procedures
	LookupDto procedure1, procedure2;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private final String CODE_1 = "1";
	private final String NAME_1 = "Procedure1";

	private final String CODE_2 = "2";
	private final String NAME_2 = "Procedure2";

	@Before
	public void setUp() {

		Mockito.reset(procedureTypeCodeService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		procedure1 = new LookupDtoBuilder().displayName(NAME_1).code(CODE_1).build();

		procedure2 = new LookupDtoBuilder().displayName(NAME_2).code(CODE_2).build();
	}

	@Test
	public void testFindAll_ShouldReturnFoundStateEntries() throws Exception {

		when(procedureTypeCodeService.findAllProcedureTypeCodes()).thenReturn(Arrays.asList(procedure1, procedure2));

		mockMvc.perform(get("/proceduretypecodes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].code", is(CODE_1)))
				.andExpect(jsonPath("$[0].displayName", is(NAME_1))).andExpect(jsonPath("$[1].code", is(CODE_2)))
				.andExpect(jsonPath("$[1].displayName", is(NAME_2)));

		verify(procedureTypeCodeService, times(1)).findAllProcedureTypeCodes();
		verifyNoMoreInteractions(procedureTypeCodeService);

	}

}
