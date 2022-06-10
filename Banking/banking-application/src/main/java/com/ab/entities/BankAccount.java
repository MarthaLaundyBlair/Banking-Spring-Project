package com.ab.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class BankAccount {

	@Id @GeneratedValue
	protected int accountId;

	protected String accountNumber;

	protected String accountName;

	protected int customerId;

	protected Double balance = 0.00;

	protected LocalDateTime dateOpened;


	public BankAccount(int customerId, String accountName ) {

		this.customerId = customerId;
		this.dateOpened = LocalDateTime.now();
		this.accountName = accountName;

	}

	public String getDateOpenedString() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = this.dateOpened.format(formatter); 

		return formattedDateTime;
	}

	public void depositMoney(double money) {
		this.balance = this.balance + money;
	}

	public void withdrawMoney(double money) {
		this.balance = this.balance - money;
	}


}
