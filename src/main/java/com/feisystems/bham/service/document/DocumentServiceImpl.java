package com.feisystems.bham.service.document;

import com.feisystems.bham.domain.document.Document;
import com.feisystems.bham.domain.document.DocumentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
    DocumentRepository documentRepository;

	public long countAllDocuments() {
        return documentRepository.count();
    }

	public void deleteDocument(Document document) {
        documentRepository.delete(document);
    }

	public Document findDocument(Long id) {
        return documentRepository.findOne(id);
    }

	public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }

	public List<Document> findDocumentEntries(int firstResult, int maxResults) {
        return documentRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveDocument(Document document) {
        documentRepository.save(document);
    }

	public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }
}
