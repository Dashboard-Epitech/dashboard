package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Auth.UserDataRequest;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Exception.SpotifyTokenExpiredException;
import com.dashboard.api.Exception.UserNotFoundException;
import com.dashboard.api.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@CrossOrigin(originPatterns = "http://localhost:*")
public class UserController {
	private final UserService userService;

	@PostMapping("/get")
	public ResponseEntity<?> getUser(@RequestBody UserDataRequest request) {
		try {
			return ResponseEntity.ok().body(userService.getUserByEmail(request.getEmail()).toSafeData());
		} catch (UserNotFoundException userNotFoundException) {
			return ResponseEntity.internalServerError().body(request);
		} catch (Exception stdException) {
			return ResponseEntity.internalServerError().body(request);
		}
	}

	@PatchMapping("/edit/{id}")
	public ResponseEntity<?> editUser(@PathVariable("id") @NotNull Long id, @RequestBody @Valid DashboardUser user) {
		try {
			return ResponseEntity.ok().body(userService.editUser(id, user));
		} catch (UserNotFoundException userNotFoundException) {
			return ResponseEntity.notFound().build();
		} catch (Exception stdException) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") @NotNull Long id) {
		try {
			userService.removeUser(id);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException userNotFoundException) {
			return ResponseEntity.notFound().build();
		} catch (Exception stdException) {
			return ResponseEntity.internalServerError().body(stdException.getClass());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getUsers() {
		try {
			return ResponseEntity.ok().body(userService.getUsers());
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(ex.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}