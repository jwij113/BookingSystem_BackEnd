package com.dominicon.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicon.booking.entity.Role;
import com.dominicon.booking.entity.SystemUser;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRole(String role);

}