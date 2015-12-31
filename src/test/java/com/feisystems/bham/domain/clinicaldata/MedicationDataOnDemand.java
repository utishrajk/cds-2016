package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.ActStatusCodeDataOnDemand;
import com.feisystems.bham.domain.reference.BodySiteCodeDataOnDemand;
import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCodeDataOnDemand;
import com.feisystems.bham.domain.reference.ProductFormCodeDataOnDemand;
import com.feisystems.bham.domain.reference.RouteCodeDataOnDemand;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import com.feisystems.bham.domain.valueobject.CodedConceptValueObject;
import com.feisystems.bham.domain.valueobject.QuantityType;
import com.feisystems.bham.service.clinicaldata.MedicationService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class MedicationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Medication> data;

	@Autowired
    BodySiteCodeDataOnDemand bodySiteCodeDataOnDemand;

	@Autowired
    MedicationDeliveryMethodCodeDataOnDemand medicationDeliveryMethodCodeDataOnDemand;

	@Autowired
    ActStatusCodeDataOnDemand actStatusCodeDataOnDemand;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    ProductFormCodeDataOnDemand productFormCodeDataOnDemand;

	@Autowired
    RouteCodeDataOnDemand routeCodeDataOnDemand;

	@Autowired
    MedicationService medicationService;

	@Autowired
    MedicationRepository medicationRepository;

	public Medication getNewTransientMedication(int index) {
        Medication obj = new Medication();
        setMedicationCode(obj, index);
        setDoseQuantity(obj, index);
        setFreeTextSig(obj, index);
        setMedicationEndDate(obj, index);
        setMedicationStartDate(obj, index);
        return obj;
    }

	public void setMedicationCode(Medication obj, int index) {
        CodedConceptValueObject embeddedClass = new CodedConceptValueObject();
        setMedicationCodeCode(embeddedClass, index);
        setMedicationCodeCodeSystem(embeddedClass, index);
        setMedicationCodeDisplayName(embeddedClass, index);
        setMedicationCodeCodeSystemName(embeddedClass, index);
        setMedicationCodeOriginalText(embeddedClass, index);
        setMedicationCodeCodeSystemVersion(embeddedClass, index);
        obj.setMedicationCode(embeddedClass);
    }

	public void setMedicationCodeCode(CodedConceptValueObject obj, int index) {
        String code = "code_" + index;
        if (code.length() > 250) {
            code = code.substring(0, 250);
        }
        obj.setCode(code);
    }

	public void setMedicationCodeCodeSystem(CodedConceptValueObject obj, int index) {
        String codeSystem = "codeSystem_" + index;
        if (codeSystem.length() > 250) {
            codeSystem = codeSystem.substring(0, 250);
        }
        obj.setCodeSystem(codeSystem);
    }

	public void setMedicationCodeDisplayName(CodedConceptValueObject obj, int index) {
        String displayName = "displayName_" + index;
        if (displayName.length() > 250) {
            displayName = displayName.substring(0, 250);
        }
        obj.setDisplayName(displayName);
    }

	public void setMedicationCodeCodeSystemName(CodedConceptValueObject obj, int index) {
        String codeSystemName = "codeSystemName_" + index;
        if (codeSystemName.length() > 250) {
            codeSystemName = codeSystemName.substring(0, 250);
        }
        obj.setCodeSystemName(codeSystemName);
    }

	public void setMedicationCodeOriginalText(CodedConceptValueObject obj, int index) {
        String originalText = "originalText_" + index;
        if (originalText.length() > 250) {
            originalText = originalText.substring(0, 250);
        }
        obj.setOriginalText(originalText);
    }

	public void setMedicationCodeCodeSystemVersion(CodedConceptValueObject obj, int index) {
        Double codeSystemVersion = new Integer(index).doubleValue();
        obj.setCodeSystemVersion(codeSystemVersion);
    }

	public void setDoseQuantity(Medication obj, int index) {
        QuantityType embeddedClass = new QuantityType();
        setDoseQuantityMeasuredValue(embeddedClass, index);
        setDoseQuantityUnitOfMeasureCode(embeddedClass, index);
        obj.setDoseQuantity(embeddedClass);
    }

	public void setDoseQuantityMeasuredValue(QuantityType obj, int index) {
        Double measuredValue = new Integer(index).doubleValue();
        obj.setMeasuredValue(measuredValue);
    }

	public void setDoseQuantityUnitOfMeasureCode(QuantityType obj, int index) {
        UnitOfMeasureCode unitOfMeasureCode = null;
        obj.setUnitOfMeasureCode(unitOfMeasureCode);
    }

	public void setFreeTextSig(Medication obj, int index) {
        String freeTextSig = "freeTextSig_" + index;
        if (freeTextSig.length() > 250) {
            freeTextSig = freeTextSig.substring(0, 250);
        }
        obj.setFreeTextSig(freeTextSig);
    }

	public void setMedicationEndDate(Medication obj, int index) {
        Date medicationEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setMedicationEndDate(medicationEndDate);
    }

	public void setMedicationStartDate(Medication obj, int index) {
        Date medicationStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setMedicationStartDate(medicationStartDate);
    }

	public Medication getSpecificMedication(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Medication obj = data.get(index);
        Long id = obj.getId();
        return medicationService.findMedication(id);
    }

	public Medication getRandomMedication() {
        init();
        Medication obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return medicationService.findMedication(id);
    }

	public boolean modifyMedication(Medication obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = medicationService.findMedicationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Medication' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Medication>();
        for (int i = 0; i < 10; i++) {
            Medication obj = getNewTransientMedication(i);
            try {
                medicationService.saveMedication(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            medicationRepository.flush();
            data.add(obj);
        }
    }
}
