package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.TelecomUseCode;
import java.util.List;

public interface TelecomUseCodeService {

	public abstract long countAllTelecomUseCodes();


	public abstract void deleteTelecomUseCode(TelecomUseCode telecomUseCode);


	public abstract TelecomUseCode findTelecomUseCode(Long id);


	public abstract List<TelecomUseCode> findAllTelecomUseCodes();


	public abstract List<TelecomUseCode> findTelecomUseCodeEntries(int firstResult, int maxResults);


	public abstract void saveTelecomUseCode(TelecomUseCode telecomUseCode);


	public abstract TelecomUseCode updateTelecomUseCode(TelecomUseCode telecomUseCode);

}
