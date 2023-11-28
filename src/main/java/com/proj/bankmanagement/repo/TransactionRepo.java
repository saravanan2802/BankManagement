package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	
}
