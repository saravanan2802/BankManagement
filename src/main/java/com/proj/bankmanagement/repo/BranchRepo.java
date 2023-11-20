package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer> {

}
