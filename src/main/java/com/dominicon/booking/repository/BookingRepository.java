package com.dominicon.booking.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicon.booking.entity.Booking;
import com.dominicon.booking.entity.SystemUser;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	Booking findByDateAndOwner(Date input, SystemUser owner);
}