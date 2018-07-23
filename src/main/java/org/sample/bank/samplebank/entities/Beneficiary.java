package org.sample.bank.samplebank.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "BENEFICIARIES")
@Table(name = "BENEFICIARIES")
public class Beneficiary {

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "BENEFICIARIES_ID_GEN", sequenceName = "BENEFICIARIES_SEQ", 
						allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BENEFICIARIES_ID_GEN")
	private Integer beneficiaryId;
	
	@Column(name = "ACCOUNT_NUMBER")
	private Integer accountNumber;
	
	@Column(name = "BENEFICIARY_ACC_NUMBER")
	private Integer beneficiaryAccountNumber;
	
	@Column(name = "BIC")
	private String bankIdentificationCode;
	
	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(Integer beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getBankIdentificationCode() {
		return bankIdentificationCode;
	}

	public void setBankIdentificationCode(String bankIdentificationCode) {
		this.bankIdentificationCode = bankIdentificationCode;
	}
	
}
