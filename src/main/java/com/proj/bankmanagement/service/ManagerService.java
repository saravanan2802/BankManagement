package com.proj.bankmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.BranchDao;
import com.proj.bankmanagement.dao.ManagerDao;
import com.proj.bankmanagement.dto.Bank;
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.dto.Manager;

@Service
public class ManagerService {

	@Autowired
	ManagerDao managerDao;
	@Autowired
	BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager, int branchId) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		if (branchDao.findBranchById(branchId) != null) {
			Branch exBranch = branchDao.findBranchById(branchId);
			Manager savedManager = managerDao.saveManager(manager);
			savedManager.setManagerBranch(exBranch);
			exBranch.setBranchManager(savedManager);
			rs.setData(managerDao.saveManager(savedManager));
			rs.setMsg("Manager has been saved");
			rs.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.CREATED);
		}
		return null; // no branch found
	}

	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int managerId) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();

		if (managerDao.findManagerById(managerId) != null) {
			Manager exManager = managerDao.findManagerById(managerId);
			Branch exBranch = exManager.getManagerBranch();
			exManager.setManagerBranch(null);
			exBranch.setBranchManager(null);
			managerDao.updateManager(managerId, exManager);
			rs.setData(managerDao.deleteManager(managerId));
			rs.setMsg("Manager with Id" + managerId + " has deleted");
			rs.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.CREATED);
		}
		return null; // no manager found
	}

	public ResponseEntity<ResponseStructure<Manager>> findManagerById(int id) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		
		if(managerDao.findManagerById(id)!=null) {
			rs.setData(managerDao.findManagerById(id));
			rs.setMsg("Manager with Id " + id + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.FOUND);
		}
		return null; //no manager found
	}
	
	public ResponseEntity<ResponseStructure<List<Manager>>> findAllManager() {
		ResponseStructure<List<Manager>> rs = new ResponseStructure<>();
		rs.setData(managerDao.findAllManager());
		rs.setMsg("All Managers were Found");
		rs.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Manager>>>(rs, HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Manager>> updateManager(int id, Manager b) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();

		if (managerDao.findManagerById(id) != null) {
			rs.setData(managerDao.updateManager(id, b));
			rs.setMsg("Manager with Id" + id + " has been Updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.CREATED);
		}
		return null; // No Manager Found
	}
}
