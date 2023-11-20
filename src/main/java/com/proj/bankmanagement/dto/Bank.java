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
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	private String bankName;
	private long bankContact;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Branch> bankBranches;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getBankContact() {
		return bankContact;
	}

	public void setBankContact(long bankContact) {
		this.bankContact = bankContact;
	}

	public List<Branch> getBankBranches() {
		return bankBranches;
	}

	public void setBankBranches(List<Branch> bankBranches) {
		this.bankBranches = bankBranches;
	}

}
