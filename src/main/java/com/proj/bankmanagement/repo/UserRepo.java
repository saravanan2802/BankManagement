package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.bankmanagement.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
