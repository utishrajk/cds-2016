package com.feisystems.bham.service.reference;

import com.feisystems.bham.domain.reference.ResultInterpretationCode;
import com.feisystems.bham.domain.reference.ResultInterpretationCodeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResultInterpretationCodeServiceImpl implements ResultInterpretationCodeService {

	@Autowired
    ResultInterpretationCodeRepository resultInterpretationCodeRepository;

	public long countAllResultInterpretationCodes() {
        return resultInterpretationCodeRepository.count();
    }

	public void deleteResultInterpretationCode(ResultInterpretationCode resultInterpretationCode) {
        resultInterpretationCodeRepository.delete(resultInterpretationCode);
    }

	public ResultInterpretationCode findResultInterpretationCode(Long id) {
        return resultInterpretationCodeRepository.findOne(id);
    }

	public List<ResultInterpretationCode> findAllResultInterpretationCodes() {
        return resultInterpretationCodeRepository.findAll();
    }

	public List<ResultInterpretationCode> findResultInterpretationCodeEntries(int firstResult, int maxResults) {
        return resultInterpretationCodeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveResultInterpretationCode(ResultInterpretationCode resultInterpretationCode) {
        resultInterpretationCodeRepository.save(resultInterpretationCode);
    }

	public ResultInterpretationCode updateResultInterpretationCode(ResultInterpretationCode resultInterpretationCode) {
        return resultInterpretationCodeRepository.save(resultInterpretationCode);
    }
}
