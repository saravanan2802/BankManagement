package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
