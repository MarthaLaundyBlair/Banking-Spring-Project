package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import com.ab.entities.CurrentAccount;
import com.ab.entities.Customer;
import com.ab.entities.SavingsAccount;
import com.ab.services.AccountService;
import com.ab.services.TransactionService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;


	@GetMapping("/createAccount")
	public String createAccount() {
		return "create_account";
	}

	//////////////////////////////////////////
	@PostMapping("/createCurrentAccount")
	public String createCurrentAccount(String accountName, HttpServletRequest request) {

		HttpSession session = request.getSession(true);


		Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

		CurrentAccount a = new CurrentAccount(loggedInCustomer.getCustomerId(), accountName);

		CurrentAccount b = this.accountService.saveCurrentAccount(a);

		session.setAttribute("currentAccount", b);


		return "new_current";
	}

	////////////////////////////////////////
	@PostMapping("/createSavingsAccount")
	public String createSavingsAccount(String accountName, HttpServletRequest request) {

		HttpSession session = request.getSession(true);


		Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");
		System.out.println(loggedInCustomer);

		SavingsAccount a = new SavingsAccount(loggedInCustomer.getCustomerId(), accountName);

		SavingsAccount b = this.accountService.saveSavingsAccount(a);

		session.setAttribute("savingsAccount", b);

		return "new_savings";

	}

	/////////////////////////////////////
	@GetMapping("/accounts")
	public String getCustomerAccounts(HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

		List<CurrentAccount> currentAccounts = accountService.loadCurrentAccounts(loggedInCustomer.getCustomerId());
		session.setAttribute("currentAccountList", currentAccounts);


		List<SavingsAccount> savingsAccounts = accountService.loadSavingsAccounts(loggedInCustomer.getCustomerId());
		session.setAttribute("savingsAccountList", savingsAccounts);

		return "accounts";

	}


	@PostMapping("/accounts")
	public String viewCurrentAccount(HttpServletRequest request, String accountToViewNumber, String accountToViewType){


		HttpSession session = request.getSession(true);
		session.setAttribute("accountToViewNumber", accountToViewNumber);
		session.setAttribute("accountToViewType",accountToViewType);

		if (accountToViewType.equals("Cur")) {

			CurrentAccount ca = accountService.findCurrentAccountByNumber(accountToViewNumber);
			session.setAttribute("currentAccountToView", ca);

			return "view_current_account";

		}
		else if (accountToViewType.equals("Sav")) {

			SavingsAccount sa = accountService.findSavingsAccountByNumber(accountToViewNumber);
			session.setAttribute("savingsAccountToView", sa);

			return "view_savings_account";

		}

		return "accounts";

	}


	//////////////////////////////////

	@PostMapping("/depositCurrent")
	public String depositCurrentAccount(HttpServletRequest request, double money){


		HttpSession session = request.getSession(true);
		String accountToViewNumber = (String) session.getAttribute("accountToViewNumber");
		CurrentAccount currentAccountToView = (CurrentAccount) session.getAttribute("currentAccountToView");

		//accountService.depositMoneyCurrent(money, accountToViewNumber);
		currentAccountToView.depositMoney(money);

		accountService.updateBalance(currentAccountToView.getBalance(), currentAccountToView.getOverdraft(), accountToViewNumber);

		transactionService.recordTransaction(accountToViewNumber, "Deposit", money, currentAccountToView.getBalance());

		session.setAttribute("currentAccountToView", currentAccountToView);

		return "view_current_account";

	}


	@PostMapping("/withdrawCurrent")
	public String withdrawCurrentAccount(HttpServletRequest request, double money){


		HttpSession session = request.getSession(true);
		String accountToViewNumber = (String) session.getAttribute("accountToViewNumber");
		CurrentAccount currentAccountToView = (CurrentAccount) session.getAttribute("currentAccountToView");

		if (currentAccountToView.getBalance() + currentAccountToView.getOverdraft( )>= money) {

			currentAccountToView.withdrawMoney(money);
			accountService.updateBalance(currentAccountToView.getBalance(), currentAccountToView.getOverdraft(), accountToViewNumber);
			transactionService.recordTransaction(accountToViewNumber, "Withdrawl", money, currentAccountToView.getBalance());
			session.setAttribute("currentAccountToView", currentAccountToView);

			return "view_current_account";
		}else {
			transactionService.recordTransaction(accountToViewNumber, "FailedWithdrawl", money, currentAccountToView.getBalance());
			return "insufficient_funds";
		}
	}

	@PostMapping("/depositSavings")
	public String depositSavingsAccount(HttpServletRequest request, double money){


		HttpSession session = request.getSession(true);
		String accountToViewNumber = (String) session.getAttribute("accountToViewNumber");
		SavingsAccount savingsAccountToView = (SavingsAccount) session.getAttribute("savingsAccountToView");

		accountService.depositMoneySavings(money, accountToViewNumber);
		savingsAccountToView.depositMoney(money);
		transactionService.recordTransaction(accountToViewNumber, "Deposit", money, savingsAccountToView.getBalance());
		session.setAttribute("savingsAccountToView", savingsAccountToView);

		if (savingsAccountToView.getBalance() >= (savingsAccountToView.getPoints())*1000) {



			savingsAccountToView.updatePoints();
			accountService.updatePoints(accountToViewNumber, savingsAccountToView.getPoints());

			return "you_won";
		}

		return "view_savings_account";

	}

	@GetMapping("/view_savings_account")
	public String viewSavingsAccount() {
		return "view_savings_account";
	}


	@PostMapping("/withdrawSavings")
	public String withdrawSavingsAccount(HttpServletRequest request, double money){


		HttpSession session = request.getSession(true);
		String accountToViewNumber = (String) session.getAttribute("accountToViewNumber");
		SavingsAccount savingsAccountToView = (SavingsAccount) session.getAttribute("savingsAccountToView");

		if (savingsAccountToView.getBalance() >= money) {
			accountService.withdrawMoneySavings(money, accountToViewNumber);
			savingsAccountToView.withdrawMoney(money);

			transactionService.recordTransaction(accountToViewNumber, "Withdrawl", money, savingsAccountToView.getBalance());
			session.setAttribute("savingsAccountToView", savingsAccountToView);

			return "view_savings_account";
		}else {
			transactionService.recordTransaction(accountToViewNumber, "FailedWithdrawl", money, savingsAccountToView.getBalance());
			return "insufficient_funds";
		}

	}

}
