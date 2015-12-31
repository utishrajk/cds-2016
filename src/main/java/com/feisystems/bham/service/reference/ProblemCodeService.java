package com.feisystems.bham.service.reference;

import com.feisystems.bham.service.dto.LookupDto;
import java.util.List;

public interface ProblemCodeService {

	public abstract List<LookupDto> findAllProblemCodes();
}
