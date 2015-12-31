package com.feisystems.bham.domain.reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageCodeRepository extends JpaRepository<LanguageCode, Long>, JpaSpecificationExecutor<LanguageCode> {
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the language code
	 */
	public abstract LanguageCode findByCode(String code);
}
