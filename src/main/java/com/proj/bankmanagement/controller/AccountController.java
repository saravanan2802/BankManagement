package com.proj.bankmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dto.Account;
import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accountService;

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateAccountValue(@RequestBody Account a,
			@RequestParam int accountId) {
		return accountService.updateAcount(a, accountId);
	}
	@PutMapping("/changeaccount")
	public ResponseEntity<ResponseStructure<Account>> changeAccountType(@RequestParam int accountId,
			@RequestParam int accountType) {
		return accountService.changeAccountType(accountId, accountType);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Account>> findAccountByAccountNumber(@RequestParam long accountNumber){
		return accountService.findAccountWithAccountNumber(accountNumber);
	}
}
