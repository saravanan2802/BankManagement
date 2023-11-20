package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	UserRepo repo;
	
	public User saveUser(User a) {
		return repo.save(a);
	}

	public User findUserById(int id) {
		Optional<User> user = repo.findById(id);

		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	public List<User> findAllUser() {
		return repo.findAll();
	}

	public User deleteUser(int id) {
		User exUser = findUserById(id);

		if (exUser != null) {
			repo.delete(exUser);
			return exUser;
		} else {
			return null;
		}
	}
}
