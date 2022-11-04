package com.dashboard.api.Controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Repository.UserRepository;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	// @GetMapping("/user/register")
	// public @ResponseBody registerUser() {

	// }

	@GetMapping(path="/user/login")
	public @ResponseBody Iterable<User> getAllUsers() {
	  // This returns a JSON or XML with the users
	  return userRepository.findAll();
	}
}