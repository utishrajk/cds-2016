package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.TelecomUseCode;
import com.feisystems.bham.domain.reference.TelecomUseCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TelecomUseCodeServiceImpl implements TelecomUseCodeService {

	@Autowired
    TelecomUseCodeRepository telecomUseCodeRepository;

	public long countAllTelecomUseCodes() {
        return telecomUseCodeRepository.count();
    }

	public void deleteTelecomUseCode(TelecomUseCode telecomUseCode) {
        telecomUseCodeRepository.delete(telecomUseCode);
    }

	public TelecomUseCode findTelecomUseCode(Long id) {
        return telecomUseCodeRepository.findOne(id);
    }

	public List<TelecomUseCode> findAllTelecomUseCodes() {
        return telecomUseCodeRepository.findAll();
    }

	public List<TelecomUseCode> findTelecomUseCodeEntries(int firstResult, int maxResults) {
        return telecomUseCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveTelecomUseCode(TelecomUseCode telecomUseCode) {
        telecomUseCodeRepository.save(telecomUseCode);
    }

	public TelecomUseCode updateTelecomUseCode(TelecomUseCode telecomUseCode) {
        return telecomUseCodeRepository.save(telecomUseCode);
    }
}
