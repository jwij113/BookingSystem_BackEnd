package com.dominicon.booking.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String role;
	
	@ManyToMany(targetEntity=SystemUser.class, mappedBy="roles", fetch = FetchType.LAZY)
	private List<SystemUser> users;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
