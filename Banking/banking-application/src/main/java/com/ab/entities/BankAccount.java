package com.ab.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)

@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class BankAccount {
	//@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	//@SequenceGenerator(name="seq", initialValue=1000, allocationSize=1000)
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
