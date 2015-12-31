package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.EthnicGroupCode;
import com.feisystems.bham.domain.reference.EthnicGroupCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EthnicGroupCodeServiceImpl implements EthnicGroupCodeService {

	@Autowired
    EthnicGroupCodeRepository ethnicGroupCodeRepository;

	public long countAllEthnicGroupCodes() {
        return ethnicGroupCodeRepository.count();
    }

	public void deleteEthnicGroupCode(EthnicGroupCode ethnicGroupCode) {
        ethnicGroupCodeRepository.delete(ethnicGroupCode);
    }

	public EthnicGroupCode findEthnicGroupCode(Long id) {
        return ethnicGroupCodeRepository.findOne(id);
    }

	public List<EthnicGroupCode> findAllEthnicGroupCodes() {
        return ethnicGroupCodeRepository.findAll();
    }

	public List<EthnicGroupCode> findEthnicGroupCodeEntries(int firstResult, int maxResults) {
        return ethnicGroupCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEthnicGroupCode(EthnicGroupCode ethnicGroupCode) {
        ethnicGroupCodeRepository.save(ethnicGroupCode);
    }

	public EthnicGroupCode updateEthnicGroupCode(EthnicGroupCode ethnicGroupCode) {
        return ethnicGroupCodeRepository.save(ethnicGroupCode);
    }
}
