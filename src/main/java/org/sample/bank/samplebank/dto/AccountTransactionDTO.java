package org.sample.bank.samplebank.dto;

import java.util.List;

public class AccountTransactionDTO {

	private Integer accountNumber;
	
	private String name;
	
	private String address;

	private Integer balance;
	
	List<TransactionRequestDTO> transactionRequests;

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

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<TransactionRequestDTO> getTransactionRequests() {
		return transactionRequests;
	}

	public void setTransactionRequests(List<TransactionRequestDTO> transactionRequests) {
		this.transactionRequests = transactionRequests;
	}

}
