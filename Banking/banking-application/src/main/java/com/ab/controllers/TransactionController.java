package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.ab.entities.Transaction;
import com.ab.services.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/view_transactions")
	public String loadTransactions(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String accountToViewNumber = (String) session.getAttribute("accountToViewNumber");
		List<Transaction> accountTransactions = transactionService.loadAccountTransactions(accountToViewNumber);

		session.setAttribute("accountTransactions", accountTransactions);

		return "view_transactions";
	}


}