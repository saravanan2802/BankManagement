package com.proj.bankmanagement.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	private long accountNumber;
	private String accountPassword;
	private double accountBalance;
	private AccountType accountType;
	@OneToOne(cascade = CascadeType.ALL)
	private User accountUser;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> accountTransactions;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public User getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(User accountUser) {
		this.accountUser = accountUser;
	}
	public List<Transaction> getAccountTransactions() {
		return accountTransactions;
	}
	public void setAccountTransactions(List<Transaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	

}
