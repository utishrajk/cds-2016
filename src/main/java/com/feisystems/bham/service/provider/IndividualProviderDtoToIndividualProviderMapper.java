package com.feisystems.bham.service.provider;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.feisystems.bham.domain.provider.Authority;
import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.domain.provider.IndividualProviderRepository;
import com.feisystems.bham.domain.reference.AdministrativeGenderCode;
import com.feisystems.bham.domain.reference.AdministrativeGenderCodeRepository;
import com.feisystems.bham.domain.reference.ProviderTaxonomyCode;
import com.feisystems.bham.domain.reference.ProviderTaxonomyCodeRepository;
import com.feisystems.bham.domain.reference.StateCodeRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.security.AuthoritiesConstants;
import com.feisystems.bham.service.dto.IndividualProviderDto;

/**
 * The Class IndividualProviderDtoToIndividualProviderMapper.
 */
@Component
public class IndividualProviderDtoToIndividualProviderMapper implements
		DtoToDomainEntityMapper<IndividualProviderDto, IndividualProvider> {

	/** The individualProvider repository. */
	private IndividualProviderRepository individualProviderRepository;
	
	/** The administrative gender code repository. */
	private AdministrativeGenderCodeRepository administrativeGenderCodeRepository;

	/** The Provider Taxonomy Code Repository. */
	private ProviderTaxonomyCodeRepository providerTaxonomyCodeRepository;
	
    @Inject
    private PasswordEncoder passwordEncoder;
	
	/**
	 * Instantiates a new individualProvider dto to individualProvider mapper.
	 *
	 * @param individualProviderRepository the individualProvider repository
	 * @param stateCodeRepository the state code repository
	 * @param countryCodeRepository the country code repository
	 * @param administrativeGenderCodeRepository the administrative gender code repository
	 */
	@Autowired
	public IndividualProviderDtoToIndividualProviderMapper(
			IndividualProviderRepository individualProviderRepository,
			StateCodeRepository stateCodeRepository,
			ProviderTaxonomyCodeRepository providerTaxonomyCodeRepository,
			AdministrativeGenderCodeRepository administrativeGenderCodeRepository) {
		
		super();
		
		this.individualProviderRepository = individualProviderRepository;
		this.administrativeGenderCodeRepository = administrativeGenderCodeRepository;
		this.providerTaxonomyCodeRepository = providerTaxonomyCodeRepository;
	}

	/* (non-Javadoc)
	 * @see com.feisystems.bham.infrastructure.DtoToDomainEntityMapper#map(java.lang.Object)
	 */
	@Override
	public IndividualProvider map(IndividualProviderDto individualProviderDto) {
		IndividualProvider individualProvider = null;

		if(individualProviderDto.getUserName() != null) {
			individualProvider = individualProviderRepository.findByUserName(individualProviderDto.getUserName());
		}
		else {
			individualProvider = new IndividualProvider();
		}

		// Return null if no IndividualProvider found
		if (individualProvider == null) {
			return null;
		}
		
		
		individualProvider.setUserName(individualProviderDto.getUserName());
		
		// for Create
		if(individualProvider.getUserName() == null) {
			individualProvider.setUserName(individualProviderDto.getEmail());
			Authority authority = new Authority();
			authority.setName(AuthoritiesConstants.ROLE_USER);
			
			Set<Authority> set = new HashSet<>();
			set.add(authority);
			
			individualProvider.setAuthorities(set);
		}
		
		String credential = individualProviderDto.getCredential();
        if (credential != null && !credential.equals("")) {
			String encryptedPassword = passwordEncoder.encode(individualProviderDto.getCredential());
			individualProvider.setCredential(encryptedPassword);
        }
		individualProvider.setFirstName(individualProviderDto.getFirstName());
		individualProvider.setLastName(individualProviderDto.getLastName());
		individualProvider.setEmail(individualProviderDto.getEmail());
		
		individualProvider.setNamePrefix(individualProviderDto.getNamePrefix());
		individualProvider.setMiddleName(individualProviderDto.getMiddleName());
		individualProvider.setNameSuffix(individualProviderDto.getNameSuffix());

		if (StringUtils.hasText(individualProviderDto.getAdministrativeGenderCode())) {
			AdministrativeGenderCode administrativeGenderCode = administrativeGenderCodeRepository
					.findByCode(individualProviderDto.getAdministrativeGenderCode());
			individualProvider.setAdministrativeGenderCode(administrativeGenderCode);
		} else {
			individualProvider.setAdministrativeGenderCode(null);
		}

		individualProvider.setNpi(individualProviderDto.getNpi());
		
		if (individualProviderDto.getProviderTaxonomyCode() != null && StringUtils.hasText(individualProviderDto.getProviderTaxonomyCode())) {
			ProviderTaxonomyCode providerTaxonomyCode = providerTaxonomyCodeRepository
					.findByCode(individualProviderDto.getProviderTaxonomyCode());
			individualProvider.setProviderTaxonomyCode(providerTaxonomyCode);
		} else {
			individualProvider.setProviderTaxonomyCode(null);
		}
		
		// Contact
		individualProvider.setWebsite(individualProviderDto.getWebsite());
		individualProvider.setMailingAddressTelephoneNumber(individualProviderDto.getMailingAddressTelephoneNumber());
		individualProvider.setMailingAddressFaxNumber(individualProviderDto.getMailingAddressFaxNumber());
		
		// Mailing Address
		individualProvider.setFirstLineMailingAddress(individualProviderDto.getFirstLineMailingAddress());
		individualProvider.setSecondLineMailingAddress(individualProviderDto.getSecondLineMailingAddress());
		individualProvider.setMailingAddressCityName(individualProviderDto.getMailingAddressCityName());
		individualProvider.setMailingAddressStateName(individualProviderDto.getMailingAddressStateName());
		individualProvider.setMailingAddressPostalCode(individualProviderDto.getMailingAddressPostalCode());
		individualProvider.setMailingAddressCountryCode("US");
		
		individualProvider.setPracticeLocationAddressCityName("test");
		individualProvider.setPracticeLocationAddressCountryCode("US");
		individualProvider.setPracticeLocationAddressFaxNumber("1231231231");
		individualProvider.setPracticeLocationAddressPostalCode("21333");
		individualProvider.setPracticeLocationAddressStateName("Maryland");
		individualProvider.setPracticeLocationAddressTelephoneNumber("1231231231");
		individualProvider.setFirstLinePracticeLocationAddress("test");
		individualProvider.setSecondLinePracticeLocationAddress("test");
		individualProvider.setEnumerationDate("date");
		individualProvider.setLastUpdateDate("date");
		individualProvider.setVerified(true);
		
		return individualProvider;
	}

}
