package com.dashboard.api;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Repository.UserRepository;

@RestController
public class TestController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/hello")
	public String sayHello() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	  // This returns a JSON or XML with the users
	  return userRepository.findAll();
	}
}