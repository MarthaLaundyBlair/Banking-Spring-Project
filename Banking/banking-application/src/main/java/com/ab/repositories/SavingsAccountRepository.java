package com.ab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ab.entities.CurrentAccount;
import com.ab.entities.SavingsAccount;

@Repository
public interface SavingsAccountRepository  extends JpaRepository<SavingsAccount, Integer> {

	@Query(value = "SELECT * FROM savings_account s where s.customer_id = :customerId", nativeQuery=true) 
	List<SavingsAccount> findByCustomer(@Param("customerId") int customerId);
	
	@Query(value = "SELECT * FROM savings_account c where c.account_number = :accountNumber", nativeQuery=true) 
	SavingsAccount findByNumber(@Param("accountNumber") String accountNumber);

	@Transactional
	@Modifying
	@Query(value = "UPDATE savings_account c SET c.balance = c.balance +:money WHERE c.account_number = :accountNumber", nativeQuery=true)
	int depositMoney(@Param("money") double money, @Param("accountNumber") String accountNumber );

	@Transactional
	@Modifying
	@Query(value = "UPDATE savings_account c SET c.balance = c.balance -:money WHERE c.account_number = :accountNumber", nativeQuery=true)
	int withdrawMoney(@Param("money") double money, @Param("accountNumber") String accountNumber );

	@Transactional
	@Modifying
	@Query(value = "UPDATE savings_account s SET s.points = s.points + 1 WHERE s.account_number = :accountNumber", nativeQuery=true)
	int updatePoints( @Param("accountNumber") String accountNumber );

	
	
}