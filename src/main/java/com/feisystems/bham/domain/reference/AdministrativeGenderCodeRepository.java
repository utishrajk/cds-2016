package com.feisystems.bham.domain.reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativeGenderCodeRepository extends JpaSpecificationExecutor<AdministrativeGenderCode>, JpaRepository<AdministrativeGenderCode, Long> {
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the administrative gender code
	 */
	public abstract AdministrativeGenderCode findByCode(String code);
	
}
