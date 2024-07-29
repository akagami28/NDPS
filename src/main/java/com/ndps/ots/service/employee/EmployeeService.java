package com.ndps.ots.service.employee;

import java.util.List;

import com.ndps.ots.dto.EmployeeDTO;
import com.ndps.ots.entity.Department;

/**
 * This interface is the Service for the entity employee.
 * @author AkhilK
 *
 */
public interface EmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO employeedto, String tracerId);
	List<EmployeeDTO> getAllEmployees(String tracerId);
	EmployeeDTO getEmployeeById(Integer id,String tracerId);
	EmployeeDTO getEmployeeByDepartment(Department department,String tracerId);
	EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO,String tracerId);
	void deleteEmployeeById(Integer id, String tracerId);
	void deleteAll(String tracerId);	
}
