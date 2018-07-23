package org.sample.bank.samplebank.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "ACCOUNTS")
@Table(name = "ACCOUNTS")
public class Account {

	@Id
	@Column(name = "ACCOUNT_NUMBER")
	@SequenceGenerator(name = "ACCOUNT_ID_GEN", sequenceName = "ACCOUNTS_SEQ", allocationSize = 25)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_GEN")
	private Integer accountNumber;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;
	
	@Column(name = "BALANCE")
	private Integer balance;
	
	@OneToMany(cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_NUMBER", insertable = false, updatable = false)
	private Set<Beneficiary> beneficiaries;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Set<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
}
