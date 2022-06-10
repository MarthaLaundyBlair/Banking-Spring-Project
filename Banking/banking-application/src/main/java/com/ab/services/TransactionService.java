package com.ab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Transaction;
import com.ab.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction recordTransaction(String accountNumber, String transactionType, double amount, double balance) {


		Transaction transactionRecord = new Transaction(accountNumber, transactionType, amount, balance);
		return this.transactionRepository.save(transactionRecord);

	}

	public List<Transaction> loadAccountTransactions(String accountNumber){
		return this.transactionRepository.findByNumber(accountNumber);
	}


}
