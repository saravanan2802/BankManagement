package com.proj.bankmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dto.Transaction;
import com.proj.bankmanagement.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TranactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(@RequestParam long fromAccountNumber,
			@RequestParam long toAccountNumber, @RequestParam double amount, @RequestParam String fromAccountpassword) {
		return transactionService.sendMoney(fromAccountNumber, fromAccountpassword, amount, toAccountNumber);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> updatePendingTransactions() {
		return transactionService.updatePendingTransaction();
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> getFilteredTransaction(@RequestParam String userName,
			@RequestParam String accountPassword, @RequestParam int month) {
		return transactionService.getFilteredTransactions(userName, accountPassword, month);
	}
}
