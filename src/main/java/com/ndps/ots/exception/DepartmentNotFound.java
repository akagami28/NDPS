package com.ndps.ots.exception;

public class DepartmentNotFound extends RuntimeException {

	/**
	 * A Custom Exception class
	 * 
	 * @param message
	 */

	private static final long serialVersionUID = 3004675946498228709L;

	public DepartmentNotFound(String message) {
		super(message);
	}

}