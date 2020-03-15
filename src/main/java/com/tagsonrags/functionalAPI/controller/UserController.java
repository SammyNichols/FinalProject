package com.tagsonrags.functionalAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tagsonrags.functionalAPI.entity.User;
import com.tagsonrags.functionalAPI.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllUsers() {
			return new ResponseEntity<Object>(service.getUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createNewUser(@RequestBody User user) {
		return new ResponseEntity<Object>(service.createUser(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id){
		try {
			return new ResponseEntity<Object>(service.updateUser(user, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		try {
			service.deleteUser(id);
			return new ResponseEntity<Object>("Successfully deleted customer with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
