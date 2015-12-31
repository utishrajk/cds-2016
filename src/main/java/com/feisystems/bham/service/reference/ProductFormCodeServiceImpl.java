package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.ProductFormCode;
import com.feisystems.bham.domain.reference.ProductFormCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductFormCodeServiceImpl implements ProductFormCodeService {

	@Autowired
    ProductFormCodeRepository productFormCodeRepository;

	public long countAllProductFormCodes() {
        return productFormCodeRepository.count();
    }

	public void deleteProductFormCode(ProductFormCode productFormCode) {
        productFormCodeRepository.delete(productFormCode);
    }

	public ProductFormCode findProductFormCode(Long id) {
        return productFormCodeRepository.findOne(id);
    }

	public List<ProductFormCode> findAllProductFormCodes() {
        return productFormCodeRepository.findAll();
    }

	public List<ProductFormCode> findProductFormCodeEntries(int firstResult, int maxResults) {
        return productFormCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveProductFormCode(ProductFormCode productFormCode) {
        productFormCodeRepository.save(productFormCode);
    }

	public ProductFormCode updateProductFormCode(ProductFormCode productFormCode) {
        return productFormCodeRepository.save(productFormCode);
    }
}
