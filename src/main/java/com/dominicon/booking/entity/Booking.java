package com.dominicon.booking.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"date", "owner_id"})})
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "booking_generator")
	@SequenceGenerator(name="booking_generator", sequenceName = "booking_seq")
	private Long id;
	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity= SystemUser.class)
	private SystemUser owner;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity= SystemUser.class)
	private SystemUser booker;
	
	private String bookingMessage;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SystemUser getOwner() {
		return owner;
	}
	public void setOwner(SystemUser owner) {
		this.owner = owner;
	}
	public SystemUser getBooker() {
		return booker;
	}
	public void setBooker(SystemUser booker) {
		this.booker = booker;
	}
	public String getBookingMessage() {
		return bookingMessage;
	}
	public void setBookingMessage(String bookingMessage) {
		this.bookingMessage = bookingMessage;
	}
	
	public String isAvailable() {
		if (this.owner != null && this.booker == null) {
			return "Available";
		}
		
		return "";
	}

}
