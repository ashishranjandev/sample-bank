package org.sample.bank.samplebank.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.sample.bank.samplebank.commons.BankConstants;
import org.sample.bank.samplebank.enums.TransactionStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TransactionRequestDTO {

	@Null
	private Integer transactionId;
	
	@Null
	private Integer accountNumber;
	
	@NotNull
	private Integer beneficiaryId;
	
	@Null
	private Timestamp createdTime;
	
	@Null
	private String createdDate;
	
	private Timestamp scheduledTime;
	
	private String description;
	
	@NotNull
	private Boolean isInstant;
	
	@Null
	private TransactionStatus transactionStatus;
	
	@NotNull
	private Double amount;
	
	public TransactionRequestDTO() {}
	
	public TransactionRequestDTO(Integer transactionId, Integer accountNumber, Integer beneficiaryId,
			Timestamp createdTime, Timestamp scheduledTime, String description, Boolean isInstant,
			TransactionStatus transactionStatus, Double amount) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.beneficiaryId = beneficiaryId;
		this.createdTime = createdTime;
		this.scheduledTime = scheduledTime;
		this.description = description;
		this.isInstant = isInstant;
		this.transactionStatus = transactionStatus;
		this.amount = amount;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@JsonIgnore
	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	@JsonIgnore
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
		this.createdDate = BankConstants.DATE_TIME_FORMAT.format(new Date(createdTime.getTime()));
	}
	

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Timestamp scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsInstant() {
		return isInstant;
	}

	public void setIsInstant(Boolean isInstant) {
		this.isInstant = isInstant;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
