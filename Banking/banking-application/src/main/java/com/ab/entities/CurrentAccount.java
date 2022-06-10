package com.ab.entities;

import java.util.Random;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class CurrentAccount extends BankAccount {

	public double overdraft = 200 ;

	public CurrentAccount (int customerId, String accountName) {

		super(customerId, accountName);


		Random r = new Random( System.currentTimeMillis() );
		int accountSeed =  10000 + r.nextInt(20000);

		this.accountNumber = "Cur" + Integer.toString(accountSeed) + Integer.toString(this.customerId);

	}


	public double getOverdraft() {
		return overdraft;
	}

	@Override
	public void withdrawMoney(double money) {
		double newbalance = this.balance - money;
		if (newbalance >= 0) {
			this.balance = newbalance;
		}
		else {
			this.balance = 0.0;
			this.overdraft = this.overdraft + newbalance;

		}

	}	
	@Override
	public void depositMoney(double money) {
		double newbalance = this.balance + money;
		if (this.overdraft < 200.0) {
			this.overdraft = this.overdraft + newbalance;
			if (this.overdraft > 200.0) {
				this.balance = this.overdraft - 200.0;
				this.overdraft = 200.0;
			}
		}
		else {
			this.balance = newbalance;

		}

	}



}
