package com.dominicon.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicon.booking.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}