package com.feisystems.bham.domain.reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderTaxonomyCodeRepository extends JpaSpecificationExecutor<ProviderTaxonomyCode>, JpaRepository<ProviderTaxonomyCode, Long> {
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the ProviderTaxonomyCode
	 */
	public abstract ProviderTaxonomyCode findByCode(String code);
}
