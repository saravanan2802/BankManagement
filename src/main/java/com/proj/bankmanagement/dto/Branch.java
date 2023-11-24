 package com.proj.bankmanagement.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	private String branchIfsc;
	@OneToOne(cascade = CascadeType.ALL)
	private Address branchAddress;
	@ManyToOne(cascade = CascadeType.ALL)
	private Bank branchBank;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Manager branchManager;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<User> branchUsers;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchIfsc() {
		return branchIfsc;
	}

	public void setBranchIfsc(String branchIfsc) {
		this.branchIfsc = branchIfsc;
	}

	public Address getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(Address branchAddress) {
		this.branchAddress = branchAddress;
	}

	public Bank getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(Bank branchBank) {
		this.branchBank = branchBank;
	}

	public Manager getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(Manager branchManager) {
		this.branchManager = branchManager;
	}

	public List<User> getBranchUsers() {
		return branchUsers;
	}

	public void setBranchUsers(List<User> branchUsers) {
		this.branchUsers = branchUsers;
	}

}
