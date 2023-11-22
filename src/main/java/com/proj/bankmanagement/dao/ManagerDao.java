package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.repo.ManagerRepo;

@Repository
public class ManagerDao {

	@Autowired
	ManagerRepo repo;

	public Manager saveManager(Manager a) {
		return repo.save(a);
	}

	public Manager findManagerById(int id) {
		Optional<Manager> manager = repo.findById(id);

		if (manager.isPresent()) {
			return manager.get();
		} else {
			return null;
		}
	}

	public List<Manager> findAllManager() {
		return repo.findAll();
	}

	public Manager deleteManager(int id) {
		Manager exManager = findManagerById(id);

		if (exManager != null) {
			repo.delete(exManager);
			return exManager;
		} else {
			return null;
		}
	}

	public Manager updateManager(int id, Manager manager) {
		Manager exManager = findManagerById(id);

		if (exManager != null) {
			manager.setManagerId(id);
			return repo.save(manager);
		} else {
			return null;
		}
	}
}
