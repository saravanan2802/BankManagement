package com.proj.bankmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.BankDao;
import com.proj.bankmanagement.dao.BranchDao;
import com.proj.bankmanagement.dto.Bank;
import com.proj.bankmanagement.dto.Branch;

@Service
public class BranchService {

	@Autowired
	BranchDao branchDao;
	@Autowired
	BankDao bankDao;

	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch b) {
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		rs.setData(branchDao.saveBranch(b));
		rs.setMsg("Branch has been saved");
		rs.setStatus(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Branch>>(rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Branch>> findBranchById(int id) {
		ResponseStructure<Branch> rs = new ResponseStructure<>();

		if (branchDao.findBranchById(id) != null) {
			rs.setData(branchDao.findBranchById(id));
			rs.setMsg("Branch with Id " + id + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs, HttpStatus.FOUND);
		}
		return null; // No Branch Found with id

	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		ResponseStructure<List<Branch>> rs = new ResponseStructure<>();
		rs.setData(branchDao.findAllBranch());
		rs.setMsg("All Books were Found");
		rs.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(rs, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranchById(int id) {
		ResponseStructure<Branch> rs = new ResponseStructure<>();

		if (branchDao.findBranchById(id) != null) {
			rs.setData(branchDao.deleteBranch(id));
			rs.setMsg("Branch with Id " + id + " has been deleted");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs, HttpStatus.CREATED);
		}
		return null; // No Branch Found
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(int id, Branch b) {
		ResponseStructure<Branch> rs = new ResponseStructure<>();

		if (branchDao.findBranchById(id) != null) {
			rs.setData(branchDao.updateBranch(id, b));
			rs.setMsg("Branch with Id" + id + " has been Updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs, HttpStatus.CREATED);
		}
		return null; // No Branch Found
	}
	
	public ResponseEntity<ResponseStructure<Branch>> addBankToBranch(int branchId,int bankId){
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		
		if (bankDao.findBankById(bankId)!=null) {
			Bank exBank = bankDao.findBankById(bankId);
			if (branchDao.findBranchById(branchId)!=null) {
				Branch exBranch = branchDao.findBranchById(branchId);
				exBranch.setBranchBank(exBank);
				rs.setData(branchDao.updateBranch(branchId, exBranch));
				rs.setMsg("Bank with Id " + bankId + " added to Branch");
				rs.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.CREATED);
			}
			return null; // no branch found
		}
		return null; //no bank found
	}

}
