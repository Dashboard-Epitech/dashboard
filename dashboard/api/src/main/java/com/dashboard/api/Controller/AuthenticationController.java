package com.dashboard.api.Controller;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.dashboard.api.Auth.AuthRequest;
import com.dashboard.api.Auth.AuthResponse;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Error.BadCredentialsError;
import com.dashboard.api.Error.UsernameAlreadyExistsError;
import com.dashboard.api.JWT.JwtTokenUtil;
import com.dashboard.api.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticationController {
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid DashboardUser user) {
		try {
			return ResponseEntity.created(null).body(userService.registerUser(user));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(new UsernameAlreadyExistsError().build());
		}
	}

	@GetMapping("/verify/{id}/{verificationCode}")
	public ResponseEntity<?> verifyUser(@PathVariable("id") Long id,
			@PathVariable("verificationCode") String verificationCode) {
		try {
			userService.verifyUser(id, verificationCode);
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create("http://localhost:3000")).build();
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(ex.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), request.getPassword()));

		DashboardUser user = (DashboardUser) authentication.getPrincipal();
		String accessToken = jwtTokenUtil.generateAccessToken(user);
		AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

		return ResponseEntity.ok().body(response);

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

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<?> handleDisabledExceptions(DisabledException ex) {
		Map<String, String> error = new HashMap<>();
		String errorTypeKey = "errorType";
		String errorName = "userNotVerified";
		String errorValueKey = "errorContent";
		String errorValue = ex.getMessage();
		error.put(errorTypeKey, errorName);
		error.put(errorValueKey, errorValue);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentialsExceptions(BadCredentialsException ex) {
		BadCredentialsError badCredentialsError = new BadCredentialsError(ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badCredentialsError.build());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		UsernameAlreadyExistsError usernameAlreadyExistsError = new UsernameAlreadyExistsError();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usernameAlreadyExistsError.build());
	}

	@ExceptionHandler(MailSendException.class)
	public ResponseEntity<?> handleMailSendException(MailSendException ex) {
		Map<String, String> error = new HashMap<>();
		String errorTypeKey = "errorType";
		String errorName = "mailError";
		String errorValueKey = "errorContent";
		String errorValue = ex.getMessage();
		error.put(errorTypeKey, errorName);
		error.put(errorValueKey, errorValue);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}