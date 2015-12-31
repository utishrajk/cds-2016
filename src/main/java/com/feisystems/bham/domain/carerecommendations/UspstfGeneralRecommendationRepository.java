package com.feisystems.bham.domain.carerecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UspstfGeneralRecommendationRepository extends JpaRepository<UspstfGeneralRecommendation, Long>, JpaSpecificationExecutor<UspstfGeneralRecommendation> {
}
