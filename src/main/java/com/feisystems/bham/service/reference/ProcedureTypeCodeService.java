package com.feisystems.bham.service.reference;

import java.util.List;

import com.feisystems.bham.service.dto.LookupDto;

public interface ProcedureTypeCodeService {
	
	public abstract List<LookupDto> findAllProcedureTypeCodes();

}
