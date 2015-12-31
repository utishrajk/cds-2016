package com.feisystems.bham.domain.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CgiSCodeRepository extends JpaRepository<CgiSCode, Long>, JpaSpecificationExecutor<CgiSCode> {

	public abstract CgiSCode findByCode(String code);

}
