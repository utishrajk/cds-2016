package com.feisystems.bham.service.clinicaldata;

import java.util.List;

import com.feisystems.bham.domain.clinicaldata.Outcome;
import com.feisystems.bham.service.dto.OutcomeDto;

public interface OutcomeService {

	public abstract void deleteOutcome(OutcomeDto dto);

	public abstract OutcomeDto findOutcome(Long id);

	public abstract List<OutcomeDto> findAllOutcomes();

	public abstract void saveOutcome(OutcomeDto dto);

	public abstract Outcome updateOutcome(OutcomeDto dto);

	public abstract List<OutcomeDto> findAllOutcomesByPatientId(Long patientId);
}
