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
public class BankService {
	@Autowired
	BankDao bankDao;
	@Autowired
	BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();
		rs.setData(bankDao.saveBank(b));
		rs.setMsg("Bank has been saved");
		rs.setStatus(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Bank>>(rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Bank>> findBankById(int id) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();

		if (bankDao.findBankById(id) != null) {
			rs.setData(bankDao.findBankById(id));
			rs.setMsg("Bank with Id " + id + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Bank>>(rs, HttpStatus.FOUND);
		}
		return null; // No Bank Found with id

	}

	public ResponseEntity<ResponseStructure<List<Bank>>> findAllBank() {
		ResponseStructure<List<Bank>> rs = new ResponseStructure<>();
		rs.setData(bankDao.findAllBank());
		rs.setMsg("All Books were Found");
		rs.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Bank>>>(rs, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Bank>> deleteBankById(int id) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();

		if (bankDao.findBankById(id) != null) {
			rs.setData(bankDao.deleteBank(id));
			rs.setMsg("Bank with Id " + id + " has been deleted");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bank>>(rs, HttpStatus.CREATED);
		}
		return null; // No Bank Found
	}

	public ResponseEntity<ResponseStructure<Bank>> updateBank(int id, Bank b) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();

		if (bankDao.findBankById(id) != null) {
			rs.setData(bankDao.updateBank(id, b));
			rs.setMsg("Bank with Id" + id + " has been Updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bank>>(rs, HttpStatus.CREATED);
		}
		return null; // No Bank Found
	}

	public ResponseEntity<ResponseStructure<Bank>> setBranchToBank(int branchId, int bankId) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();

		if (bankDao.findBankById(bankId) != null) {
			Bank exBank = bankDao.findBankById(bankId);
			if (branchDao.findBranchById(branchId) != null) {
				Branch exBranch = branchDao.findBranchById(branchId);
				List<Branch> branches = exBank.getBankBranches();
				branches.add(exBranch);
				exBank.setBankBranches(branches);
				rs.setData(bankDao.updateBank(bankId, exBank));
				rs.setMsg("Branch with id " + branchId + " added to Bank");
				rs.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Bank>>(rs, HttpStatus.CREATED);
			}
			return null; // No Branch Found
		}
		return null; // No Bank Found
	}
}
