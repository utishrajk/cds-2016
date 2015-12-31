package com.feisystems.bham.service.reference;

import java.util.List;
import com.feisystems.bham.service.dto.LookupDto;

public interface CgiICodeService {

	public abstract List<LookupDto> findAllCgiICodes();

}
