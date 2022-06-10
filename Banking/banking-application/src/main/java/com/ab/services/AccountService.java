package com.ab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.CurrentAccount;
import com.ab.entities.SavingsAccount;
import com.ab.repositories.CurrentAccountRepository;
import com.ab.repositories.SavingsAccountRepository;

@Service
public class AccountService {

	@Autowired
	private CurrentAccountRepository currentAccountRepository;

	@Autowired
	private SavingsAccountRepository savingsAccountRepository;

	// Write Business methods/logic

	public CurrentAccount saveCurrentAccount(CurrentAccount account) {

		return this.currentAccountRepository.save(account);

	}

	public SavingsAccount saveSavingsAccount(SavingsAccount account) {

		return this.savingsAccountRepository.save(account);

	}

	public List<CurrentAccount> loadCurrentAccounts(int customerId){
		return this.currentAccountRepository.findByCustomer(customerId);
	}

	public List<SavingsAccount> loadSavingsAccounts(int customerId){
		return this.savingsAccountRepository.findByCustomer(customerId);
	}
	/////////////////////////////////

	public CurrentAccount findCurrentAccountByNumber(String accountNumber){
		return this.currentAccountRepository.findByNumber(accountNumber);
	}

	public SavingsAccount findSavingsAccountByNumber(String accountNumber){
		return this.savingsAccountRepository.findByNumber(accountNumber);
	}

	//////////////////////////////////


	public void updateBalance(double newBalance, double newOverdraft, String accountNumber) {
		this.currentAccountRepository.updateBalanceOverdraft(newBalance, newOverdraft, accountNumber);
	}

	/////////////////////////////////	
	public void depositMoneySavings(double money, String accountNumber) {
		this.savingsAccountRepository.depositMoney(money, accountNumber);
	}

	public void withdrawMoneySavings(double money, String accountNumber) {
		this.savingsAccountRepository.withdrawMoney(money, accountNumber);
	}

	/////////////////////

	public void updatePoints (String accountNumber, int points) {
		this.savingsAccountRepository.updatePoints(accountNumber, points);
	}

}