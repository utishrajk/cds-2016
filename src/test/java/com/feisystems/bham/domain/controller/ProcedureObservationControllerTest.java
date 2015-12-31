package com.feisystems.bham.domain.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
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

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ProcedureTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.BodySiteCode;
import com.feisystems.bham.domain.reference.ProcedureTypeCode;
import com.feisystems.bham.service.clinicaldata.ProcedureObservationService;
import com.feisystems.bham.service.dto.ProcedureObservationDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProcedureTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class ProcedureObservationControllerTest {
	
	@Autowired
	ProcedureObservationService procedureObservationService;

	ProcedureObservationDto first, second;
	ProcedureTypeCode procedureCode1, procedureCode2;
	ActStatusCode status1, status2;
	BodySiteCode siteCode1, siteCode2;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	@Before
	public void setUp() throws Exception {
		Mockito.reset(procedureObservationService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		procedureCode1 = new CodeDataBuilder<ProcedureTypeCode>(new ProcedureTypeCode()).setCode("1").setDisplayName("Procedure 1").build();
		procedureCode2 = new CodeDataBuilder<ProcedureTypeCode>(new ProcedureTypeCode()).setCode("2").setDisplayName("Procedure 2").build();

		status1 = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("active").setDisplayName("Active").build();
		status2 = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("cancelled").setDisplayName("Cancelled").build();

		first = new ProcedureObservationDto();

		first.setId(1L);
		first.setProcedureTypeCode(procedureCode1.getCode());
		first.setProcedureStatusCode(status1.getCode());

		second = new ProcedureObservationDto();

		second.setId(2L);
		second.setProcedureTypeCode(procedureCode2.getCode());
		second.setProcedureStatusCode(status2.getCode());
	}
	
	@Test
	public void testFindAll() throws Exception {
		when(procedureObservationService.findAllProcedureObservations()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/procedureobservations")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(procedureObservationService, times(1)).findAllProcedureObservations();
		verifyNoMoreInteractions(procedureObservationService);
	}
	
	@Test
	public void testFindOne() throws Exception {
		when(procedureObservationService.findProcedureObservation(1L)).thenReturn(first);

		mockMvc.perform(get("/procedureobservations/1")).andExpect(status().isOk()).andExpect(jsonPath("id", is(1)));
		verify(procedureObservationService, times(1)).findProcedureObservation(1L);
		verifyNoMoreInteractions(procedureObservationService);
	}
	
	@Test
	public void testFindOneForPatient() throws Exception {
		when(procedureObservationService.findAllProcedureObservationsByPatientId(213L)).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/procedureobservations/patient/213")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(procedureObservationService, times(1)).findAllProcedureObservationsByPatientId(213L);
		verifyNoMoreInteractions(procedureObservationService);
	}
	
	@Test
	public void testPostForPatient() throws Exception {
		mockMvc.perform(post("/procedureobservations/patient/213").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
		when(procedureObservationService.updateProcedureObservation(any(ProcedureObservationDto.class))).thenReturn(new ProcedureObservation());

		mockMvc.perform(put("/procedureobservations/2").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		when(procedureObservationService.findProcedureObservation(first.getId())).thenReturn(first);

		mockMvc.perform(delete("/procedureobservations/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateForNotFound() throws Exception {
		when(procedureObservationService.updateProcedureObservation(any(ProcedureObservationDto.class))).thenReturn(null);

		mockMvc.perform(put("/procedureobservations/3").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isNotFound());
	}
	
	@Test
	public void testDeleteForNotFound() throws Exception {
		when(procedureObservationService.findProcedureObservation(first.getId())).thenReturn(null);

		mockMvc.perform(delete("/procedureobservations/1")).andExpect(status().isNotFound());
	}
	
}
