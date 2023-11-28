package com.proj.bankmanagement.dto;


import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	private double transactionAmount;
	private long transactionToAccount;
	private LocalDate transactionDateAndtime;
	public LocalDate getTransactionDateAndtime() {
		return transactionDateAndtime;
	}

	public void setTransactionDateAndtime(LocalDate transactionDateAndtime) {
		this.transactionDateAndtime = transactionDateAndtime;
	}

	private TransactionType transType;
	private TransactionStatus transStatus;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public long getTransactionToAccount() {
		return transactionToAccount;
	}

	public void setTransactionToAccount(long transactionToAccount) {
		this.transactionToAccount = transactionToAccount;
	}

	

	public TransactionType getTransType() {
		return transType;
	}

	public void setTransType(TransactionType transType) {
		this.transType = transType;
	}

	public TransactionStatus getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(TransactionStatus transStatus) {
		this.transStatus = transStatus;
	}

}
