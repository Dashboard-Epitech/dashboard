package com.dashboard.api.Controller;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dashboard.api.Entity.User;
import com.dashboard.api.Repository.UserRepository;
import com.dashboard.api.Service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/user")
public class UserController {
	private final UserService userService;

	@GetMapping("/all")
	public ResponseEntity<?> getUsers() {
		try {
			return ResponseEntity.ok().body(userService.getUsers());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Bad request");
		}
	}
}