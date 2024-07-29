package com.ndps.ots.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * All the exceptions are handled in this class also called as global exception
 * handler class.
 * 
 * @author AkhilK
 *
 */
@RestControllerAdvice
public class Handler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerMethod(EmployeeNotFound employeeNotFound) {
		ErrorResponse response = ErrorResponse.builder().timestamp(System.currentTimeMillis())
				.messageString(employeeNotFound.getMessage()).status(HttpStatus.NOT_FOUND.value()).build();

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerMethod(RuntimeException re) {
		ErrorResponse response = ErrorResponse.builder().messageString(re.getMessage())
				.status(HttpStatus.BAD_REQUEST.value()).timestamp(System.currentTimeMillis()).build();

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerMethod(DepartmentNotFound departmentNotFound) {
		ErrorResponse response = ErrorResponse.builder().status(HttpStatus.NOT_FOUND.value())
				.messageString(departmentNotFound.getMessage()).timestamp(System.currentTimeMillis()).build();

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerMethod(UsernameNotFoundException usernameNotFoundException) {
		ErrorResponse response = ErrorResponse.builder().messageString(usernameNotFoundException.getMessage())
				.status(HttpStatus.NOT_FOUND.value()).timestamp(System.currentTimeMillis()).build();

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerMethod(AuthorizationException authorizationException) {
		ErrorResponse response = ErrorResponse.builder().messageString(authorizationException.getMessage())
				.status(HttpStatus.UNAUTHORIZED.value()).timestamp(System.currentTimeMillis()).build();

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.UNAUTHORIZED);
	}
}
