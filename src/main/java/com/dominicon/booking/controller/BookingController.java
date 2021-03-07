package com.dominicon.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dominicon.booking.entity.Booking;
import com.dominicon.booking.entity.SystemUser;
import com.dominicon.booking.repository.BookingRepository;
import com.dominicon.booking.repository.UserRepository;

@RestController
@CrossOrigin
public class BookingController {
	
private final BookingRepository repository;
private final UserRepository 	userRepository;
	
	BookingController(BookingRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	
	@PostMapping("/booking/submitAvailableTime")
	Boolean test(@RequestBody Map<String, String> bookingInputForm){
		String officer = bookingInputForm.get("officer");
		String sessionID = bookingInputForm.get("sessionID");
		String date = bookingInputForm.get("date");
		String time = bookingInputForm.get("time");
		Optional<SystemUser> owner = null;
		
		Booking newBookingTime = new Booking();
		
		if (officer.length() == 0)
			owner = Optional.ofNullable(userRepository.findBySessionID(sessionID));
		else
			owner = userRepository.findById(Long.parseLong(officer));
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH.mm", Locale.ENGLISH);
		String dateInString = date + " " +  time;
		Date bookingDate = null;
		
		try {
			bookingDate = formatter.parse(dateInString);
			System.out.println(bookingDate);
		} catch (ParseException e) {
			return false;
		}
		
		newBookingTime.setDate(bookingDate);
		newBookingTime.setOwner(owner.get());
		
		try {
			repository.save(newBookingTime);
		}catch(DataIntegrityViolationException e) {
			return false;
		}
		
		return true;
	}

}
