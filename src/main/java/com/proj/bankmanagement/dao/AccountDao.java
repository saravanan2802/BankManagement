package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Account;
import com.proj.bankmanagement.repo.AccountRepo;

@Repository
public class AccountDao {
	@Autowired
	AccountRepo repo;

	public Account saveAccount(Account a) {
		return repo.save(a);
	}

	public Account findAccountById(int id) {
		Optional<Account> account = repo.findById(id);

		if (account.isPresent()) {
			return account.get();
		} else {
			return null;
		}
	}

	public List<Account> findAllAccount() {
		return repo.findAll();
	}

	public Account deleteAccount(int id) {
		Account exAccount = findAccountById(id);

		if (exAccount != null) {
			repo.delete(exAccount);
			return exAccount;
		} else {
			return null;
		}
	}
	
	public Account updateAccount(Account a,int id) {
		Account exAccount = findAccountById(id);
		
		if (exAccount!=null) {
			if (a.getAccountBalance()<=0) {
				a.setAccountBalance(exAccount.getAccountBalance());
			}
			if (a.getAccountNumber()<0) {
				a.setAccountNumber(exAccount.getAccountNumber());
			}
			if(a.getAccountPassword().equals(null)) {
				a.setAccountPassword(exAccount.getAccountPassword());
			}
			if (a.getAccountTransactions().isEmpty()) {
				a.setAccountTransactions(exAccount.getAccountTransactions());
			}
			if (a.getAccountType()==null) {
				a.setAccountType(exAccount.getAccountType());
			}
			if(a.getAccountUser()==null) {
				a.setAccountUser(exAccount.getAccountUser());
			}
			a.setAccountId(id);
			return repo.save(a);
		}
		return null; //no account found
	}
}
