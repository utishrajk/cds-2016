package com.feisystems.bham.domain.patient;
import com.feisystems.bham.domain.reference.AddressUseCode;
import com.feisystems.bham.domain.reference.AdministrativeGenderCodeDataOnDemand;
import com.feisystems.bham.domain.reference.CountryCode;
import com.feisystems.bham.domain.reference.EthnicGroupCodeDataOnDemand;
import com.feisystems.bham.domain.reference.LanguageCodeDataOnDemand;
import com.feisystems.bham.domain.reference.MaritalStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.RaceCodeDataOnDemand;
import com.feisystems.bham.domain.reference.ReligiousAffiliationCodeDataOnDemand;
import com.feisystems.bham.domain.reference.StateCode;
import com.feisystems.bham.domain.reference.TelecomUseCode;
import com.feisystems.bham.domain.valueobject.AddressType;
import com.feisystems.bham.domain.valueobject.TelephoneType;
import com.feisystems.bham.service.patient.PatientService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class PatientDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Patient> data;

	@Autowired
    AdministrativeGenderCodeDataOnDemand administrativeGenderCodeDataOnDemand;

	@Autowired
    EthnicGroupCodeDataOnDemand ethnicGroupCodeDataOnDemand;

	@Autowired
    LanguageCodeDataOnDemand languageCodeDataOnDemand;

	@Autowired
    MaritalStatusCodeDataOnDemand maritalStatusCodeDataOnDemand;

	@Autowired
    RaceCodeDataOnDemand raceCodeDataOnDemand;

	@Autowired
    ReligiousAffiliationCodeDataOnDemand religiousAffiliationCodeDataOnDemand;

	@Autowired
    PatientService patientService;

	@Autowired
    PatientRepository patientRepository;

	public Patient getNewTransientPatient(int index) {
        Patient obj = new Patient();
        setAddress(obj, index);
        setTelephone(obj, index);
        setBirthDay(obj, index);
        setEmail(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setMedicalRecordNumber(obj, index);
        setPatientIdNumber(obj, index);
        setPrefix(obj, index);
        setSocialSecurityNumber(obj, index);
        setUsername(obj, index);
        return obj;
    }

	public void setAddress(Patient obj, int index) {
        AddressType embeddedClass = new AddressType();
        setAddressAddressUseCode(embeddedClass, index);
        setAddressStreetAddressLine(embeddedClass, index);
        setAddressCity(embeddedClass, index);
        setAddressStateCode(embeddedClass, index);
        setAddressPostalCode(embeddedClass, index);
        setAddressCountryCode(embeddedClass, index);
        obj.setAddress(embeddedClass);
    }

	public void setAddressAddressUseCode(AddressType obj, int index) {
        AddressUseCode addressUseCode = null;
        obj.setAddressUseCode(addressUseCode);
    }

	public void setAddressStreetAddressLine(AddressType obj, int index) {
        String streetAddressLine = "streetAddressLine_" + index;
        if (streetAddressLine.length() > 50) {
            streetAddressLine = streetAddressLine.substring(0, 50);
        }
        obj.setStreetAddressLine(streetAddressLine);
    }

	public void setAddressCity(AddressType obj, int index) {
        String city = "city_" + index;
        if (city.length() > 30) {
            city = city.substring(0, 30);
        }
        obj.setCity(city);
    }

	public void setAddressStateCode(AddressType obj, int index) {
        StateCode stateCode = null;
        obj.setStateCode(stateCode);
    }

	public void setAddressPostalCode(AddressType obj, int index) {
        String postalCode = "postalCode_" + index;
        obj.setPostalCode(postalCode);
    }

	public void setAddressCountryCode(AddressType obj, int index) {
        CountryCode countryCode = null;
        obj.setCountryCode(countryCode);
    }

	public void setTelephone(Patient obj, int index) {
        TelephoneType embeddedClass = new TelephoneType();
        setTelephoneTelephone(embeddedClass, index);
        setTelephoneTelecomUseCode(embeddedClass, index);
        obj.setTelephone(embeddedClass);
    }

	public void setTelephoneTelephone(TelephoneType obj, int index) {
        String telephone = "telephone_" + index;
        if (telephone.length() > 30) {
            telephone = telephone.substring(0, 30);
        }
        obj.setTelephone(telephone);
    }

	public void setTelephoneTelecomUseCode(TelephoneType obj, int index) {
        TelecomUseCode telecomUseCode = null;
        obj.setTelecomUseCode(telecomUseCode);
    }

	public void setBirthDay(Patient obj, int index) {
        Date birthDay = new Date(new Date().getTime() - 10000000L);
        obj.setBirthDay(birthDay);
    }

	public void setEmail(Patient obj, int index) {
        String email = "foo" + index + "@bar.com";
        obj.setEmail(email);
    }

	public void setFirstName(Patient obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 30) {
            firstName = firstName.substring(0, 30);
        }
        obj.setFirstName(firstName);
    }

	public void setLastName(Patient obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 30) {
            lastName = lastName.substring(0, 30);
        }
        obj.setLastName(lastName);
    }

	public void setMedicalRecordNumber(Patient obj, int index) {
        String medicalRecordNumber = "medicalRecordNumber_" + index;
        if (medicalRecordNumber.length() > 30) {
            medicalRecordNumber = medicalRecordNumber.substring(0, 30);
        }
        obj.setMedicalRecordNumber(medicalRecordNumber);
    }

	public void setPatientIdNumber(Patient obj, int index) {
        String patientIdNumber = "patientIdNumber_" + index;
        if (patientIdNumber.length() > 30) {
            patientIdNumber = patientIdNumber.substring(0, 30);
        }
        obj.setPatientIdNumber(patientIdNumber);
    }

	public void setPrefix(Patient obj, int index) {
        String prefix = "prefix_" + index;
        if (prefix.length() > 30) {
            prefix = prefix.substring(0, 30);
        }
        obj.setPrefix(prefix);
    }

	public void setSocialSecurityNumber(Patient obj, int index) {
        String socialSecurityNumber = Integer.toString(index);
        obj.setSocialSecurityNumber("123-12-1212");
    }

	public void setUsername(Patient obj, int index) {
        String username = "username_" + index;
        obj.setUsername(username);
    }

	public Patient getSpecificPatient(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Patient obj = data.get(index);
        Long id = obj.getId();
        return patientRepository.findOne(id);
    }

	public Patient getRandomPatient() {
        init();
        Patient obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return patientRepository.findOne(id);
    }

	public boolean modifyPatient(Patient obj) {
        return false;
    }

	public void init() {
        int pageNumber = 0;
        int pageSize = 10;
        data =  patientRepository.findAll(
				new PageRequest(pageNumber, pageSize)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Patient' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Patient>();
        for (int i = 0; i < 10; i++) {
            Patient obj = getNewTransientPatient(i);
            try {
            	patientRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            patientRepository.flush();
            data.add(obj);
        }
    }
}
