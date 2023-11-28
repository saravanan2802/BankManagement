package com.proj.bankmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.bankmanagement.config.ResponseStructure;
import com.proj.bankmanagement.dao.AddressDao;
import com.proj.bankmanagement.dao.BranchDao;
import com.proj.bankmanagement.dao.ManagerDao;
import com.proj.bankmanagement.dao.UserDao;
import com.proj.bankmanagement.dto.Address;
import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.dto.Manager;
import com.proj.bankmanagement.dto.User;
import com.proj.bankmanagement.exception.UserNotFound;

@Service
public class AddressService {

	@Autowired
	AddressDao addressDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BranchDao branchDao;
	@Autowired
	ManagerDao managerDao;

	public ResponseEntity<ResponseStructure<User>> createAddressForUser(Address a, int userId) {
		ResponseStructure<User> rs = new ResponseStructure<>();

		User u = userDao.findUserById(userId);
		if (u != null) {
			Address savedAddress = addressDao.saveAddress(a);
			u.setUserAddress(savedAddress);
			rs.setData(userDao.updateUser(userId, u));
			rs.setMsg("user assigned with address");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);
		}
		throw new UserNotFound("No User Found");
	}

	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(Address a, int branchId) {
		ResponseStructure<Branch> rs = new ResponseStructure<>();

		Branch b = branchDao.findBranchById(branchId);
		if (b != null) {
			Address savedAddress = addressDao.saveAddress(a);
			b.setBranchAddress(savedAddress);
			rs.setData(branchDao.updateBranch(branchId, b));
			rs.setMsg("Branch assigned with address");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs, HttpStatus.CREATED);
		}
		throw new UserNotFound("No Branch Found");
	}

	public ResponseEntity<ResponseStructure<Manager>> createAddressForManager(Address a, int managerId) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();

		Manager u = managerDao.findManagerById(managerId);
		if (u != null) {
			Address savedAddress = addressDao.saveAddress(a);
			u.setManagerAddress(savedAddress);
			rs.setData(managerDao.updateManager(managerId, u));
			rs.setMsg("Manager assigned with address");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs, HttpStatus.CREATED);
		}
		throw new UserNotFound("No Manager Found");
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address a, int addressId) {
		ResponseStructure<Address> rs = new ResponseStructure<>();

		Address exAddress = addressDao.findAddressById(addressId);
		
		if (exAddress!=null) {
			rs.setData(addressDao.updateAddress(addressId, a));
			rs.setMsg("Address Updated");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(rs,HttpStatus.CREATED);
		}
		throw new UserNotFound("No Address Found");
	}
	
	
}
