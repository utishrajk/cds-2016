package com.feisystems.bham.domain.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientTestContext;
import com.feisystems.bham.domain.reference.RaceCode;
import com.feisystems.bham.service.dto.PatientProfileDto;
import com.feisystems.bham.service.patient.PatientService;

import java.util.Arrays;
import java.util.Calendar;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PatientTestContext.class})
@WebAppConfiguration
public class PatientControllerTest {

    @Autowired
	PatientService patientServiceMock;

	PatientProfileDto first, second;
	RaceCode mockAfricanRaceCode, mockAsianRaceCode;
	
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(patientServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockAsianRaceCode = new CodeDataBuilder<RaceCode>(new RaceCode())
        					.setCode("2028-9")
							.setDisplayName("Asian")
							.build();

        mockAfricanRaceCode = new CodeDataBuilder<RaceCode>(new RaceCode())
        					.setCode("2054-5")
    						.setDisplayName("Black or African American")
    						.build();

		first = new PatientProfileDtoBuilder()
	      .id(1L)
	      .firstName("Himalay")
	      .lastName("Majumdar")
	      .username("hmajumdar")
	      .raceCode(mockAsianRaceCode.getCode())
	      .raceCodeDisplayName(mockAsianRaceCode.getDisplayName())
	      .addressStateCode("MD")
	      .addressPostalCode("21030")
	      .build();
		
		second = new PatientProfileDtoBuilder()
	      .id(2L)
	      .firstName("Tomson")
	      .lastName("Ngassa")
	      .username("tngassa")
	      .raceCode(mockAfricanRaceCode.getCode())
	      .raceCodeDisplayName(mockAfricanRaceCode.getDisplayName())
	      .addressStateCode("MD")
	      .addressPostalCode("21075")
	      .build();
    }
 
//	HAPPY PATH
	@Test
    public void testFindAll_ShouldReturnFoundPatientEntries() throws Exception {
		
        when(patientServiceMock.findAllPatients()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Himalay")))
				.andExpect(jsonPath("$[0].raceCodeDisplayName", is("Asian")))
                .andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("Tomson")))
				.andExpect(jsonPath("$[1].raceCodeDisplayName", is("Black or African American")));
        verify(patientServiceMock, times(1)).findAllPatients();
        verifyNoMoreInteractions(patientServiceMock);
        
    }
	
	@Test
    public void testFindOne_ShouldReturnSpecificPatientEntry() throws Exception {
		
        when(patientServiceMock.findPatient(1L)).thenReturn(first);

		mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("firstName", is("Himalay")))
				.andExpect(jsonPath("raceCodeDisplayName", is("Asian")));
        verify(patientServiceMock, times(1)).findPatient(1L);
        verifyNoMoreInteractions(patientServiceMock);
        
    }
	
	@Test
    public void testDeleteOne_ShouldDeleteSpecificPatientEntry() throws Exception {
		
        when(patientServiceMock.findPatient(1L)).thenReturn(first);

		mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());
		verify(patientServiceMock, times(1)).findPatient(1L);
		verify(patientServiceMock, times(1)).deletePatient(first);
        verifyNoMoreInteractions(patientServiceMock);
        
    }

	
	@Test
    public void testPutOne_ShouldUpdateSpecificPatientEntry() throws Exception {
		
        when(patientServiceMock.findPatient(1L)).thenReturn(first);
        when(patientServiceMock.updatePatient(any(PatientProfileDto.class))).thenReturn(new Patient());
        
		mockMvc.perform(put("/patients/1", 1L)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(first))
		)
                .andExpect(status().isOk());
        ArgumentCaptor<PatientProfileDto> dtoCaptor = ArgumentCaptor.forClass(PatientProfileDto.class);

        verify(patientServiceMock, times(1)).updatePatient(dtoCaptor.capture());
        verifyNoMoreInteractions(patientServiceMock);
        
    }
	
	
	@Test
    public void testPostOne_ShouldCreateSpecificPatientEntry() throws Exception {

//		doThrow(new RuntimeException()).when(patientServiceMock).savePatient(any(PatientProfileDto.class));
   		
		mockMvc.perform(post("/patients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(first))
        )
              	.andExpect(status().isCreated());
		
        ArgumentCaptor<PatientProfileDto> dtoCaptor = ArgumentCaptor.forClass(PatientProfileDto.class);

        verify(patientServiceMock, times(1)).savePatient(dtoCaptor.capture());
        verifyNoMoreInteractions(patientServiceMock);
    }
	
	
//	UNHAPPY PATH
	
	@Test
    public void testFindAll_BadUrl_ShouldReturnFoundPatientEntries() throws Exception {
		
		mockMvc.perform(get("/patientsss"))
                .andExpect(status().isOk());
        verify(patientServiceMock, times(0)).findAllPatients();
        verifyNoMoreInteractions(patientServiceMock);
        
    }
	
	@Test
    public void testPostOne_EmptyFirstName_ShouldCreateSpecificPatientEntry() throws Exception {
		
		first.setFirstName(null);

		mockMvc.perform(post("/patients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(first))
        )
              	.andExpect(status().isBadRequest())
              	.andExpect(jsonPath("message", containsString("Both FirstName and LastName are Required")));
		
        ArgumentCaptor<PatientProfileDto> dtoCaptor = ArgumentCaptor.forClass(PatientProfileDto.class);

        verify(patientServiceMock, times(0)).savePatient(dtoCaptor.capture());
        verifyNoMoreInteractions(patientServiceMock);
    }
	
	@Test
    public void testPostOne_EmptyLastName_ShouldCreateSpecificPatientEntry() throws Exception {
		
		first.setFirstName("Himalay");
		first.setLastName(null);

		mockMvc.perform(post("/patients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(first))
        )
              	.andExpect(status().isBadRequest())
              	.andExpect(jsonPath("message", containsString("Both FirstName and LastName are Required")));
		
        ArgumentCaptor<PatientProfileDto> dtoCaptor = ArgumentCaptor.forClass(PatientProfileDto.class);

        verify(patientServiceMock, times(0)).savePatient(dtoCaptor.capture());
        verifyNoMoreInteractions(patientServiceMock);
    }

	@Test
    public void testPostOne_InvalidBirthDate_ShouldCreateSpecificPatientEntry() throws Exception {
		
		first.setFirstName("Himalay");
		first.setLastName("Majumdar");
		
		// Future Date
		Date date = new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.DATE, 1);
		
		first.setBirthDate(cal.getTime());

		mockMvc.perform(post("/patients")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(first))
        )
              	.andExpect(status().isBadRequest())
              	.andExpect(jsonPath("message", containsString("Invalid BirthDate")));
		
        ArgumentCaptor<PatientProfileDto> dtoCaptor = ArgumentCaptor.forClass(PatientProfileDto.class);

        verify(patientServiceMock, times(0)).savePatient(dtoCaptor.capture());
        verifyNoMoreInteractions(patientServiceMock);
    }

}
