package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{


	@Query(value = "SELECT * FROM customer c where c.username = :username and c.password = :password", nativeQuery=true) 
	Customer findByUsername(@Param("username") String username, @Param("password") String password);



}
