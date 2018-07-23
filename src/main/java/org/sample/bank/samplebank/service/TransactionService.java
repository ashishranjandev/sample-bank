package org.sample.bank.samplebank.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sample.bank.samplebank.dto.TransactionRequestDTO;
import org.sample.bank.samplebank.entities.TransactionRequest;
import org.sample.bank.samplebank.enums.TransactionStatus;
import org.sample.bank.samplebank.repository.TransactionRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TransactionService {

	@Autowired private TransactionRequestRepository transactionRequestRepository;
	
	public Optional<TransactionRequestDTO> createTransactionRequest(
			Integer accountNumber, TransactionRequestDTO transactionRequestDTO) {
		TransactionRequest tr = new TransactionRequest();
		BeanUtils.copyProperties(transactionRequestDTO, tr);
		tr.setAccountNumber(accountNumber);
		tr.setCreatedTime(new Timestamp(new Date().getTime()));
		tr.setTransactionStatus(TransactionStatus.INITIATED);
		
		TransactionRequest savedTR = transactionRequestRepository.save(tr);
		if(savedTR != null) {
			TransactionRequestDTO trDTO = new TransactionRequestDTO();
			BeanUtils.copyProperties(savedTR, trDTO);
			return Optional.of(trDTO);
		}
		return Optional.empty();
	}
	
	public List<TransactionRequestDTO> getTransactionRequests(Integer accountNumber) {
		List<TransactionRequest> transactions = transactionRequestRepository.findByAccountNumber(accountNumber);
		if (!CollectionUtils.isEmpty(transactions)) {
			List<TransactionRequestDTO> trDTOs = new ArrayList<>(transactions.size());
			transactions.forEach(transaction -> {
				TransactionRequestDTO trDTO = new TransactionRequestDTO();
				BeanUtils.copyProperties(transaction, trDTO);
				trDTOs.add(trDTO);
			});
			return trDTOs;
		}
		return new ArrayList<>(0);
	}
}
