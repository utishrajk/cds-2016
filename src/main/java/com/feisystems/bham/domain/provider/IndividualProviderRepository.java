
package com.feisystems.bham.domain.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProviderRepository extends JpaRepository<IndividualProvider, Long>, JpaSpecificationExecutor<IndividualProvider> {
	
	IndividualProvider findByUserName(String userName);
}
