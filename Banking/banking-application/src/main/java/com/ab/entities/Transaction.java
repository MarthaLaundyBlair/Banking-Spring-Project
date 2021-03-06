package com.ab.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Transaction {

	@Id @GeneratedValue
	private int transactionId;

	private String accountNumber;

	private String transactionType;

	private double amount;

	private double balance;

	private LocalDateTime dateOfTransaction;


	public Transaction(String accountNumber, String transactionType, double amount, double balance) {
		this.transactionType = transactionType;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.balance = balance;
		this.dateOfTransaction = LocalDateTime.now();

	}


	public void setTransactionType(String transactionType){

		switch(transactionType) {
		case "Deposit":
			this.transactionType = transactionType;
			break;
		case "Withdraw":
			this.transactionType = transactionType;
			break;
		case "Opening Balance":
			this.transactionType = transactionType;
			break;
		case "Reversal":
			this.transactionType = transactionType;
			break;

		}


	}

}
