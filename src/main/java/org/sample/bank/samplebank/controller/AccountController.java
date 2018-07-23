package org.sample.bank.samplebank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.sample.bank.samplebank.dto.AccountDTO;
import org.sample.bank.samplebank.dto.AccountTransactionDTO;
import org.sample.bank.samplebank.dto.BeneficiaryDTO;
import org.sample.bank.samplebank.dto.TransactionRequestDTO;
import org.sample.bank.samplebank.service.AccountsService;
import org.sample.bank.samplebank.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired private AccountsService accountsService;
	
	@Autowired private TransactionService transactionService;
	
	@PostMapping("/")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
		Optional<AccountDTO> savedAccountDTO = accountsService.createAccount(accountDTO);
		if(savedAccountDTO.isPresent()) {
			return new ResponseEntity<AccountDTO>(savedAccountDTO.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<AccountDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer accountNumber) {
		Optional<AccountDTO> savedAccountDTO =  accountsService.getAccount(accountNumber);
		if(savedAccountDTO.isPresent()) {
			return new ResponseEntity<AccountDTO>(savedAccountDTO.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{accountNumber}/beneficiaries")
	public ResponseEntity<List<BeneficiaryDTO>> getBeneficiary(
			@PathVariable Integer accountNumber) {
		List<BeneficiaryDTO> beneficiaries = accountsService.getBeneficiaries(accountNumber);
		return new ResponseEntity<List<BeneficiaryDTO>>(beneficiaries, HttpStatus.OK);
	}
	
	@DeleteMapping("/{accountNumber}/beneficiaries/{beneficiaryId}")
	public ResponseEntity<String> deleteBeneficiary(
			@PathVariable Integer accountNumber, @PathVariable Integer beneficiaryId) {
		accountsService.deleteBeneficiary(accountNumber, beneficiaryId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/{accountNumber}/beneficiaries")
	public ResponseEntity<BeneficiaryDTO> createBeneficiary(
			@PathVariable Integer accountNumber,
			@RequestBody @Valid BeneficiaryDTO beneficiaryDTO) {
		Optional<BeneficiaryDTO> savedBeneficiaryDTO = accountsService.createBeneficiary(accountNumber, beneficiaryDTO);
		if(savedBeneficiaryDTO.isPresent()) {
			return new ResponseEntity<BeneficiaryDTO>(savedBeneficiaryDTO.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<BeneficiaryDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{accountNumber}/transactionrequests")
	public ResponseEntity<TransactionRequestDTO> createTransactionRequest(
			@PathVariable Integer accountNumber,
			@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
		Optional<TransactionRequestDTO> savedTrDTO = 
				transactionService.createTransactionRequest(accountNumber, transactionRequestDTO);
		if(savedTrDTO.isPresent()) {
			return new ResponseEntity<TransactionRequestDTO>(savedTrDTO.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<TransactionRequestDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{accountNumber}/transactionrequests")
	public ResponseEntity<AccountTransactionDTO> getAccountWithTransactionRequests(
			@PathVariable Integer accountNumber) {
		Optional<AccountDTO> savedAccountDTO =  accountsService.getAccount(accountNumber);
		if(savedAccountDTO.isPresent()) {
			AccountTransactionDTO atDTO = new AccountTransactionDTO(); 
			BeanUtils.copyProperties(savedAccountDTO.get(), atDTO);
			atDTO.setTransactionRequests(transactionService.getTransactionRequests(accountNumber));
			return new ResponseEntity<AccountTransactionDTO>(atDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountTransactionDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
}
