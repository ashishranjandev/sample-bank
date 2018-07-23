package org.sample.bank.samplebank.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.bank.samplebank.SampleBankApplication;
import org.sample.bank.samplebank.commons.BankConstants;
import org.sample.bank.samplebank.config.ServiceTestConfiguration;
import org.sample.bank.samplebank.controller.AccountController;
import org.sample.bank.samplebank.dto.AccountDTO;
import org.sample.bank.samplebank.dto.AccountTransactionDTO;
import org.sample.bank.samplebank.dto.BeneficiaryDTO;
import org.sample.bank.samplebank.dto.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ServiceTestConfiguration.class, SampleBankApplication.class})
public class SampleBankApplicationTests {

	AccountDTO accountDTO = null;
	BeneficiaryDTO beneficiaryDTO = null;
	BeneficiaryDTO beneficiaryDTO2 = null;
	TransactionRequestDTO trDTO = null;

	Integer accountNumber = null;
	Integer beneficiaryId = null;
	
	@Autowired AccountController accountController;
	
	@Before
	public void init() {
		accountDTO = new AccountDTO(null, "John", "SF", 2000.00);
		beneficiaryDTO = new BeneficiaryDTO(null, 7882, "IFSC2012");
		beneficiaryDTO2 = new BeneficiaryDTO(null, 7881, "IFSC2022");
	}
	
	@Test
	public void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	public void testIntegrationTest() {
		ResponseEntity<AccountDTO> response = accountController.createAccount(accountDTO);
		assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
		AccountDTO accountDTO = response.getBody();
		assertNotNull(accountDTO.getAccountNumber());
		accountNumber = accountDTO.getAccountNumber();
		
		response = accountController.getAccount(accountNumber);
		assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
	
		ResponseEntity<BeneficiaryDTO> response3 = accountController.createBeneficiary(accountNumber, beneficiaryDTO2);
		assertEquals(response3.getStatusCode().value(), HttpStatus.CREATED.value());
		BeneficiaryDTO savedBeneficiaryDTO = response3.getBody();
		assertNotNull(savedBeneficiaryDTO.getBeneficiaryId());
		Integer toBeDeletedId = savedBeneficiaryDTO.getBeneficiaryId();
		
		response3 = accountController.createBeneficiary(accountNumber, beneficiaryDTO);
		assertEquals(response3.getStatusCode().value(), HttpStatus.CREATED.value());
		savedBeneficiaryDTO = response3.getBody();
		assertNotNull(savedBeneficiaryDTO.getBeneficiaryId());
		beneficiaryId = savedBeneficiaryDTO.getBeneficiaryId();
		
		ResponseEntity<List<BeneficiaryDTO>> getResponse = accountController.getBeneficiary(accountNumber);
		List<BeneficiaryDTO> list = getResponse.getBody();
		assertEquals(list.size(), 2);
		
		accountController.deleteBeneficiary(accountNumber, toBeDeletedId);
		getResponse = accountController.getBeneficiary(accountNumber);
		list = getResponse.getBody();
		assertEquals(list.size(), 1);
	
		trDTO = new TransactionRequestDTO(null, accountNumber, beneficiaryId, null, null, null, true, null, 200.00);
		ResponseEntity<TransactionRequestDTO> response2 = accountController.createTransactionRequest(accountNumber, trDTO);
		assertEquals(response2.getStatusCode().value(), HttpStatus.CREATED.value());
		
		ResponseEntity<AccountTransactionDTO> getResponse2 = accountController
				.getAccountWithTransactionRequests(accountNumber);
		assertEquals(getResponse2.getStatusCode().value(), HttpStatus.OK.value());
		List<TransactionRequestDTO> trDTOs = getResponse2.getBody().getTransactionRequests();
		assertEquals(trDTOs.size(), 1);
		
		Calendar calendar = Calendar.getInstance();         
		calendar.add(Calendar.YEAR, 1);
		ResponseEntity<Double> dResponse = accountController.getFutureBalance(accountNumber, 
				BankConstants.DATE_TIME_FORMAT.format(calendar.getTime()));
		assertEquals(dResponse.getStatusCode().value(), HttpStatus.OK.value());
		assertTrue(dResponse.getBody().equals(2079.78));
	}

}
