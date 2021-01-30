package com.dominicon.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicon.booking.entity.SystemUser;

public interface UserRepository extends JpaRepository<SystemUser, Long> {
	
	SystemUser findByEmail(String email);

}