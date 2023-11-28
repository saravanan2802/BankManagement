package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proj.bankmanagement.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userName=?1")
	public User findUserByName(String userName);
}
