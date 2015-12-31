package com.feisystems.bham.service.clinicaldata;
import java.util.List;

import com.feisystems.bham.domain.clinicaldata.ProcedureObservation;
import com.feisystems.bham.service.dto.ProcedureObservationDto;

public interface ProcedureObservationService {

	public abstract void deleteProcedureObservation(ProcedureObservationDto dto);


	public abstract ProcedureObservationDto findProcedureObservation(Long id);


	public abstract List<ProcedureObservationDto> findAllProcedureObservations();


	public abstract void saveProcedureObservation(ProcedureObservationDto dto);


	public abstract ProcedureObservation updateProcedureObservation(ProcedureObservationDto dto);
	
	
	public abstract List<ProcedureObservationDto> findAllProcedureObservationsByPatientId(Long patientId); 

}
