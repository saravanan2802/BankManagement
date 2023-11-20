package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

}
