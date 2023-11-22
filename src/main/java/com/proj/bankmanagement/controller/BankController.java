package com.proj.bankmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dto.Bank;
import com.proj.bankmanagement.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank b) {
		return bankService.saveBank(b);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Bank>> findBankById(@RequestParam int id) {
		return bankService.findBankById(id);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Bank>>> findAllBank() {
		return bankService.findAllBank();
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Bank>> deleteBank(@RequestParam int id) {
		return bankService.deleteBankById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Bank>> updateBank(@RequestBody Bank b, @RequestParam int id) {
		return bankService.updateBank(id, b);
	}

	@PutMapping("/assign")
	public ResponseEntity<ResponseStructure<Bank>> addBranch(@RequestParam int branchId, @RequestParam int bankId) {
		return bankService.setBranchToBank(branchId, bankId);
	}
}
