package org.sample.bank.samplebank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sample.bank.samplebank.dto.AccountDTO;
import org.sample.bank.samplebank.dto.BeneficiaryDTO;
import org.sample.bank.samplebank.entities.Account;
import org.sample.bank.samplebank.entities.Beneficiary;
import org.sample.bank.samplebank.repository.AccountRepository;
import org.sample.bank.samplebank.repository.BeneficiaryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AccountsService {

	@Autowired private AccountRepository accountRepository;
	
	@Autowired private BeneficiaryRepository beneficiaryRepository;
	
	public Optional<AccountDTO> createAccount(AccountDTO accountDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDTO, account);
		
		Account savedAccount = accountRepository.save(account);
		return getAccountDTOFromEntity(savedAccount);
	}
	
	public Optional<AccountDTO> getAccount(Integer accountNumber) {
		Account savedAccount = accountRepository.findOne(accountNumber);
		return getAccountDTOFromEntity(savedAccount); 
	}
	
	public Optional<BeneficiaryDTO> createBeneficiary(Integer accountNumber, 
			BeneficiaryDTO beneficiaryDTO) {
		Beneficiary beneficiary = new Beneficiary();
		BeanUtils.copyProperties(beneficiaryDTO, beneficiary);
		beneficiary.setAccountNumber(accountNumber);
		
		Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
		return getDTOFromEntity(savedBeneficiary);
	}
	
	public List<BeneficiaryDTO> getBeneficiaries(Integer accountNumber) {
		List<Beneficiary> savedBeneficiaries = beneficiaryRepository.findByAccountNumber(accountNumber);
		if(!CollectionUtils.isEmpty(savedBeneficiaries)) {
			List<BeneficiaryDTO> beneficiaryDTOs = new ArrayList<>(savedBeneficiaries.size());
			savedBeneficiaries.forEach(savedBeneficiary -> {
				beneficiaryDTOs.add(getDTOFromEntity(savedBeneficiary).get());
			});
			return beneficiaryDTOs;
		} 
		return new ArrayList<>(0);
	}
	
	public void deleteBeneficiary(Integer accountNumber, Integer beneficiaryId) {
		beneficiaryRepository.delete(beneficiaryId);
	}
	
	private Optional<AccountDTO> getAccountDTOFromEntity(Account savedAccount) {
		if(savedAccount != null) {
			AccountDTO savedAccountDTO = new AccountDTO();
			BeanUtils.copyProperties(savedAccount, savedAccountDTO);
			return Optional.of(savedAccountDTO);
		} else {
			return Optional.empty();
		}
	}
	
	private Optional<BeneficiaryDTO> getDTOFromEntity(Beneficiary savedBeneficiary) {
		if(savedBeneficiary != null) {
			BeneficiaryDTO savedBeneficiaryDTO = new BeneficiaryDTO();
			BeanUtils.copyProperties(savedBeneficiary, savedBeneficiaryDTO);
			return Optional.of(savedBeneficiaryDTO);
		} else {
			return Optional.empty();
		}
	}
}
