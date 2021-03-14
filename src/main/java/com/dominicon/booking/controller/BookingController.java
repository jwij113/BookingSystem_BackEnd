package com.dominicon.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
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
	Boolean submitAvailableTime(@RequestBody Map<String, String> bookingInputForm){
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
	
	@PostMapping("/booking/changeCalendarDate")
	Map<String,Object> changeCalendarDate(@RequestBody Map<String, String> request){
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.get("date"));
			
			System.out.println(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int minusDay= dayOfWeek - calendar.getFirstDayOfWeek() - 1;
			
			calendar.add(Calendar.DAY_OF_MONTH, -1 * minusDay);
	        Date monday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date tuesday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date wednesday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date thursday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date friday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date saturday = calendar.getTime();
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        Date sunday = calendar.getTime();
	        System.out.println(monday + " "+tuesday + " "+ wednesday +  " " + thursday );
	        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	        String[] dateArray = {format1.format(monday), 
	        					  format1.format(tuesday),
	        					  format1.format(wednesday),
	        					  format1.format(thursday),
	        					  format1.format(friday),
	        					  format1.format(saturday),
	        					  format1.format(sunday)};
	        
	        Optional<SystemUser> owner = null;
	        
	        String officer = request.get("officer");
			String sessionID = request.get("sessionID");
			
	        if (officer.length() == 0)
				owner = Optional.ofNullable(userRepository.findBySessionID(sessionID));
			else
				owner = userRepository.findById(Long.parseLong(officer));
	     
					
	        return Map.of("date", dateArray, 
	        			  "Mon", getDayMap(monday, owner.get()),
	        			  "Tue", getDayMap(tuesday, owner.get()),
	        			  "Wed", getDayMap(wednesday, owner.get()),
	        			  "Thurs", getDayMap(thursday, owner.get()),
	        			  "Fri", getDayMap(friday, owner.get()),
	        			  "Sat", getDayMap(saturday, owner.get()),
	        			  "Sun", getDayMap(sunday, owner.get()));
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Map.of("success","false");
	}




	private Map<String, Object> getDayMap(Date day, SystemUser user) throws ParseException{
		
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH.mm", Locale.ENGLISH);
		
		return  Map.of("9.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "9.00"), user)),
				"10.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "10.00"), user)),
				"11.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "11.00"), user)),
				"12.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "12.00"), user)),
				"13.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "13.00"), user)),
				"14.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "14.00"), user)),
				"15.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "15.00"), user)),
				"16.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "16.00"), user)),
				"17.00", isAvailable(repository.findByDateAndOwner(formatter.parse(format1.format(day) +" "+ "17.00"), user)));
		
	}
	
	private String isAvailable(Booking booking) {
		if (booking == null)
			return "";
		else {
			return booking.isAvailable();
		}
	}
}