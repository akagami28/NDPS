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

import com.ndps.ots.dto.DepartmentDTO;
import com.ndps.ots.service.department.DepartmentService;
import com.ndps.ots.utility.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * This Controller class contains all the CRUD operations for Department
 * 
 * @author AkhilK
 *
 */

@Slf4j
@RestController
@RequestMapping("/api/department")
@Api(value = "Department Controller containing all the REST Endpoints for Department")
//@Tag(name = "Department", description = "Department Controller containing all the REST Endpoints for Department")
public class DepartmentController {

	private DepartmentService departmentService;

	/**
	 * Adding Dependency through Constructor Injection
	 * 
	 * @param departmentService
	 */
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	public static final String tracerId = String.valueOf(System.currentTimeMillis());

	/**
	 * Calls the save method in Service
	 * 
	 * @param departmentDTO
	 * @return ResponseEntity<DepartmentDTO>
	 */
	@PostMapping("/")
	@ApiOperation(value = "Store the Department")
	public ResponseEntity<DepartmentDTO> saveDepartment(// @Parameter(description = "Department Object")
			@RequestBody DepartmentDTO departmentDTO) {
		log.info("{}_{} Inside saveDepartment() - Request >> {}", Constant.SERVICE_2, tracerId, departmentDTO);
		DepartmentDTO departmentDTO2 = departmentService.saveDepartment(departmentDTO, tracerId);
		log.info("{}_{} Inside saveDepartment(), Data saved in the database with ID: " + departmentDTO2,
				Constant.SERVICE_2, tracerId);

		return new ResponseEntity<DepartmentDTO>(departmentDTO2, HttpStatus.CREATED);
	}

	/**
	 * Calls the method getAllDepartments in service which returns the list of
	 * departments.
	 * 
	 * @return ResponseEntity<List<DepartmentDTO>>
	 */
	@GetMapping("/")
	@ApiOperation(value = "Retrieve All the Departments")
	// @Operation(summary = "Get All Departments", description = "Retrieve all the
	// departments from database")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
		log.info("{}_{} Inside getAllDepartments()", Constant.SERVICE_2, tracerId);
		List<DepartmentDTO> list = departmentService.getAllDepartment(tracerId);
		log.info("{}_{} Inside getAllDepartments(), All the departments are retrieved " + list, Constant.SERVICE_2,
				tracerId);
		return new ResponseEntity<List<DepartmentDTO>>(list, HttpStatus.OK);
	}

	/**
	 * Calls the method getDepartmentById present in Service which returns a
	 * department based on the given input id.
	 * 
	 * @param deptId
	 * @return ResponseEntity<DepartmentDTO>
	 */
	@GetMapping("/{deptId}")
	@ApiOperation(value = "Retrieves Department By ID")
	// @Operation(summary = "Get Department By ID", description = "Retrieving
	// department based on ID")
	public ResponseEntity<DepartmentDTO> getDepartmentById(
			// @Parameter(description = "Department ID")
			@PathVariable Integer deptId) {
		log.info("{}_{} Inside getDepartmentById()- Request >> {} ", Constant.SERVICE_2, tracerId, deptId);
		DepartmentDTO departmentDTO = departmentService.getDepartmentById(deptId, tracerId);
		log.info("{}_{} Inside getDepartmentById(), Department is retrieved with ID: " + departmentDTO);
		return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.OK);
	}

	/**
	 * Calls the method updateDepartmentById present in Service which updates the
	 * department details and returns a department.
	 * 
	 * @param departmentDTO
	 * @param deptId
	 * @return ResponseEntity<DepartmentDTO>
	 */
	@PutMapping("/")
	@ApiOperation(value = "Update Department By ID")
	// @Operation(summary = "Update Department By ID", description = "Updating
	// department based on ID")
	public ResponseEntity<DepartmentDTO> updateDepartmentById(
			// @Parameter(description = "Department Object")
			@RequestBody DepartmentDTO departmentDTO) {
		log.info("{}_{} Inside updateDepartmentById()- Request >> {} ", Constant.SERVICE_2, tracerId, departmentDTO);
		DepartmentDTO departmentDTO2 = departmentService.updateDepartmentById(departmentDTO, tracerId);
		log.info("{}_{} Inside updateDepartmentById(), Updated Department with ID: " + departmentDTO2.getId(),
				Constant.SERVICE_2, tracerId);
		return new ResponseEntity<DepartmentDTO>(departmentDTO2, HttpStatus.ACCEPTED);
	}

	/**
	 * Calls the method deleteDepartmentById present in Service which deletes
	 * department based on the given input id.
	 * 
	 * @param deptId
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/{deptId}")
	@ApiOperation(value = "Delete Department By ID")
	// @Operation(summary = "Delete Department By ID", description = "Deleting
	// department based on ID")
	public ResponseEntity<String> deleteDepartmentById(
			// @Parameter(description = "Department ID")
			@PathVariable Integer deptId) {
		log.info("{}_{} Inside deleteDepartmentById()- Request >> {} ", Constant.SERVICE_2, tracerId, deptId);
		departmentService.deleteDepartmentById(deptId, tracerId);
		log.info("{}_{} Inside deleteDepartmentById(), Deleted the department with ID: " + deptId, Constant.SERVICE_2,
				tracerId);
		return new ResponseEntity<String>("Department deleted having ID: " + deptId, HttpStatus.OK);
	}

	/**
	 * Calls the method deleteAll present in Service which deletes all the
	 * departments in the database.
	 * 
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/")
	@ApiOperation(value = "Delete All Departments")
	// @Operation(summary = "Delete All Departments", description = "Deleting All
	// the departments in the database")
	public ResponseEntity<String> deleteAllDepartments() {
		log.info("{}_{} Inside deleteAllDepartments()", Constant.SERVICE_2, tracerId);
		departmentService.deleteAll(tracerId);
		log.info("{}_{} Inside deleteAllDepartments(), All the Departments are deleted successfully",
				Constant.SERVICE_2, tracerId);
		
		return new ResponseEntity<String>("All the Departments deleted successfully!!!", HttpStatus.OK);
	}
}
