package com.feisystems.bham.weka;

public enum ServiceCodesEnum {
	
	H0018("H0018","Behavioral health; short-term residential"),
	H2034("H2034","Alcohol and/or drug abuse halfway house services, per diem"),
	H0004("H0004","Behavioral health counseling and therapy, per 15 minutes"),
	H0009("H0009","Alcohol and/or drug services; acute detoxification (hospital inpatient)"),
	H0006("H0006","Alcohol and/or drug services; case management");
	
	private ServiceCodesEnum(String serviceCode, String serviceDescription) {
		this.serviceCode = serviceCode;
		this.serviceDescription = serviceDescription;
	}

	private String serviceCode;
	
	private String serviceDescription;
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

}
