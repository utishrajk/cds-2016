package com.feisystems.bham.domain.reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TelecomUseCodeRepository extends JpaSpecificationExecutor<TelecomUseCode>, JpaRepository<TelecomUseCode, Long> {
}
