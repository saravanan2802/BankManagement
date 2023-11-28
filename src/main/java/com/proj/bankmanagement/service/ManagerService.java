package com.proj.bankmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.BranchDao;
import com.proj.bankmanagement.dao.ManagerDao;
import com.proj.bankmanagement.dao.UserDao;
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.exception.BranchNotFound;
import com.proj.bankmanagement.exception.ManagerNotFound;
import com.proj.bankmanagement.exception.PasswordIncorrect;
import com.proj.bankmanagement.exception.UserInSameBranch;
import com.proj.bankmanagement.exception.UserNotFound;
import com.proj.bankmanagement.repo.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	ManagerDao managerDao;
	@Autowired
	BranchDao branchDao;
	@Autowired
	ManagerRepo managerRepo;
	@Autowired
	UserDao userDao;

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
		throw new BranchNotFound("No Branch Found");
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
		throw new ManagerNotFound("No Manager Found");
	}

	public ResponseEntity<ResponseStructure<Manager>> findManagerById(int id) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();

		if (managerDao.findManagerById(id) != null) {
			rs.setData(managerDao.findManagerById(id));
			rs.setMsg("Manager with Id " + id + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.FOUND);
		}
		throw new ManagerNotFound("No Manager Found");
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
		throw new ManagerNotFound("No Manager Found");
	}

	public ResponseEntity<ResponseStructure<Manager>> managerLogin(String managerName, String managerPassword) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();

		if (managerRepo.findManagerByName(managerName) != null) {
			if (managerRepo.findManagerByName(managerName).getManagerPassword().equals(managerPassword)) {
				rs.setData(managerRepo.findManagerByName(managerName));
				rs.setMsg("Manager Found");
				rs.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.FOUND);
			}
			throw new PasswordIncorrect("Password Mismatch");
		}
		throw new ManagerNotFound("No Manager Found");
	}

	public ResponseEntity<ResponseStructure<User>> changeAccountBranch(String managerName, String managerPassword,
			int userId, int newBranchId) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		Manager m = managerDao.managerLogin(managerName, managerPassword);
		User exUser = userDao.findUserById(userId);
		Branch newBranch = branchDao.findBranchById(newBranchId);

		if (m != null) {
			if (exUser != null) {
				if (newBranch != null) {
					if (exUser.getUserBranch().getBranchId() != newBranchId) {
						Branch exBranch = exUser.getUserBranch();
						exBranch.getBranchUsers().remove(exUser);
						branchDao.updateBranch(exBranch.getBranchId(), exBranch);
						exUser.setUserBranch(newBranch);
						newBranch.getBranchUsers().add(exUser);
						branchDao.updateBranch(newBranchId, newBranch);
						rs.setData(exUser);
						rs.setMsg("Account moved to new Branch");
						rs.setStatus(HttpStatus.CREATED.value());
						return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
					}
					throw new UserInSameBranch("Cannot Change Account Branch To Same branch");
				}
				throw new BranchNotFound("No branch Found");
			}
			throw new UserNotFound("No User Found");
		}
		throw new ManagerNotFound("No Manager Found");
	}
}
