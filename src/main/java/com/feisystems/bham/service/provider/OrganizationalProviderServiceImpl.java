package com.feisystems.bham.service.provider;

import com.feisystems.bham.domain.provider.OrganizationalProvider;
import com.feisystems.bham.domain.provider.OrganizationalProviderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationalProviderServiceImpl implements OrganizationalProviderService {

	@Autowired
    OrganizationalProviderRepository organizationalProviderRepository;

	public long countAllOrganizationalProviders() {
        return organizationalProviderRepository.count();
    }

	public void deleteOrganizationalProvider(OrganizationalProvider organizationalProvider) {
        organizationalProviderRepository.delete(organizationalProvider);
    }

	public OrganizationalProvider findOrganizationalProvider(Long id) {
        return organizationalProviderRepository.findOne(id);
    }

	public List<OrganizationalProvider> findAllOrganizationalProviders() {
        return organizationalProviderRepository.findAll();
    }

	public List<OrganizationalProvider> findOrganizationalProviderEntries(int firstResult, int maxResults) {
        return organizationalProviderRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveOrganizationalProvider(OrganizationalProvider organizationalProvider) {
        organizationalProviderRepository.save(organizationalProvider);
    }

	public OrganizationalProvider updateOrganizationalProvider(OrganizationalProvider organizationalProvider) {
        return organizationalProviderRepository.save(organizationalProvider);
    }
}
