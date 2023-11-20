package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Address;
import com.proj.bankmanagement.dto.Bank;
import com.proj.bankmanagement.repo.BankRepo;

@Repository
public class BankDao {
	@Autowired
	BankRepo repo;
	
	public Bank saveBank(Bank a) {
		return repo.save(a);
	}

	public Bank findBankById(int id) {
		Optional<Bank> bank = repo.findById(id);

		if (bank.isPresent()) {
			return bank.get();
		} else {
			return null;
		}
	}

	public List<Bank> findAllBank() {
		return repo.findAll();
	}

	public Bank deleteBank(int id) {
		Bank exBank = findBankById(id);

		if (exBank != null) {
			repo.delete(exBank);
			return exBank;
		} else {
			return null;
		}
	}
	
	public Bank updateBank(int id,Bank bank ) {
		Bank exBank = findBankById(id);
		
		if (exBank!=null) {
			bank.setBankId(id);
			return repo.save(bank);
		}else {
			return null;
		}
	}
}
