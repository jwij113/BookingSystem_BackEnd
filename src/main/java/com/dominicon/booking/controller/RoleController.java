package com.dominicon.booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominicon.booking.entity.Role;
import com.dominicon.booking.entity.SystemUser;
import com.dominicon.booking.repository.RoleRepository;

@RestController
@CrossOrigin
public class RoleController {
	
	private final RoleRepository repository;
	
	RoleController(RoleRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/role/officers")
	List<SystemUser> allOfficer(){
		return repository.findByRole("officer").fetchUsers();
	}
}
