package com.ndps.ots.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndps.ots.dto.EmployeeDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.service.employee.EmployeeService;
import com.ndps.ots.utility.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AkhilK This Controller contains all the CRUD operations for Employee.
 */

@Slf4j
@RestController
@RequestMapping("/api/employee")
//@Tag(name = "Employee",description = "Employee Controller containing all the REST Endpoints for Employee")
@Api(value = "Employee Controller containing all the REST Endpoints for Employee")
public class EmployeeController {

	private EmployeeService employeeService;

	/**
	 * Adding Dependency through Constructor Injection
	 * 
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	public final static String tracerId = String.valueOf(System.currentTimeMillis());

	/**
	 * Calls the save method in Service.
	 * 
	 * @param employeeDTO
	 * @return ResponseEntiy<EmployeeDTO>
	 */
	@PostMapping("/")
	@ApiOperation(value = "Store the Employee")
	// @Operation(summary = "Save Employee", description = "Store the employee in
	// database")
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		log.info("{}_{} Inside saveEmployee() - Request >> {} ", Constant.SERVICE_1, tracerId, employeeDTO);
		EmployeeDTO dto = employeeService.saveEmployee(employeeDTO, tracerId);
		log.info("{}_{} Inside saveEmployee() , Employee saved in the database with ID: " + dto, Constant.SERVICE_1,
				tracerId);
		return new ResponseEntity<EmployeeDTO>(dto, HttpStatus.CREATED);
	}

	/**
	 * Calls the method getAllEmployees present in Service which returns all the
	 * employees.
	 * 
	 * @return ResponseEntity<List<EmployeeDTO>>
	 */
	@GetMapping("/")
	@ApiOperation(value = "Retrieve All the Employees")
	// @Operation(summary = "Get All Employees", description = "Retrieve all the
	// employees from database")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		log.info("{}_{} Inside getAllEmployees()", Constant.SERVICE_1, tracerId);
		List<EmployeeDTO> list = employeeService.getAllEmployees(tracerId);
		log.info("{}_{} Inside getAllEmployees(), all the employees from the database are retrieved!!! " + list,
				Constant.SERVICE_1, tracerId);
		return new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
	}

	/**
	 * Calls the method getEmployeeById present in Service which returns an employee
	 * based on the given input id.
	 * 
	 * @param id
	 * @return ResponeEntity<EmployeeDTO>
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Employee By ID")
	// @Operation(summary = "Get Employee By ID", description = "Retrieve employee
	// based on employee ID")
	public ResponseEntity<EmployeeDTO> getEmployeeById(// @Parameter(description = "Employee ID")
			@PathVariable Integer id) {
		log.info("{}_{} Inside getEmployeeById()- Request >> {} ", Constant.SERVICE_1, tracerId, id);
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id, tracerId);
		log.info("{}_{} Inside getEmployeeById(), Employee id retrieved is: " + employeeDTO, Constant.SERVICE_1,
				tracerId);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	/**
	 * Calls the method getEmployeeByDepartment present in Service which returns an
	 * employee based on department.
	 * 
	 * @param department
	 * @return ReponseEntity<EmployeeDTO>
	 */
	@GetMapping("/department/")
	public ResponseEntity<EmployeeDTO> getEmployeeByDepartment(@RequestBody Department department) {
		log.info("{}_{} Inside getEmployeeByDepartment() - Request >> {}", Constant.SERVICE_1, tracerId, department);
		EmployeeDTO employeeDTO = employeeService.getEmployeeByDepartment(department, tracerId);
		log.info("{}_{} Inside getEmployeeByDepartment() - Response >> {}", Constant.SERVICE_1, tracerId, employeeDTO);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	/**
	 * Calls the method updateEmployeeById present in Service which updates the
	 * employee details and returns an employee.
	 * 
	 * @param employeeDTO
	 * @param id
	 * @return ResponseEntity<EmployeeDTO>
	 */
	@PutMapping("/")
	@ApiOperation(value = "Update Employee By ID")
	// @Operation(summary = "Update Employee By ID", description = "Updating an
	// employee by ID")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(// @Parameter(description = "Employee Object")
			@RequestBody EmployeeDTO employeeDTO) {
		log.info("{}_{} Inside updateEmployeeById()- Request >> {} ", Constant.SERVICE_1, tracerId, employeeDTO);
		EmployeeDTO dto = employeeService.updateEmployeeById(employeeDTO, tracerId);
		log.info("{}_{} Inside updateEmployeeById(), Updated Employee with ID: " + dto, Constant.SERVICE_1, tracerId);
		return new ResponseEntity<EmployeeDTO>(dto, HttpStatus.ACCEPTED);
	}

	/**
	 * Calls the method deleteEmployeeById present in Service which deletes employee
	 * based on the given input id.
	 * 
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Employee By ID")
	// @Operation(summary = "Delete Employee By ID", description = "Delete an
	// employee by ID")
	public ResponseEntity<String> deleteById(// @Parameter(description = "Employee ID")
			@PathVariable Integer id) {
		log.info("{}_{} Inside deleteById()- Request >> {} ", Constant.SERVICE_1, tracerId, id);
		employeeService.deleteEmployeeById(id, tracerId);
		log.info("{}_{} Inside deleteById(), Deleted employee with ID: " + id, Constant.SERVICE_1, tracerId);
		return new ResponseEntity<String>("Employee deleted!!! " + id, HttpStatus.OK);
	}

	/**
	 * Calls the method deleteAll present in Service which deletes all the employees
	 * in the database.
	 * 
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/")
	@ApiOperation(value = "Delete All Employees")
	// @Operation(summary = "Delete All Employees",description = "delete all
	// employees in the database")
	public ResponseEntity<String> deleteAll() {
		log.info("{}_{} Inside deleteAll()", Constant.SERVICE_1, tracerId);
		employeeService.deleteAll(tracerId);
		log.info("{}_{} Inside deleteAll(), All the Employees are deleted successfully!!!", Constant.SERVICE_1,
				tracerId);
		return new ResponseEntity<String>("All the Employees are deleted!!!", HttpStatus.OK);
	}

}
