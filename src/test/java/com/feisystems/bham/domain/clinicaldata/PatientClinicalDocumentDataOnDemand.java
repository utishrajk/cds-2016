package com.feisystems.bham.domain.clinicaldata;
import com.feisystems.bham.domain.patient.PatientDataOnDemand;
import com.feisystems.bham.domain.reference.PatientClinicalDocumentTypeCodeDataOnDemand;
import com.feisystems.bham.service.clinicaldata.PatientClinicalDocumentService;
import java.security.SecureRandom;
import java.util.ArrayList;
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
public class PatientClinicalDocumentDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<PatientClinicalDocument> data;

	@Autowired
    PatientDataOnDemand patientDataOnDemand;

	@Autowired
    PatientClinicalDocumentTypeCodeDataOnDemand patientClinicalDocumentTypeCodeDataOnDemand;

	@Autowired
    PatientClinicalDocumentService patientClinicalDocumentService;

	@Autowired
    PatientClinicalDocumentRepository patientClinicalDocumentRepository;

	public PatientClinicalDocument getNewTransientPatientClinicalDocument(int index) {
        PatientClinicalDocument obj = new PatientClinicalDocument();
        setContent(obj, index);
        setContentStandardName(obj, index);
        setContentStandardVersion(obj, index);
        setContentType(obj, index);
        setDescription(obj, index);
        setDocumentProvenance(obj, index);
        setDocumentSize(obj, index);
        setDocumentUrl(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setContent(PatientClinicalDocument obj, int index) {
        String content = "content_" + index;
        obj.setContent(content);
    }

	public void setContentStandardName(PatientClinicalDocument obj, int index) {
        String contentStandardName = "contentStandardName_" + index;
        obj.setContentStandardName(contentStandardName);
    }

	public void setContentStandardVersion(PatientClinicalDocument obj, int index) {
        String contentStandardVersion = "contentStandardVersion_" + index;
        obj.setContentStandardVersion(contentStandardVersion);
    }

	public void setContentType(PatientClinicalDocument obj, int index) {
        String contentType = "contentType_" + index;
        obj.setContentType(contentType);
    }

	public void setDescription(PatientClinicalDocument obj, int index) {
        String description = "description_" + index;
        if (description.length() > 500) {
            description = description.substring(0, 500);
        }
        obj.setDescription(description);
    }

	public void setDocumentProvenance(PatientClinicalDocument obj, int index) {
        String DocumentProvenance = "DocumentProvenance_" + index;
        obj.setDocumentProvenance(DocumentProvenance);
    }

	public void setDocumentSize(PatientClinicalDocument obj, int index) {
        Long documentSize = new Integer(index).longValue();
        obj.setDocumentSize(documentSize);
    }

	public void setDocumentUrl(PatientClinicalDocument obj, int index) {
        String documentUrl = "documentUrl_" + index;
        if (documentUrl.length() > 100) {
            documentUrl = documentUrl.substring(0, 100);
        }
        obj.setDocumentUrl(documentUrl);
    }

	public void setName(PatientClinicalDocument obj, int index) {
        String name = "name_" + index;
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        obj.setName(name);
    }

	public PatientClinicalDocument getSpecificPatientClinicalDocument(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PatientClinicalDocument obj = data.get(index);
        Long id = obj.getId();
        return patientClinicalDocumentService.findPatientClinicalDocument(id);
    }

	public PatientClinicalDocument getRandomPatientClinicalDocument() {
        init();
        PatientClinicalDocument obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return patientClinicalDocumentService.findPatientClinicalDocument(id);
    }

	public boolean modifyPatientClinicalDocument(PatientClinicalDocument obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = patientClinicalDocumentService.findPatientClinicalDocumentEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PatientClinicalDocument' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PatientClinicalDocument>();
        for (int i = 0; i < 10; i++) {
            PatientClinicalDocument obj = getNewTransientPatientClinicalDocument(i);
            try {
                patientClinicalDocumentService.savePatientClinicalDocument(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            patientClinicalDocumentRepository.flush();
            data.add(obj);
        }
    }
}
