package org.sample.bank.samplebank.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.sample.bank.samplebank.enums.TransactionStatus;

@Entity(name = "TRANSACTION_REQUEST")
@Table(name = "TRANSACTION_REQUEST")
public class TransactionRequest {
	
	@Id
	@Column(name = "TRANSACTION_ID")
	@SequenceGenerator(name = "TRANSACTION_REQUEST_GEN", sequenceName = "TRANSACTION_REQUEST_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_REQUEST_GEN")
	private Integer transactionId;
	
	@Column(name = "ACCOUNT_NUMBER")
	private Integer accountNumber;
	
	@Column(name = "BENEFICIARY_ID")
	private Integer beneficiaryId;

	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;
	
	@Column(name = "SCHEDULED_TIME")
	private Timestamp scheduledTime;
	
	@Column(name = "DESCRIPTION")
	private Boolean description;
	
	@Column(name = "IS_INSTANT")
	private Boolean isInstant;
	
	@Column(name = "STATUS")
	private TransactionStatus transactionStatus;
	
	@Column(name = "AMOUNT")
	private Integer amount;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Timestamp scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	public Boolean getDescription() {
		return description;
	}

	public void setDescription(Boolean description) {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
