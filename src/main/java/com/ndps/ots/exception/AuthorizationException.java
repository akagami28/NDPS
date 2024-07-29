package com.ndps.ots.exception;

import org.springframework.security.access.AccessDeniedException;

public class AuthorizationException extends AccessDeniedException {

	private static final long serialVersionUID = 3210385828943175804L;

	public AuthorizationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
