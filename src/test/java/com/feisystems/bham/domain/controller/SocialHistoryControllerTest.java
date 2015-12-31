package com.feisystems.bham.domain.controller;

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

import com.feisystems.bham.domain.clinicaldata.SocialHistory;
import com.feisystems.bham.domain.clinicaldata.SocialHistoryTestContext;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.domain.reference.SocialHistoryTypeCode;
import com.feisystems.bham.service.clinicaldata.SocialHistoryService;
import com.feisystems.bham.service.dto.SocialHistoryDto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SocialHistoryTestContext.class, PatientTestContext.class })
@WebAppConfiguration
public class SocialHistoryControllerTest {
	
	@Autowired
	SocialHistoryService socialhistoryService;
	
	SocialHistoryDto first, second;
	SocialHistoryTypeCode typeCode1, typeCode2;
	ActStatusCode statusCode1, statusCode2;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	@Before
	public void setUp() throws Exception {
		Mockito.reset(socialhistoryService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		typeCode1 = new CodeDataBuilder<SocialHistoryTypeCode>(new SocialHistoryTypeCode()).setCode("1").setDisplayName("Code 1").build();
		typeCode2 = new CodeDataBuilder<SocialHistoryTypeCode>(new SocialHistoryTypeCode()).setCode("2").setDisplayName("Code 2").build();

		statusCode1 = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("active").setDisplayName("Active").build();
		statusCode2 = new CodeDataBuilder<ActStatusCode>(new ActStatusCode()).setCode("cancelled").setDisplayName("Cancelled").build();

		first = new SocialHistoryDto();

		first.setId(1L);
		first.setSocialHistoryTypeCode(typeCode1.getCode());
		first.setSocialHistoryStatusCode(statusCode1.getCode());

		second = new SocialHistoryDto();

		second.setId(2L);
		second.setSocialHistoryTypeCode(typeCode1.getCode());
		second.setSocialHistoryStatusCode(statusCode1.getCode());
	}
	
	@Test
	public void testFindAll() throws Exception {
		when(socialhistoryService.findAllSocialHistorys()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/socialhistorys")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(socialhistoryService, times(1)).findAllSocialHistorys();
		verifyNoMoreInteractions(socialhistoryService);
	}
	
	@Test
	public void testFindOne() throws Exception {
		when(socialhistoryService.findSocialHistory(1L)).thenReturn(first);

		mockMvc.perform(get("/socialhistorys/1")).andExpect(status().isOk()).andExpect(jsonPath("id", is(1)));
		verify(socialhistoryService, times(1)).findSocialHistory(1L);
		verifyNoMoreInteractions(socialhistoryService);
	}
	
	@Test
	public void testFindOneForPatient() throws Exception {
		when(socialhistoryService.findAllSocialHistorysByPatientId(213L)).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/socialhistorys/patient/213")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
		verify(socialhistoryService, times(1)).findAllSocialHistorysByPatientId(213L);
		verifyNoMoreInteractions(socialhistoryService);
	}
	
	@Test
	public void testPostForPatient() throws Exception {
		mockMvc.perform(post("/socialhistorys/patient/213").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
		when(socialhistoryService.updateSocialHistory(any(SocialHistoryDto.class))).thenReturn(new SocialHistory());

		mockMvc.perform(put("/socialhistorys/2").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		when(socialhistoryService.findSocialHistory(first.getId())).thenReturn(first);

		mockMvc.perform(delete("/socialhistorys/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateForNotFound() throws Exception {
		when(socialhistoryService.updateSocialHistory(any(SocialHistoryDto.class))).thenReturn(null);

		mockMvc.perform(put("/socialhistorys/3").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(first))).andExpect(
				status().isNotFound());
	}
	
	@Test
	public void testDeleteForNotFound() throws Exception {
		when(socialhistoryService.findSocialHistory(first.getId())).thenReturn(null);

		mockMvc.perform(delete("/socialhistorys/1")).andExpect(status().isNotFound());
	}


}
