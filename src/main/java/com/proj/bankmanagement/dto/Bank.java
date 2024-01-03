package com.proj.bankmanagement.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@NotNull
	@NotBlank
	private String bankName;
	@Min(value = 6000000000l, message = "Invalid Contact Number")
	@Max(value = 9999999999l, message = "Invalid Contact Number")
	private long bankContact;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
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
