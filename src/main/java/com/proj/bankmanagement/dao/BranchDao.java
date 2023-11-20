package com.proj.bankmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.bankmanagement.dto.Branch;
import com.proj.bankmanagement.repo.BranchRepo;

@Repository
public class BranchDao {
	@Autowired
	BranchRepo repo;
	
	public Branch saveBranch(Branch a) {
		return repo.save(a);
	}

	public Branch findBranchById(int id) {
		Optional<Branch> branch = repo.findById(id);

		if (branch.isPresent()) {
			return branch.get();
		} else {
			return null;
		}
	}

	public List<Branch> findAllBranch() {
		return repo.findAll();
	}

	public Branch deleteBranch(int id) {
		Branch exBranch = findBranchById(id);

		if (exBranch != null) {
			repo.delete(exBranch);
			return exBranch;
		} else {
			return null;
		}
	}
}
