package com.ab.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ab.entities.Customer;

import com.ab.services.CustomerService;

@Controller
public class CustomerController {


	@Autowired
	private CustomerService customerService;

	// Http Request Mapping methods


	@RequestMapping(value="/",method = RequestMethod.GET )
	public String landing() {
		return "landing";
	}

	/////////////////////////////////////////

	@GetMapping("/register")
	public String registerForm() {
		return "register";
	}


	@PostMapping("/register")
	public String registerProcess(@ModelAttribute Customer customer) {

		Customer registeredCustomer = this.customerService.saveCustomer(customer);

		if(registeredCustomer != null) {
			return "login";
		}
		else {
			return "reg_failure";
		}
	}

	/////////////////////////////////////////////

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String loginProcess(String username, String password, HttpServletRequest request) {

		Customer loggedInCustomer = this.customerService.loginCustomer(username, password);


		if(loggedInCustomer != null) {


			HttpSession session = request.getSession(true);


			session.setAttribute("loggedInCustomer", loggedInCustomer);



			return "welcome";
		}
		else {
			return "login";
		}
	}

	////////////////////////////////////////////////////

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}




}
