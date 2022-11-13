package com.dashboard.api.Controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/github")
@CrossOrigin(originPatterns = "http://localhost:*")
public class GithubController {
    @GetMapping("/token")
    public ResponseEntity<?> getGithubToken(@AuthenticationPrincipal OAuth2User user, @RequestParam("code") String githubCode) {
        try {
            user.getAttributes();
            return ResponseEntity.ok().body(githubCode);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getClass() + " : " + ex.getMessage());
        }
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getClass());
	}
}
