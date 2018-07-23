package org.sample.bank.samplebank.repository;

import org.sample.bank.samplebank.entities.TransactionRequest;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRequestRepository extends CrudRepository<TransactionRequest, Integer> {

}
