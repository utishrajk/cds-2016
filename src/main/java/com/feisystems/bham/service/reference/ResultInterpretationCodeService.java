package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.ResultInterpretationCode;
import java.util.List;

public interface ResultInterpretationCodeService {

	public abstract long countAllResultInterpretationCodes();


	public abstract void deleteResultInterpretationCode(ResultInterpretationCode resultInterpretationCode);


	public abstract ResultInterpretationCode findResultInterpretationCode(Long id);


	public abstract List<ResultInterpretationCode> findAllResultInterpretationCodes();


	public abstract List<ResultInterpretationCode> findResultInterpretationCodeEntries(int firstResult, int maxResults);


	public abstract void saveResultInterpretationCode(ResultInterpretationCode resultInterpretationCode);


	public abstract ResultInterpretationCode updateResultInterpretationCode(ResultInterpretationCode resultInterpretationCode);

}
