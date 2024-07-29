package com.application.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.ndps.ots.controller.EmployeeController;
import com.ndps.ots.dto.DepartmentDTO;
import com.ndps.ots.dto.EmployeeDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.entity.Employee;
import com.ndps.ots.service.employee.EmployeeService;

class EmployeeControllerTests {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;

	Employee employee1;
	Employee employee2;
	Department department;
	Department department2;
	DepartmentDTO departmentDTO;
	DepartmentDTO departmentDTO2;
	EmployeeDTO employeeDTO;
	EmployeeDTO employeeDTO2;
	String tracerId;
	List<EmployeeDTO> employeeDTOs = new ArrayList<>();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		department = new Department(1, "Finance");
		employee1 = new Employee(1, "Akhil", department);
		department2 = new Department(1, "HR");
		employee2 = new Employee(2, "Den", department2);
		employeeDTO = new EmployeeDTO(1, "Akhil", department);
		employeeDTO2 = new EmployeeDTO(2, "Den", department2);
		
		employeeDTOs.add(employeeDTO);
		employeeDTOs.add(employeeDTO2);
		
		tracerId = EmployeeController.tracerId;
		//tracerId = String.valueOf(System.currentTimeMillis());
		
		employeeService = Mockito.mock(EmployeeService.class);
		employeeController = Mockito.mock(EmployeeController.class);
		employeeController = new EmployeeController(employeeService);
	}
	
	@Test
    void testSaveEmployee() 
    {
    	when(employeeService.saveEmployee(employeeDTO,tracerId)).thenReturn(employeeDTO);
    	
    	assertThat(HttpStatus.CREATED)
    			.isEqualTo(employeeController.saveEmployee(employeeDTO)
    					.getStatusCode());
    	
    	assertEquals(employeeDTO, employeeController.saveEmployee(employeeDTO).getBody());
    }

	@Test
	void testgetAllEmployees()
	{
		when(employeeService.getAllEmployees(tracerId)).thenReturn(employeeDTOs);
		
		assertThat(HttpStatus.OK)
				.isEqualTo(employeeController.getAllEmployees()
						.getStatusCode());
		
		assertEquals(employeeDTOs, employeeController.getAllEmployees().getBody());
	}

	@Test
    void testgetEmployeeByID()
    {
    	when(employeeService.getEmployeeById(1,tracerId)).thenReturn(employeeDTO);
    	
    	assertThat(HttpStatus.OK)
    			.isEqualTo(employeeController.getEmployeeById(1)
    					.getStatusCode());
    	
    	assertEquals(employeeDTO, employeeController.getEmployeeById(1).getBody());
    }

	@Test
	void testgetEmployeeByDepartment()
	{
		when(employeeService.getEmployeeByDepartment(department, tracerId)).thenReturn(employeeDTO);
		
		assertThat(HttpStatus.OK)
				.isEqualTo(employeeController.getEmployeeByDepartment(department)
						.getStatusCode());
		
		assertEquals(employeeDTO, employeeController.getEmployeeByDepartment(department).getBody());
	}
	
	@Test
	void testupdateEmployeeByID()
	{
		when(employeeService.updateEmployeeById(employeeDTO,tracerId)).thenReturn(employeeDTO);
		
		assertThat(HttpStatus.ACCEPTED)
				.isEqualTo(employeeController.updateEmployeeById(employeeDTO)
						.getStatusCode());
		
		assertEquals(employeeDTO, employeeController.updateEmployeeById(employeeDTO).getBody());
	}

	@Test
	void testdeleteEmployeeByID() {
		doNothing().when(employeeService).deleteEmployeeById(1,tracerId);

		assertThat(HttpStatus.OK).isEqualTo(employeeController.deleteById(1).getStatusCode());

		assertThat(employeeController.deleteById(1).getBody()).isExactlyInstanceOf(String.class);
	}

	@Test
	void testdeleteAllEmployees() {
		doNothing().when(employeeService).deleteAll(tracerId);

		assertThat(HttpStatus.OK).isEqualTo(employeeController.deleteAll().getStatusCode());

		assertThat(employeeController.deleteAll().getBody()).isExactlyInstanceOf(String.class);
	}

	@AfterEach
	void tearDown() {
		department = null;
		department2 = null;
		employee1 = null;
		employee2 = null;
		employeeDTO = null;
		employeeDTO2 = null;
		employeeDTOs = null;
		tracerId = null;
	}
}
