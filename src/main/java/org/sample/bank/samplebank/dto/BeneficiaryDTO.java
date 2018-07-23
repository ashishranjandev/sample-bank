package org.sample.bank.samplebank.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class BeneficiaryDTO {

	@Null
	private Integer beneficiaryId;
	
	@NotNull
	private Integer beneficiaryAccountNumber;
	
	@NotNull
	private String bankIdentificationCode;
	
	public BeneficiaryDTO() {}

	public BeneficiaryDTO(Integer beneficiaryId, Integer beneficiaryAccountNumber, String bankIdentificationCode) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.bankIdentificationCode = bankIdentificationCode;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Integer getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(Integer beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public String getBankIdentificationCode() {
		return bankIdentificationCode;
	}

	public void setBankIdentificationCode(String bankIdentificationCode) {
		this.bankIdentificationCode = bankIdentificationCode;
	}
	
}
