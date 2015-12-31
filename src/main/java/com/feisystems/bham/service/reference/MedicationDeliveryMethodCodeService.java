package com.feisystems.bham.service.reference;
import com.feisystems.bham.domain.reference.MedicationDeliveryMethodCode;
import java.util.List;

public interface MedicationDeliveryMethodCodeService {

	public abstract long countAllMedicationDeliveryMethodCodes();


	public abstract void deleteMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode);


	public abstract MedicationDeliveryMethodCode findMedicationDeliveryMethodCode(Long id);


	public abstract List<MedicationDeliveryMethodCode> findAllMedicationDeliveryMethodCodes();


	public abstract List<MedicationDeliveryMethodCode> findMedicationDeliveryMethodCodeEntries(int firstResult, int maxResults);


	public abstract void saveMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode);


	public abstract MedicationDeliveryMethodCode updateMedicationDeliveryMethodCode(MedicationDeliveryMethodCode medicationDeliveryMethodCode);

}
