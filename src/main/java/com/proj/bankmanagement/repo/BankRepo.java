package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer> {

}
