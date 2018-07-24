package org.sample.bank.samplebank.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.sample.bank.samplebank.commons.BankConstants;
import org.sample.bank.samplebank.dto.AccountDTO;
import org.sample.bank.samplebank.dto.AccountTransactionDTO;
import org.sample.bank.samplebank.dto.BeneficiaryDTO;
import org.sample.bank.samplebank.dto.TransactionRequestDTO;
import org.sample.bank.samplebank.service.AccountsService;
import org.sample.bank.samplebank.service.TransactionService;
import org.sample.bank.samplebank.utils.BankUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class for all Account related calls
 * <ul>
 * 	<li> Create Account </li>
 * 	<li> Get Account </li>
 *  <li> Create Beneficiary</li>
 *  <li> Get Beneficiary</li>
 *  <li> Delete Beneficiary</li>
 *  <li> Create transaction request</li>
 *  <li> Get transaction requests</li>
 *  <li> Get Future Balance</li>
 * </ul
 * 
 * @author Ashish
 *
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired private AccountsService accountsService;
	
	@Autowired private TransactionService transactionService;
	
	/**
	 * Controller Method to Create Account
	 * @param accountDTO
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
		log.info("Received account creation request with Name : {} ", accountDTO.getName());
		Optional<AccountDTO> savedAccountDTO = accountsService.createAccount(accountDTO);
		if(savedAccountDTO.isPresent()) {
			return new ResponseEntity<AccountDTO>(savedAccountDTO.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<AccountDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Controller Method to get Account Number
	 * @param accountNumber
	 * @return
	 */
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer accountNumber) {
		log.info("Received get account details request with acc number : {} ", accountNumber);
		Optional<AccountDTO> savedAccountDTO =  accountsService.getAccount(accountNumber);
		if(savedAccountDTO.isPresent()) {
			return new ResponseEntity<AccountDTO>(savedAccountDTO.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Controller Method to get beneficiary
	 * @param accountNumber
	 * @return
	 */
	@GetMapping("/{accountNumber}/beneficiaries")
	public ResponseEntity<List<BeneficiaryDTO>> getBeneficiary(
			@PathVariable Integer accountNumber) {
		log.info("Received get beneficiaries request with acc number : {} ", accountNumber);
		List<BeneficiaryDTO> beneficiaries = accountsService.getBeneficiaries(accountNumber);
		return new ResponseEntity<List<BeneficiaryDTO>>(beneficiaries, HttpStatus.OK);
	}
	
	/**
	 * Controller Method to 
	 * @param accountNumber
	 * @param beneficiaryId
	 * @return
	 */
	@DeleteMapping("/{accountNumber}/beneficiaries/{beneficiaryId}")
	public ResponseEntity<String> deleteBeneficiary(
			@PathVariable Integer accountNumber, @PathVariable Integer beneficiaryId) {
		log.info("Received delete beneficiary request with acc number : {} and beneficary id: {}", accountNumber,
				beneficiaryId);
		accountsService.deleteBeneficiary(accountNumber, beneficiaryId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * Controller Method to create beneficiary
	 * @param accountNumber
	 * @param beneficiaryDTO
	 * @return
	 */
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
	
	/**
	 * Controller Method to create transaction requests
	 * @param accountNumber
	 * @param transactionRequestDTO
	 * @return
	 */
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
	
	/**
	 * Controller Method to Get Transaction Requests
	 * @param accountNumber
	 * @return
	 */
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
	
	/**
	 * Controller Method to Get Future Balance
	 * @param accountNumber
	 * @param dateStr
	 * @return
	 */
	@GetMapping("/{accountNumber}/balance")
	public ResponseEntity<Double> getFutureBalance(
			@PathVariable Integer accountNumber, @RequestParam String dateStr) {
		Date requestedDate = null;
		try {
			requestedDate = BankConstants.DATE_TIME_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<AccountDTO> savedAccountDTO =  accountsService.getAccount(accountNumber);
		if(savedAccountDTO.isPresent()) {
			return new ResponseEntity<Double>(BankUtils.getAmount(savedAccountDTO.get().getBalance(), requestedDate, 4.0f), HttpStatus.OK);
		}
		return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
	}
	
}
