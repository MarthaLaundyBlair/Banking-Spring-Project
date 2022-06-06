package com.ab.entities;

import java.util.Random;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class SavingsAccount extends BankAccount {
	
	public double points = 1;
	
		public SavingsAccount (int customerId, String accountName) {
				
				super(customerId, accountName);
		
				Random r = new Random( System.currentTimeMillis() );
				int accountSeed =  10000 + r.nextInt(20000);
		
				this.accountNumber = "Sav" + Integer.toString(accountSeed) + Integer.toString(this.customerId);
				
				
			}
		
			public double getPoints() {
				return points;
			}
			
			public void addPoint() {
				this.points = this.points + 1;
	}


}
