package com.application.demo.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ndps.ots.exception.DepartmentNotFound;
import com.ndps.ots.exception.EmployeeNotFound;
import com.ndps.ots.exception.ErrorResponse;
import com.ndps.ots.exception.Handler;

public class ExceptionTests {
	
	private EmployeeNotFound employeeNotFound;
	private DepartmentNotFound departmentNotFound;
	private RuntimeException runtimeException;
	private UsernameNotFoundException usernameNotFoundException;
	
	@BeforeEach
	void setUp()
	{
		employeeNotFound = new EmployeeNotFound("Employee Not Found!!!");
		departmentNotFound = new DepartmentNotFound("Department Not Found!!!");
		runtimeException = new RuntimeException("Runtime Exception");
		usernameNotFoundException = new UsernameNotFoundException("User Not Found!!!");
	}

	@Test
	void testErrorResponse()
	{
		ErrorResponse errorResponse = new ErrorResponse("Error Format", 201, System.currentTimeMillis());
		ErrorResponse errorResponse2 = new ErrorResponse();
		errorResponse2.setMessageString(errorResponse.getMessageString());
		errorResponse2.setStatus(errorResponse.getStatus());
		errorResponse2.setTimestamp(errorResponse.getTimestamp());
		errorResponse2.toString();
	}
	
	@Test
	void testHandler()
	{
		Handler handler = new Handler();
		handler.handlerMethod(employeeNotFound);
		handler.handlerMethod(departmentNotFound);
		handler.handlerMethod(runtimeException);
		handler.handlerMethod(usernameNotFoundException);
	}
	
	@AfterEach
	void tearDown()
	{
		employeeNotFound=null;
		departmentNotFound=null;
		runtimeException=null;
	}
}
