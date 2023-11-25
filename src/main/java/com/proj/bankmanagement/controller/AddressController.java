package com.proj.bankmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dto.Address;
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addressService;

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> createAddressForUser(@RequestBody Address a,
			@RequestParam int userId) {
		return addressService.createAddressForUser(a, userId);
	}

	@PostMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(@RequestBody Address a,
			@RequestParam int branchId) {
		return addressService.createAddressForBranch(a, branchId);
	}

	@PostMapping("/manager")
	public ResponseEntity<ResponseStructure<Manager>> createAddressForManager(@RequestBody Address a,
			@RequestParam int managerId) {
		return addressService.createAddressForManager(a, managerId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address a, @RequestParam int id) {
		return addressService.updateAddress(a, id);
	}
}
