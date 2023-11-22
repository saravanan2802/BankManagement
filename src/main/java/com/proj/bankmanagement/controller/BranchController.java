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
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	BranchService branchService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> saveBank(@RequestBody Branch b) {
		return branchService.saveBranch(b);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(@RequestParam int id) {
		return branchService.findBranchById(id);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		return branchService.findAllBranch();
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@RequestParam int id) {
		return branchService.deleteBranchById(id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch b, @RequestParam int id) {
		return branchService.updateBranch(id, b);
	}
	
	@PutMapping("/assign")
	public ResponseEntity<ResponseStructure<Branch>> addBank(@RequestParam int branchId, @RequestParam int bankId) {
		return branchService.addBankToBranch(bankId, bankId);
	}
}
