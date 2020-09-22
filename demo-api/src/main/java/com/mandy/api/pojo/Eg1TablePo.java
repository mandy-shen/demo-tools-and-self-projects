package com.mandy.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Eg1TablePo {
	
	private String caseType;

    private String banNo;

    private String cmpyName;
    
    private String cmpyAddress;
    
    private String investAmt;
    
    private String approvedDate;
    
    private String dissolutionDate;

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getBanNo() {
		return banNo;
	}

	public void setBanNo(String banNo) {
		this.banNo = banNo;
	}

	public String getCmpyName() {
		return cmpyName;
	}

	public void setCmpyName(String cmpyName) {
		this.cmpyName = cmpyName;
	}

	public String getCmpyAddress() {
		return cmpyAddress;
	}

	public void setCmpyAddress(String cmpyAddress) {
		this.cmpyAddress = cmpyAddress;
	}

	public String getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
	}

	public String getDissolutionDate() {
		return dissolutionDate;
	}

	public void setDissolutionDate(String dissolutionDate) {
		this.dissolutionDate = dissolutionDate;
	}

	@Override
	public String toString() {
		return "Eg1TablePo [caseType=" + caseType + ", banNo=" + banNo + ", cmpyName=" + cmpyName + ", cmpyAddress="
				+ cmpyAddress + ", investAmt=" + investAmt + ", approvedDate=" + approvedDate + ", dissolutionDate="
				+ dissolutionDate + "]";
	}

}
