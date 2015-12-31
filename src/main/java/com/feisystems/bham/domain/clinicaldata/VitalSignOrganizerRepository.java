package com.feisystems.bham.domain.clinicaldata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalSignOrganizerRepository extends JpaRepository<VitalSignOrganizer, Long>, JpaSpecificationExecutor<VitalSignOrganizer> {
}
