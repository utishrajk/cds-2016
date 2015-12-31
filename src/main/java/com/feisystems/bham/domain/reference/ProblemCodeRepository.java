package com.feisystems.bham.domain.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemCodeRepository extends JpaRepository<ProblemCode, Long>, JpaSpecificationExecutor<ProblemCode> {

	public abstract ProblemCode findByCode(String code);
}
