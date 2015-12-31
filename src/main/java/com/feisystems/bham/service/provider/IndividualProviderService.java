package com.feisystems.bham.service.provider;
import java.util.List;

import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.service.dto.IndividualProviderDto;

public interface IndividualProviderService {

	public abstract void deleteIndividualProvider(IndividualProvider individualProvider);

	public abstract IndividualProviderDto findIndividualProvider(Long id);

	public abstract void saveIndividualProvider(IndividualProviderDto individualProviderDto);

	public abstract IndividualProvider updateIndividualProvider(IndividualProviderDto individualProviderDto);
	
	public List<IndividualProviderDto> findAllIndividualProviders();
	
	public IndividualProvider getUserWithAuthorities();
	
	public void changePassword(String password);
	
	public void updateUserInformation(String firstName, String lastName, String email);
	
}
