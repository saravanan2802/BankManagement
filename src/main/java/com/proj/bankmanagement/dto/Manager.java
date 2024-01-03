package com.proj.bankmanagement.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Component
@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	@NotNull
	@NotBlank
	private String managerName;
	@NotNull
	@NotBlank
	private String managerPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Address managerAddress;
	@OneToOne(cascade = CascadeType.ALL)
	private Branch managerBranch;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public Address getManagerAddress() {
		return managerAddress;
	}
	public void setManagerAddress(Address managerAddress) {
		this.managerAddress = managerAddress;
	}
	public Branch getManagerBranch() {
		return managerBranch;
	}
	public void setManagerBranch(Branch managerBranch) {
		this.managerBranch = managerBranch;
	}
	
	
}
