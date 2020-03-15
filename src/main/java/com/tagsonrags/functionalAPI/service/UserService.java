package com.tagsonrags.functionalAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tagsonrags.functionalAPI.entity.User;
import com.tagsonrags.functionalAPI.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Iterable<User> getUsers() {
		return repo.findAll();
	}
	
	public User createUser(User user) {
		return repo.save(user);
	}

	public User updateUser(User user, Long id) throws Exception {
		try {
			User oldUser = repo.findOne(id);
			oldUser.setAddress(user.getAddress());
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setListing(user.getListing());
			return repo.save(oldUser);
		} catch (Exception e) {
			throw new Exception("Exception occured while trying to update user id: " + id);
			
		}
	}
	
	public void deleteUser(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			throw new Exception("Exception occured while trying to delete user id: " + id);
		}
	}
}
