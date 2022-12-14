package com.dashboard.api.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.dashboard.api.Request.AuthRequest;
import com.dashboard.api.Request.AuthResponse;
import com.dashboard.api.Security.token.JWTProvider;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Error.BadCredentialsResponseError;
import com.dashboard.api.Error.BadUserFormResponseError;
import com.dashboard.api.Error.MailResponseError;
import com.dashboard.api.Error.UserNotVerifiedError;
import com.dashboard.api.Error.UsernameAlreadyExistsResponseError;
import com.dashboard.api.Model.Request.LocalAuthLoginRequest;
import com.dashboard.api.Model.Request.LocalAuthRegisterRequest;
import com.dashboard.api.Model.Response.LocalAuthLoginResponse;
import com.dashboard.api.Service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@CrossOrigin(originPatterns = "http://localhost:*")
public class AuthController {
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JWTProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LocalAuthLoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), request.getPassword()
					)
				);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.createToken(authentication);

		return ResponseEntity.ok().body(new LocalAuthLoginResponse(request.getUsername(), token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid LocalAuthRegisterRequest request) {
		try {
			return ResponseEntity.created(null).body(userService.registerUser(request));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(new UsernameAlreadyExistsResponseError().build());
		}
	}

	@GetMapping("/verify/{id}/{verificationCode}")
	public ResponseEntity<?> verifyUser(@PathVariable("id") Long id,
			@PathVariable("verificationCode") String verificationCode) {
		try {
			userService.verifyUser(id, verificationCode);
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
					.location(URI.create("http://localhost:3000/auth/login/verified")).build();
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body(ex.getMessage());
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> validationErrors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});

		BadUserFormResponseError badUserFormResponseError = new BadUserFormResponseError(validationErrors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badUserFormResponseError.build());
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<?> handleDisabledExceptions(DisabledException ex) {
		UserNotVerifiedError userNotVerifiedError = new UserNotVerifiedError();

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userNotVerifiedError.build());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentialsExceptions(BadCredentialsException ex) {
		BadCredentialsResponseError badCredentialsError = new BadCredentialsResponseError();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badCredentialsError.build());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		UsernameAlreadyExistsResponseError usernameAlreadyExistsError = new UsernameAlreadyExistsResponseError();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usernameAlreadyExistsError.build());
	}

	@ExceptionHandler(MailSendException.class)
	public ResponseEntity<?> handleMailSendException(MailSendException ex) {
		MailResponseError mailResponseError = new MailResponseError(ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mailResponseError.build());
	}
}