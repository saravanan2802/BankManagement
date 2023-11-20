package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
