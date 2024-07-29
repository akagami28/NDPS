package com.ndps.ots.security.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndps.ots.exception.AuthorizationException;
import com.ndps.ots.security.config.user.User;

@RestController
@RequestMapping("/api/security/jwt")
public class JwtController {

	@Autowired
	private JwtService jwtService;

//	@Autowired
//	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public final static String tracerId = String.valueOf(System.currentTimeMillis());

	@PostMapping("/token")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody User user) {
//		userService.saveUser(user, tracerId);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = jwtService.generateToken(user.getUsername());
			return new ResponseEntity<String>(token, HttpStatus.CREATED);
		} else
			throw new AuthorizationException("Authentication Failed!!!");
	}

}
