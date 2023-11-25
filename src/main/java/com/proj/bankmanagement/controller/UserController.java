package com.proj.bankmanagement.controller;

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
import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User u, @RequestParam int accountType,
			@RequestParam String managerName, @RequestParam String managerPassword) {

		return userService.saveUserWithAcountBranch(u, accountType, managerName, managerPassword);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUserById(@RequestParam int id){
		return userService.findUserById(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUserById(@RequestParam int id){
		return userService.deleteUserById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User u, @RequestParam int id){
		return userService.updateUser(u, id);
	}
	
}
