package com.ab.entities;

import java.util.Random;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class SavingsAccount extends BankAccount {

	public int points = 1;

	public SavingsAccount (int customerId, String accountName) {

		super(customerId, accountName);

		Random r = new Random( System.currentTimeMillis() );
		int accountSeed =  10000 + r.nextInt(20000);

		this.accountNumber = "Sav" + Integer.toString(accountSeed) + Integer.toString(this.customerId);


	}

	public int getPoints() {
		return points;
	}

	public void updatePoints() {

		int newPoints = (int) Math.round(this.balance/1000);
		System.out.print("new points " + newPoints);

		if (newPoints >= this.points )

			this.points = newPoints + 1 ;

	}



}
