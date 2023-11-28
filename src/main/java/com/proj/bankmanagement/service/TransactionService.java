package com.proj.bankmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.AccountDao;
import com.proj.bankmanagement.dao.TransactionDao;
import com.proj.bankmanagement.dao.UserDao;
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
	@Autowired
	UserDao userDao;

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
							rs.setMsg("Transction pending. Wait for 24hrs...Status will be displayed Shortly");
							rs.setStatus(HttpStatus.ALREADY_REPORTED.value());
							return new ResponseEntity<ResponseStructure<Transaction>>(rs, HttpStatus.ALREADY_REPORTED);
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

	public ResponseEntity<ResponseStructure<List<Transaction>>> updatePendingTransaction() {
		ResponseStructure<List<Transaction>> rs = new ResponseStructure<>();
		LocalDate currentDate = LocalDate.now();
		List<Transaction> transactions = transactionDao.findAllTransaction();

		if (transactions != null) {
			for (Transaction trans : transactions) {
				if (trans.getTransStatus().equals(TransactionStatus.PENDING)) {
					if (currentDate.equals(trans.getTransactionDateAndtime().plusDays(1))) {
						trans.setTransStatus(TransactionStatus.FAILED);
						trans.setTransType(TransactionType.NOTDEBITED);
						transactionDao.updateTransaction(trans.getTransactionId(), trans);

					}
				}
			}
			List<Transaction> updatedtransactions = transactionDao.findAllTransaction();
			rs.setData(updatedtransactions);
			rs.setMsg("Transaction Updated");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<Transaction>>>(rs, HttpStatus.ACCEPTED);
		}
		return null; // no pending transaction
	}

	public ResponseEntity<ResponseStructure<List<Transaction>>> getFilteredTransactions(String userName,
			String accountPassword, int month) {

		ResponseStructure<List<Transaction>> rs = new ResponseStructure<>();
		List<Transaction> filteredList = new ArrayList<>();

		if (userDao.findUserByName(userName) != null) {
			if (userDao.findUserByName(userName).getUserAccount().getAccountPassword().equals(accountPassword)) {
				List<Transaction> userTransactions = userDao.findUserByName(userName).getUserAccount()
						.getAccountTransactions();
				LocalDate currentDate = LocalDate.now();
				LocalDate preDate = LocalDate.now().minusMonths(month);
				for (Transaction transaction : userTransactions) {
					if (transaction.getTransactionDateAndtime().isAfter(preDate)
							&& transaction.getTransactionDateAndtime().isBefore(currentDate)) {
						filteredList.add(transaction);
					}
				}
				rs.setData(filteredList);
				rs.setMsg("Transactions Found within Given Months");
				rs.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<List<Transaction>>>(rs,HttpStatus.FOUND);
			}
			return null; // password mismatch
		}
		return null; // no user found

	}

}
