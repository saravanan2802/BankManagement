package com.proj.bankmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.BranchDao;
import com.proj.bankmanagement.dao.ManagerDao;
import com.proj.bankmanagement.dao.UserDao;
import com.proj.bankmanagement.dto.Account;
import com.proj.bankmanagement.dto.AccountType;
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.dto.User;

@Service
public class UserService {
	@Autowired
	ManagerDao managerDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BranchDao branchDao;

	public ResponseEntity<ResponseStructure<User>> saveUserWithAcountBranch(User u, int accountType, String managerName,
			String managerPassword) {

		ResponseStructure<User> rs = new ResponseStructure<>();

		Manager exManager = managerDao.managerLogin(managerName, managerPassword);

		Branch branch = exManager.getManagerBranch();

		if (exManager != null) {
			Account account = new Account();
			if (accountType == 1) {
				account.setAccountType(AccountType.SAVING);
			} else if (accountType == 2) {
				account.setAccountType(AccountType.CURRENT);
			} else {
				return null; // bad request only 1 or 2
			}
			u.setUserAccount(account);
			account.setAccountUser(u);
			u.setUserBranch(branch);

			User savedUser = userDao.saveUser(u);

			branch.getBranchUsers().add(savedUser);
			branchDao.updateBranch(branch.getBranchId(), branch);

			rs.setData(savedUser);
			rs.setMsg("User has Been Saved");
			rs.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);

		} else {
			return null; // no manager found
		}

	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		if (userDao.findUserById(id) != null) {
			rs.setData(userDao.findUserById(id));
			rs.setMsg("User with Id " + id + " found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.FOUND);
		}
		return null; // no user found
	}

	public ResponseEntity<ResponseStructure<User>> deleteUserById(int id) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		User u = userDao.findUserById(id);
		Branch b = u.getUserBranch();

		if (u != null) {
			u.setUserBranch(null);
			b.getBranchUsers().remove(u);
			branchDao.updateBranch(b.getBranchId(), b);
			rs.setData(userDao.deleteUser(id));
			rs.setMsg("User with Id " + id + " deleted");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);

		}
		return null; // no user found

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User u, int id) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		User exUser = userDao.findUserById(id);

		if (exUser != null) {
			rs.setData(userDao.updateUser(id, u));
			rs.setMsg("User with Id " + id + "updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		}
		return null; // no user found
	}
	
	public ResponseEntity<ResponseStructure<User>> findUserByName(String userName,String userPassword){
		ResponseStructure<User> rs = new ResponseStructure<>();
		
		User exUser = userDao.findUserByName(userName);
		
		if (exUser!=null) {
			if (exUser.getUserAccount().getAccountPassword().equals(userPassword)) {
				rs.setData(exUser);
				rs.setMsg("User Found");
				rs.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.FOUND);
				
			}
			return null; //password not match
		}
		return null; //no user found
	}

}
