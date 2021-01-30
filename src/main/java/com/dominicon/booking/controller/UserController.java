package com.dominicon.booking.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dominicon.booking.entity.SystemUser;
import com.dominicon.booking.repository.UserRepository;

@RestController
class UserController {

	private final UserRepository repository;

	UserController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/user")
	SystemUser newUser(@RequestBody SystemUser newUser) {
		return repository.save(newUser);
	}

	@PutMapping("/user/{email}")
	SystemUser updateUser(@RequestBody SystemUser newUser, @PathVariable String email) {
		SystemUser u = repository.findByEmail(email);
		u.setFirstName(newUser.getFirstName());
		u.setLastName(newUser.getLastName());
		u.setPassword(newUser.getPassword());
		return repository.save(u);

	}
	
	@GetMapping("/user/{email}")
	SystemUser one(@PathVariable String email) {
		return repository.findByEmail(email);
	}

	@DeleteMapping("/user/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}

}