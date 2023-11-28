package com.proj.bankmanagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.AccountDao;
import com.proj.bankmanagement.dao.TransactionDao;
import com.proj.bankmanagement.dto.Account;
import com.proj.bankmanagement.dto.Transaction;
import com.proj.bankmanagement.dto.TransactionStatus;
import com.proj.bankmanagement.dto.TransactionType;

@Service
public class TransactionService {
	@Autowired
	TransactionDao transactionDao;
	@Autowired
	AccountDao accountDao;

	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(long fromAccountNumber, String accountPassword,
			double amount, long toAccountNumber) {

		ResponseStructure<Transaction> rs = new ResponseStructure<>();

		Account fromAccount = accountDao.findAccountByAccountNumber(fromAccountNumber);
		Account toAccount = accountDao.findAccountByAccountNumber(toAccountNumber);

		if (fromAccountNumber != toAccountNumber) {
			if (amount > 0) {
				if (fromAccount != null) {
					if (toAccount != null) {
						if (fromAccount.getAccountPassword().equals(accountPassword)) {
							if (fromAccount.getAccountBalance() >= amount) {
								if (fromAccount.getAccountBalance() - 100 >= amount) {
									Transaction t1 = new Transaction();
									t1.setTransactionAmount(amount);
									t1.setTransactionDateAndtime(LocalDate.now());
									t1.setTransactionToAccount(toAccountNumber);
									t1.setTransStatus(TransactionStatus.SUCCESS);
									t1.setTransType(TransactionType.DEBITED);
									Transaction savedTransaction1 = transactionDao.saveTransaction(t1);
									fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
									fromAccount.getAccountTransactions().add(savedTransaction1);
									accountDao.updateAccount(fromAccount, fromAccount.getAccountId());
									Transaction t2 = new Transaction();
									t2.setTransactionAmount(amount);
									t2.setTransactionDateAndtime(LocalDate.now());
									t2.setTransactionToAccount(fromAccountNumber);
									t2.setTransStatus(TransactionStatus.SUCCESS);
									t2.setTransType(TransactionType.CREDITED);
									Transaction savedTransaction2 = transactionDao.saveTransaction(t2);
									toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);
									toAccount.getAccountTransactions().add(savedTransaction2);
									accountDao.updateAccount(toAccount, toAccount.getAccountId());
									rs.setData(savedTransaction1);
									rs.setMsg("Amount Transfer successful");
									rs.setStatus(HttpStatus.CREATED.value());
									return new ResponseEntity<ResponseStructure<Transaction>>(rs, HttpStatus.CREATED);

								}
								return null; // minimum balance limit exceeds
							}
							Transaction t = new Transaction();
							t.setTransactionAmount(amount);
							t.setTransactionDateAndtime(LocalDate.now());
							t.setTransactionToAccount(toAccountNumber);
							t.setTransStatus(TransactionStatus.PENDING);
							Transaction savedunsuccesstransaction = transactionDao.saveTransaction(t);
							fromAccount.getAccountTransactions().add(savedunsuccesstransaction);
							accountDao.updateAccount(fromAccount, fromAccount.getAccountId());
							rs.setData(t);
							rs.setMsg("Transction pending");
							rs.setStatus(HttpStatus.ALREADY_REPORTED.value());
							return new ResponseEntity<ResponseStructure<Transaction>>(rs,HttpStatus.ALREADY_REPORTED);
						}
						return null; // password mismatch
					}
					return null; // no to Account
				}
				return null; // no from Account Found
			}
			return null; // transaction amount should be greater than zero
		}
		return null; // cannot send money to same account

	}

}
