package com.feisystems.bham.domain.decisionfacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class RuleExecutionResponse.
 */
public class RuleExecutionResponse {

	private PatientGoal patientGoal;
	
	private Set<String> objectives = new HashSet<String>();
	
	private ProcedureCode procedureCode;
	
	private List<ProbabilityDistribution> probabilityDistributionList;
	
	public List<ProbabilityDistribution> getProbabilityDistributionList() {
		return probabilityDistributionList;
	}

	public void setProbabilityDistributionList(
			List<ProbabilityDistribution> probabilityDistributionList) {
		this.probabilityDistributionList = probabilityDistributionList;
	}

	public RuleExecutionResponse(PatientGoal patientGoal,
			Set<String> objective, ProcedureCode procedureCode, List<ProbabilityDistribution> probabilityDistributionList) {
		super();
		this.patientGoal = patientGoal;
		this.objectives = objective;
		this.procedureCode = procedureCode;
		this.probabilityDistributionList = probabilityDistributionList;
	}

	public Set<String> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<String> objectives) {
		this.objectives = objectives;
	}
	
	public PatientGoal getPatientGoal() {
		return patientGoal;
	}

	public void setPatientGoal(PatientGoal patientGoal) {
		this.patientGoal = patientGoal;
	}

	public ProcedureCode getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(ProcedureCode procedureCode) {
		this.procedureCode = procedureCode;
	}


}
