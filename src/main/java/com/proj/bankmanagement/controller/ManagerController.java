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
import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	ManagerService managerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@RequestBody Manager m,@RequestParam int branchId){
		return managerService.saveManager(m, branchId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(@RequestParam int id){
		return managerService.deleteManager(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Manager>> findManagerById(@RequestParam int id){
		return managerService.findManagerById(id);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Manager>>> findAllManager(){
		return managerService.findAllManager();
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Manager>> updateManager(@RequestBody Manager m, @RequestParam int id){
		return managerService.updateManager(id, m);
	}
}
