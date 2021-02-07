package com.dominicon.booking.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dominicon.booking.entity.Role;
import com.dominicon.booking.entity.SystemUser;
import com.dominicon.booking.repository.UserRepository;

@RestController
@CrossOrigin
class UserController {

	private final UserRepository repository;

	UserController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/user")
	SystemUser newUser(@RequestBody SystemUser newUser) {
		return repository.save(newUser);
	}
	
	boolean isAdminOrOfficer(SystemUser userRow){
		
		if (userRow.getRoles() == null) return false;
		
		for (Role role : userRow.getRoles()) {
			if (role.getRole().equals("admin") || role.getRole().equals("officer"))
				return true;
		}
		
		return false;
	}
	
	@PostMapping("/user/login")
	Map<String,String> login(@RequestBody Map<String, String> loginInputForm) {
		
		// find userRow by email and lowercase the email
		SystemUser userRow = repository.findByEmail(loginInputForm.get("email").toLowerCase());
		
		//if user email is not found
		if (userRow == null) return Map.of("success","false");
		
		if (loginInputForm.get("password").equals(userRow.grabPassword())) {
			
			//generate session ID and save it to user row
			String sessionID = UUID.randomUUID().toString();
			userRow.setSessionID(sessionID);
			repository.save(userRow);
			
			// check if user is admin or officer and redirect appropriately
			String redirectTo = isAdminOrOfficer(userRow) ? "admin":"public";
			
			return Map.of("success", "true", "sessionID", sessionID, "redirectTo", redirectTo);
		}
		
		return Map.of("success","false");
	}

	@PutMapping("/user/{email}")
	SystemUser updateUser(@RequestBody SystemUser newUser, @PathVariable String email) {
		SystemUser u = repository.findByEmail(email);
		u.setFirstName(newUser.getFirstName());
		u.setLastName(newUser.getLastName());
		u.putPassword(newUser.grabPassword());
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