package com.feisystems.bham.service.clinicaldata;

import com.feisystems.bham.domain.clinicaldata.HospitalDischargeInstruction;
import com.feisystems.bham.domain.clinicaldata.HospitalDischargeInstructionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospitalDischargeInstructionServiceImpl implements HospitalDischargeInstructionService {

	@Autowired
    HospitalDischargeInstructionRepository hospitalDischargeInstructionRepository;

	public long countAllHospitalDischargeInstructions() {
        return hospitalDischargeInstructionRepository.count();
    }

	public void deleteHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction) {
        hospitalDischargeInstructionRepository.delete(hospitalDischargeInstruction);
    }

	public HospitalDischargeInstruction findHospitalDischargeInstruction(Long id) {
        return hospitalDischargeInstructionRepository.findOne(id);
    }

	public List<HospitalDischargeInstruction> findAllHospitalDischargeInstructions() {
        return hospitalDischargeInstructionRepository.findAll();
    }

	public List<HospitalDischargeInstruction> findHospitalDischargeInstructionEntries(int firstResult, int maxResults) {
        return hospitalDischargeInstructionRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction) {
        hospitalDischargeInstructionRepository.save(hospitalDischargeInstruction);
    }

	public HospitalDischargeInstruction updateHospitalDischargeInstruction(HospitalDischargeInstruction hospitalDischargeInstruction) {
        return hospitalDischargeInstructionRepository.save(hospitalDischargeInstruction);
    }
}
