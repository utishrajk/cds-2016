package com.feisystems.bham.domain.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureTypeCodeRepository extends JpaRepository<ProcedureTypeCode, Long>, JpaSpecificationExecutor<ProcedureTypeCode> {
	
	public abstract ProcedureTypeCode findByCode(String code);

}
