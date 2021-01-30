package com.dominicon.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicon.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}