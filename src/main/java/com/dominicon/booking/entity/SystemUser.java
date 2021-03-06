package com.dominicon.booking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "system_user_generator")
	@SequenceGenerator(name="system_user_generator", sequenceName = "system_user_seq")
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	
	@Column(unique=true)
	private String email;
	
	private String sessionID;
	
	@ManyToMany(targetEntity=Role.class, fetch = FetchType.EAGER)
	@JoinTable(
			  name = "user_role", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "booker", targetEntity = Booking.class)
	private List<Booking> bookings;
	
	public SystemUser() {
	}
 
	public SystemUser(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String grabPassword() {
		return password;
	}

	public void putPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
