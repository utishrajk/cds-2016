package com.feisystems.bham.domain.decisionfacts;

public class PatientGoal {
	
	public String shortTermGoal;
	
	public String longTermGoal;

	public PatientGoal(String shortTermGoal, String longTermGoal) {
		super();
		this.shortTermGoal = shortTermGoal;
		this.longTermGoal = longTermGoal;
	}

	public String getShortTermGoal() {
		return shortTermGoal;
	}

	public void setShortTermGoal(String shortTermGoal) {
		this.shortTermGoal = shortTermGoal;
	}

	public String getLongTermGoal() {
		return longTermGoal;
	}

	public void setLongTermGoal(String longTermGoal) {
		this.longTermGoal = longTermGoal;
	}

}
