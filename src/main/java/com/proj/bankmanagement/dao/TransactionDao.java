package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Address;
import com.proj.bankmanagement.dto.Transaction;
import com.proj.bankmanagement.repo.TransactionRepo;

@Repository
public class TransactionDao {
	@Autowired
	TransactionRepo repo;
	
	public Transaction saveTransaction(Transaction a) {
		return repo.save(a);
	}

	public Transaction findTransactionById(int id) {
		Optional<Transaction> transaction = repo.findById(id);

		if (transaction.isPresent()) {
			return transaction.get();
		} else {
			return null;
		}
	}

	public List<Transaction> findAllTransaction() {
		return repo.findAll();
	}

	public Transaction deleteTransaction(int id) {
		Transaction exTransaction = findTransactionById(id);

		if (exTransaction != null) {
			repo.delete(exTransaction);
			return exTransaction;
		} else {
			return null;
		}
	}
	
	public Transaction updateTransaction(int id,Transaction transaction ) {
		Transaction exTransaction = findTransactionById(id);
		
		if (exTransaction!=null) {
			transaction.setTransactionId(id);
			return repo.save(transaction);
		}else {
			return null;
		}
	}
}
