package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Address;
import com.proj.bankmanagement.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	AddressRepo repo;
	
	public Address saveAddress(Address a) {
		return repo.save(a);
	}

	public Address findAddressById(int id) {
		Optional<Address> address = repo.findById(id);

		if (address.isPresent()) {
			return address.get();
		} else {
			return null;
		}
	}

	public List<Address> findAllAddress() {
		return repo.findAll();
	}

	public Address deleteAddress(int id) {
		Address exAddress = findAddressById(id);

		if (exAddress != null) {
			repo.delete(exAddress);
			return exAddress;
		} else {
			return null;
		}
	}
	
	public Address updateAddress(int id,Address address ) {
		Address exAddress = findAddressById(id);
		
		if (exAddress!=null) {
			address.setAddressId(id);
			return repo.save(address);
		}else {
			return null;
		}
	}
}
