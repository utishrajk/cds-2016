package com.feisystems.bham.service.reference;
import com.feisystems.bham.service.dto.LookupDto;

import java.util.List;

public interface StateCodeService {

	public abstract List<LookupDto> findAllStateCodes();

}
