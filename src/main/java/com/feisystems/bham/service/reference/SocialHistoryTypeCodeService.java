package com.feisystems.bham.service.reference;
import java.util.List;

import com.feisystems.bham.service.dto.LookupDto;

public interface SocialHistoryTypeCodeService {

	public abstract List<LookupDto> findAllSocialHistoryTypeCodes();
}
