package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.UnitOfMeasureCode;
import java.util.List;

public interface UnitOfMeasureCodeService {

	public abstract long countAllUnitOfMeasureCodes();


	public abstract void deleteUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode);


	public abstract UnitOfMeasureCode findUnitOfMeasureCode(Long id);


	public abstract List<UnitOfMeasureCode> findAllUnitOfMeasureCodes();


	public abstract List<UnitOfMeasureCode> findUnitOfMeasureCodeEntries(int firstResult, int maxResults);


	public abstract void saveUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode);


	public abstract UnitOfMeasureCode updateUnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode);

}
