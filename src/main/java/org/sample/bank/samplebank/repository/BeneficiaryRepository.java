package org.sample.bank.samplebank.repository;

import java.util.List;

import org.sample.bank.samplebank.entities.Beneficiary;
import org.springframework.data.repository.CrudRepository;

public interface BeneficiaryRepository extends CrudRepository<Beneficiary, Integer> {

	List<Beneficiary> findByAccountNumber(Integer accountNumber);
}
