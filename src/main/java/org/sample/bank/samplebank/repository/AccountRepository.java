package org.sample.bank.samplebank.repository;

import org.sample.bank.samplebank.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	
}
