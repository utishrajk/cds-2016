package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.ProductFormCode;
import java.util.List;

public interface ProductFormCodeService {

	public abstract long countAllProductFormCodes();


	public abstract void deleteProductFormCode(ProductFormCode productFormCode);


	public abstract ProductFormCode findProductFormCode(Long id);


	public abstract List<ProductFormCode> findAllProductFormCodes();


	public abstract List<ProductFormCode> findProductFormCodeEntries(int firstResult, int maxResults);


	public abstract void saveProductFormCode(ProductFormCode productFormCode);


	public abstract ProductFormCode updateProductFormCode(ProductFormCode productFormCode);

}
