package com.dominicon.booking.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Role {
	
	@Id
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
