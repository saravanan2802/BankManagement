package com.proj.bankmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proj.bankmanagement.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
	
	@Query("select m from Manager m where m.managerName=?1")
	public Manager findManagerByName(String managerName); 
}
