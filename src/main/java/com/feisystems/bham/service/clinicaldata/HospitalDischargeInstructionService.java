package com.feisystems.bham.service.clinicaldata;
import com.feisystems.bham.domain.clinicaldata.HospitalDischargeInstruction;
import java.util.List;

public interface HospitalDischargeInstructionService {

	public abstract long countAllHospitalDischargeInstructions();


	public abstract void deleteHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction);


	public abstract HospitalDischargeInstruction findHospitalDischargeInstruction(Long id);


	public abstract List<HospitalDischargeInstruction> findAllHospitalDischargeInstructions();


	public abstract List<HospitalDischargeInstruction> findHospitalDischargeInstructionEntries(int firstResult, int maxResults);


	public abstract void saveHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction);


	public abstract HospitalDischargeInstruction updateHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction);

}
