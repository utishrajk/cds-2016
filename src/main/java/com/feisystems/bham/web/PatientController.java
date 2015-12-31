package com.feisystems.bham.web;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.feisystems.bham.service.dto.PatientProfileDto;
import com.feisystems.bham.service.patient.PatientService;
import com.feisystems.bham.service.reference.AdministrativeGenderCodeService;
import com.feisystems.bham.service.reference.RaceCodeService;

@Controller
@RequestMapping("/patients")
public class PatientController {

	@Autowired
    PatientService patientService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
    AdministrativeGenderCodeService administrativeGenderCodeService;

	@Autowired
    RaceCodeService raceCodeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public PatientProfileDto showJson(@PathVariable("id") Long id) {
		PatientProfileDto patientProfileDto = patientService.findPatient(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return patientProfileDto;
    }
	
	@RequestMapping( method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public  List<PatientProfileDto> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PatientProfileDto> result = patientService.findAllPatients();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody PatientProfileDto patientProfileDto) {
		isValidRequest(patientProfileDto);
		patientService.savePatient(patientProfileDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody PatientProfileDto patientProfileDto, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		isValidRequest(patientProfileDto);
		if (patientService.updatePatient(patientProfileDto) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		PatientProfileDto patientProfileDto = patientService.findPatient(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (patientProfileDto == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        patientService.deletePatient(patientProfileDto);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.patient.bad.request", null, locale);

		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();

		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
        return errorInfo;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
		Locale locale = LocaleContextHolder.getLocale();
 
		String errorMessage = messageSource.getMessage("error_resourcenotfound_title", null, locale);
		errorMessage += ex.getMessage();

		String errorURL = req.getRequestURL().toString();

		ErrorInfo errorInfo = new ErrorInfo(errorURL, errorMessage);
        return errorInfo;
	}
	
	public void isValidRequest(PatientProfileDto dto) {
		if (dto.getFirstName() == null || dto.getLastName() == null) {
			throw new IllegalArgumentException("Both FirstName and LastName are Required");
		} 
		if (dto.getBirthDate() != null && dto.getBirthDate().after(new Date())){
			throw new IllegalArgumentException("Invalid BirthDate");
		}
	}
}
