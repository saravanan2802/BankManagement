package com.proj.bankmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.AccountDao;
import com.proj.bankmanagement.dto.Account;
import com.proj.bankmanagement.dto.AccountType;
import com.proj.bankmanagement.dto.User;

@Service
public class AccountService {
	@Autowired
	AccountDao accountDao;

	public ResponseEntity<ResponseStructure<User>> updateAcount(Account a, int accountId) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		Account exAccount = accountDao.findAccountById(accountId);

		if (exAccount != null) {
			exAccount.setAccountBalance(a.getAccountBalance());
			exAccount.setAccountNumber(a.getAccountNumber());
			exAccount.setAccountPassword(a.getAccountPassword());
			Account updatedAccount = accountDao.updateAccount(exAccount, accountId);
			rs.setData(updatedAccount.getAccountUser());
			rs.setMsg("Account Updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);
		}
		return null; // no account found
	}

	public ResponseEntity<ResponseStructure<Account>> changeAccountType(int accountId, int accountType) {
		ResponseStructure<Account> rs = new ResponseStructure<>();

		Account exAccount = accountDao.findAccountById(accountId);
		if (exAccount != null) {
			if (accountType == 1 && exAccount.getAccountType() == AccountType.CURRENT) {
				exAccount.setAccountType(AccountType.SAVING);
				rs.setData(accountDao.updateAccount(exAccount, accountId));
				rs.setMsg("Account Type Changed");
				rs.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(rs, HttpStatus.CREATED);
			} else if (accountType == 2 && exAccount.getAccountType() == AccountType.SAVING) {
				exAccount.setAccountType(AccountType.CURRENT);
				rs.setData(accountDao.updateAccount(exAccount, accountId));
				rs.setMsg("Account Type Changed");
				rs.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(rs, HttpStatus.CREATED);
			} else if (accountType == 1 && exAccount.getAccountType() == AccountType.SAVING
					|| accountType == 2 && exAccount.getAccountType() == AccountType.CURRENT) {
				return null; // cannot change account to same type
			} else {
				return null; // limit exceeds 3
			}
		}
		return null; // no account found
	}

	public ResponseEntity<ResponseStructure<Account>> findAccountWithAccountNumber(long accountNumber) {
		ResponseStructure<Account> rs = new ResponseStructure<>();

		Account a = accountDao.findAccountByAccountNumber(accountNumber);
		if (a != null) {
			rs.setData(a);
			rs.setMsg("Account with Account No " + accountNumber + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Account>>(rs, HttpStatus.FOUND);
		}
		return null; //no account number
	}
}
