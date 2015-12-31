package com.feisystems.bham.domain.reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySiteCodeRepository extends JpaSpecificationExecutor<BodySiteCode>, JpaRepository<BodySiteCode, Long> {

	public abstract BodySiteCode findByCode(String code);	
}
