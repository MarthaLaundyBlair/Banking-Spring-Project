package com.ab.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ab.entities.CurrentAccount;


@Repository
public interface CurrentAccountRepository  extends JpaRepository<CurrentAccount, Integer> {

	@Query(value = "SELECT * FROM current_account c where c.customer_id = :customerId", nativeQuery=true) 
	List<CurrentAccount> findByCustomer(@Param("customerId") int customerId);

	@Query(value = "SELECT * FROM current_account c where c.account_number = :accountNumber", nativeQuery=true) 
	CurrentAccount findByNumber(@Param("accountNumber") String accountNumber);


	@Transactional
	@Modifying
	@Query(value = "UPDATE current_account c SET c.balance = :newBalance, c.overdraft = :newOverdraft WHERE c.account_number = :accountNumber", nativeQuery=true)
	int updateBalanceOverdraft(@Param("newBalance") double newBalance, @Param("newOverdraft") double newOverdraft, @Param("accountNumber") String accountNumber );


}

