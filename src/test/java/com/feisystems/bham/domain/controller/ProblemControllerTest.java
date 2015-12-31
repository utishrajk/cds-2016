package com.feisystems.bham.domain.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

import com.feisystems.bham.domain.clinicaldata.Problem;
import com.feisystems.bham.domain.clinicaldata.ProblemTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.ProblemCode;
import com.feisystems.bham.service.clinicaldata.ProblemService;
import com.feisystems.bham.service.dto.ProblemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProblemTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class ProblemControllerTest {

	@Autowired
	ProblemService problemService;

	ProblemDto first, second;
	ProblemCode mockType2, mockHypertension;
	ActStatusCode mockActive, mockCancelled;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Before
	public void setUp() throws Exception {
		Mockito.reset(problemService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		mockType2 = new CodeDataBuilder<ProblemCode>(new ProblemCode()).setCode("E11").setDisplayName("Type 2 diabetes mellitus").build();
		mockHypertension = new CodeDataBuilder<ProblemCode>(new ProblemCode()).setCode("I10").setDisplayName("Hypertension").build();

		mockActive = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("active").setDisplayName("Active").build();
		mockCancelled = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("cancelled").setDisplayName("Cancelled").build();

		first = new ProblemDto();

		first.setId(1L);
		first.setProblemCode(mockType2.getCode());
		first.setProblemStatusCode(mockActive.getCode());

		second = new ProblemDto();

		second.setId(2L);
		second.setProblemCode(mockHypertension.getCode());
		second.setProblemStatusCode(mockCancelled.getCode());
	}

	@Test
	public void testFindAll() throws Exception {
		when(problemService.findAllProblems()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/problems")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(problemService, times(1)).findAllProblems();
		verifyNoMoreInteractions(problemService);
	}

	@Test
	public void testFindOne() throws Exception {
		when(problemService.findProblem(1L)).thenReturn(first);

		mockMvc.perform(get("/problems/1")).andExpect(status().isOk()).andExpect(jsonPath("id", is(1)));
		verify(problemService, times(1)).findProblem(1L);
		verifyNoMoreInteractions(problemService);
	}

	@Test
	public void testFindOneForPatient() throws Exception {
		when(problemService.findAllProblemsByPatientId(213L)).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/problems/patient/213")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(problemService, times(1)).findAllProblemsByPatientId(213L);
		verifyNoMoreInteractions(problemService);
	}

	@Test
	public void testPostForPatient() throws Exception {
		mockMvc.perform(post("/problems/patient/213").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isCreated());
	}

	@Test
	public void testUpdate() throws Exception {
		when(problemService.updateProblem(any(ProblemDto.class))).thenReturn(new Problem());

		mockMvc.perform(put("/problems/2").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		when(problemService.findProblem(first.getId())).thenReturn(first);

		mockMvc.perform(delete("/problems/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateForNotFound() throws Exception {
		when(problemService.updateProblem(any(ProblemDto.class))).thenReturn(null);

		mockMvc.perform(put("/problems/3").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isNotFound());
	}

	@Test
	public void testDeleteForNotFound() throws Exception {
		when(problemService.findProblem(first.getId())).thenReturn(null);

		mockMvc.perform(delete("/problems/1")).andExpect(status().isNotFound());
	}

}
