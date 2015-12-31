package com.feisystems.bham.service.provider;
import com.feisystems.bham.domain.provider.OrganizationalProvider;
import java.util.List;

public interface OrganizationalProviderService {

	public abstract long countAllOrganizationalProviders();


	public abstract void deleteOrganizationalProvider(OrganizationalProvider organizationalProvider);


	public abstract OrganizationalProvider findOrganizationalProvider(Long id);


	public abstract List<OrganizationalProvider> findAllOrganizationalProviders();


	public abstract List<OrganizationalProvider> findOrganizationalProviderEntries(int firstResult, int maxResults);


	public abstract void saveOrganizationalProvider(OrganizationalProvider organizationalProvider);


	public abstract OrganizationalProvider updateOrganizationalProvider(OrganizationalProvider organizationalProvider);

}
