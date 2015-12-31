package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.EthnicGroupCode;
import java.util.List;

public interface EthnicGroupCodeService {

	public abstract long countAllEthnicGroupCodes();


	public abstract void deleteEthnicGroupCode(EthnicGroupCode ethnicGroupCode);


	public abstract EthnicGroupCode findEthnicGroupCode(Long id);


	public abstract List<EthnicGroupCode> findAllEthnicGroupCodes();


	public abstract List<EthnicGroupCode> findEthnicGroupCodeEntries(int firstResult, int maxResults);


	public abstract void saveEthnicGroupCode(EthnicGroupCode ethnicGroupCode);


	public abstract EthnicGroupCode updateEthnicGroupCode(EthnicGroupCode ethnicGroupCode);

}
