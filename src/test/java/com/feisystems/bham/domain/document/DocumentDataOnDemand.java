package com.feisystems.bham.domain.document;
import com.feisystems.bham.domain.reference.DocumentTypeCode;
import com.feisystems.bham.service.document.DocumentService;
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

@Component
@Configurable
public class DocumentDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Document> data;

	@Autowired
    DocumentService documentService;

	@Autowired
    DocumentRepository documentRepository;

	public Document getNewTransientDocument(int index) {
        Document obj = new Document();
        setContent(obj, index);
        setContentStandardName(obj, index);
        setContentStandardVersion(obj, index);
        setContentType(obj, index);
        setDescription(obj, index);
        setDocumentProvenance(obj, index);
        setDocumentSize(obj, index);
        setDocumentTypeCode(obj, index);
        setDocumentUrl(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setContent(Document obj, int index) {
        String content = "content_" + index;
        obj.setContent(content);
    }

	public void setContentStandardName(Document obj, int index) {
        String contentStandardName = "contentStandardName_" + index;
        obj.setContentStandardName(contentStandardName);
    }

	public void setContentStandardVersion(Document obj, int index) {
        String contentStandardVersion = "contentStandardVersion_" + index;
        obj.setContentStandardVersion(contentStandardVersion);
    }

	public void setContentType(Document obj, int index) {
        String contentType = "contentType_" + index;
        obj.setContentType(contentType);
    }

	public void setDescription(Document obj, int index) {
        String description = "description_" + index;
        if (description.length() > 500) {
            description = description.substring(0, 500);
        }
        obj.setDescription(description);
    }

	public void setDocumentProvenance(Document obj, int index) {
        String DocumentProvenance = "DocumentProvenance_" + index;
        obj.setDocumentProvenance(DocumentProvenance);
    }

	public void setDocumentSize(Document obj, int index) {
        Long documentSize = new Integer(index).longValue();
        obj.setDocumentSize(documentSize);
    }

	public void setDocumentTypeCode(Document obj, int index) {
        DocumentTypeCode documentTypeCode = null;
        obj.setDocumentTypeCode(documentTypeCode);
    }

	public void setDocumentUrl(Document obj, int index) {
        String documentUrl = "documentUrl_" + index;
        if (documentUrl.length() > 250) {
            documentUrl = documentUrl.substring(0, 250);
        }
        obj.setDocumentUrl(documentUrl);
    }

	public void setName(Document obj, int index) {
        String name = "name_" + index;
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        obj.setName(name);
    }

	public Document getSpecificDocument(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Document obj = data.get(index);
        Long id = obj.getId();
        return documentService.findDocument(id);
    }

	public Document getRandomDocument() {
        init();
        Document obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return documentService.findDocument(id);
    }

	public boolean modifyDocument(Document obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = documentService.findDocumentEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Document' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Document>();
        for (int i = 0; i < 10; i++) {
            Document obj = getNewTransientDocument(i);
            try {
                documentService.saveDocument(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            documentRepository.flush();
            data.add(obj);
        }
    }
}
