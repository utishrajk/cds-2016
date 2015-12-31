package com.feisystems.bham.service.clinicaldata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.domain.clinicaldata.ProcedureObservationRepository;
import com.feisystems.bham.domain.patient.Patient;
import com.feisystems.bham.domain.patient.PatientRepository;
import com.feisystems.bham.infrastructure.DtoToDomainEntityMapper;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

@Service
@Transactional(rollbackFor = { ClinicalDataNotFoundException.class })
public class ProcedureObservationServiceImpl implements ProcedureObservationService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
    private ProcedureObservationRepository procedureObservationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DtoToDomainEntityMapper<ProcedureObservationDto, ProcedureObservation> procedureDtoToProcedureMapper;
	
	@Override
	public ProcedureObservationDto findProcedureObservation(Long id) {
        ProcedureObservation procedure =  procedureObservationRepository.findOne(id);
        
        if(procedure != null) {
        	return modelMapper.map(procedure, ProcedureObservationDto.class);
        }
        throw new ClinicalDataNotFoundException(id);
    }
	
	@Override
	public List<ProcedureObservationDto> findAllProcedureObservations() {
        List<ProcedureObservationDto> procedureDtoList = new ArrayList<>();
        List<ProcedureObservation> procedureList = procedureObservationRepository.findAll();
        
        if(procedureList.isEmpty()) {
        	throw new IllegalArgumentException("No Procudre Found");
        }
        
        for(ProcedureObservation procedure : procedureList) {
        	ProcedureObservationDto procedureDto = modelMapper.map(procedure, ProcedureObservationDto.class);
        	procedureDtoList.add(procedureDto);
        }
        
        return procedureDtoList;
    }
	
	@Override
	public void saveProcedureObservation(ProcedureObservationDto dto) {
		ProcedureObservation procedure = procedureDtoToProcedureMapper.map(dto);
        procedureObservationRepository.save(procedure);
    }
	
	@Override
	public ProcedureObservation updateProcedureObservation(ProcedureObservationDto dto) {
        ProcedureObservation procedure = procedureDtoToProcedureMapper.map(dto);
        
        if(procedure == null) {
        	throw new ClinicalDataNotFoundException(dto.getId());
        }
        
        return procedureObservationRepository.save(procedure);
    }


	@Override
	public void deleteProcedureObservation(ProcedureObservationDto dto) {
        procedureObservationRepository.delete(dto.getId());
    }
	
	@Override
	public List<ProcedureObservationDto> findAllProcedureObservationsByPatientId(Long patientId) {
		List<ProcedureObservationDto> dtoList = new ArrayList<>();
		Patient patient = patientRepository.findOne(patientId);
		
		Set<ProcedureObservation> procedureList = patient.getProcedureObservations();
		
		for(ProcedureObservation procedure : procedureList) {
			ProcedureObservationDto dto = modelMapper.map(procedure, ProcedureObservationDto.class);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

}
