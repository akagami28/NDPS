package com.ndps.ots.exception;

public class EmployeeNotFound extends RuntimeException {

	/**
	 * A Custom Exception class
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = -4273939031636387986L;

	public EmployeeNotFound(String message) {
		super(message);
	}

}
