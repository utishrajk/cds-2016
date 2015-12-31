package com.feisystems.bham.service.provider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.feisystems.bham.domain.provider.IndividualProvider;
import com.feisystems.bham.domain.provider.IndividualProviderRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.security.SecurityUtils;
import com.feisystems.bham.service.clinicaldata.ClinicalDataNotFoundException;
import com.feisystems.bham.service.dto.IndividualProviderDto;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndividualProviderServiceImpl implements IndividualProviderService {
	
	private final Logger log = LoggerFactory.getLogger(IndividualProviderServiceImpl.class);
	
    @Inject
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    IndividualProviderRepository individualProviderRepository;
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/** The individualProvider profile dto to individualProvider mapper. */
	@Autowired
	private DtoToDomainEntityMapper<IndividualProviderDto, IndividualProvider> individualProviderDtoToIndividualProviderMapper;

	public IndividualProviderDto findIndividualProvider(Long id) {
		IndividualProvider individualProvider = individualProviderRepository.findOne(id);
		if(individualProvider != null) {
			return modelMapper.map(individualProvider, IndividualProviderDto.class);
		}
		throw new ClinicalDataNotFoundException(1L);
    }

	public IndividualProvider updateIndividualProvider(IndividualProviderDto individualProviderDto) {
		IndividualProvider individualProvider = individualProviderDtoToIndividualProviderMapper.map(individualProviderDto);
		if (individualProvider != null) {
			return individualProviderRepository.save(individualProvider);	
		} 
		throw new ClinicalDataNotFoundException(individualProviderDto.getId());
    }

	/** {@inheritDoc} */
	public List<IndividualProviderDto> findAllIndividualProviders() {
		List<IndividualProviderDto> individualProviderProfileDtoList = new ArrayList<>();
        List<IndividualProvider> individualProviderList =  individualProviderRepository.findAll();
        
        if (individualProviderList != null &&  individualProviderList.size() > 0) {
            for(IndividualProvider individualProvider: individualProviderList) {
            	IndividualProviderDto individualProviderDto = modelMapper.map(individualProvider, IndividualProviderDto.class);
            	individualProviderProfileDtoList.add(individualProviderDto);
            }
            return individualProviderProfileDtoList;
        }
		throw new IllegalArgumentException("No Care Manager Found");
	}
	
	public void deleteIndividualProvider(IndividualProvider individualProvider) {
        individualProviderRepository.delete(individualProvider);
    }

	public void saveIndividualProvider(IndividualProviderDto individualProviderDto) {
		IndividualProvider individualProvider = individualProviderDtoToIndividualProviderMapper.map(individualProviderDto);
        individualProviderRepository.save(individualProvider);
    }
	
   public void updateUserInformation(String firstName, String lastName, String email) {
        IndividualProvider currentUser = individualProviderRepository.findByUserName(SecurityUtils.getCurrentLogin());
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        individualProviderRepository.save(currentUser);
        log.debug("Changed Information for IndividualProvider: {}", currentUser);
    }

    public void changePassword(String password) {
        IndividualProvider currentUser = individualProviderRepository.findByUserName(SecurityUtils.getCurrentLogin());
        String encryptedPassword = passwordEncoder.encode(password);
        currentUser.setCredential(encryptedPassword);
        individualProviderRepository.save(currentUser);
        log.debug("Changed password for IndividualProvider: {}", currentUser);
    }

	@Transactional(readOnly = true)
    public IndividualProvider getUserWithAuthorities() {
        IndividualProvider currentUser = individualProviderRepository.findByUserName(SecurityUtils.getCurrentLogin());
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

}
