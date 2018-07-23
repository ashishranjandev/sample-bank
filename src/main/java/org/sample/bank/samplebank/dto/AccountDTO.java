package org.sample.bank.samplebank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class AccountDTO {

	@Null
	private Integer accountNumber;
	
	@NotNull
	private String name;
	
	@NotNull
	private String address;

	@Min(500)
	private Double balance;
	
	public AccountDTO() {}

	public AccountDTO(Integer accountNumber, String name, String address, Double balance) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.address = address;
		this.balance = balance;
	}

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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
