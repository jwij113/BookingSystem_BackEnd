package com.dominicon.booking.controller;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dominicon.booking.entity.SystemUser;
import com.dominicon.booking.repository.UserRepository;

@SpringBootTest
class UsertControllerTest {
	
	@Autowired
	private UserController controller;
	
	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testLoginUserFoundPasswordFalse() {
		Map<String, String> loginToken = Map.of("email","jokowi@gmail.com", "password", "joko");
		Map<String, String> response = controller.login(loginToken);
		assertThat(response.get("success").equals("false")).isEqualTo(true);
	}
	

}
