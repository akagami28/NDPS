package com.ndps.ots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndps.ots.entity.Department;
import com.ndps.ots.entity.Employee;

/**
 * This repository extends the JPA Repository which is used for CRUD operations.
 * 
 * @author AkhilK
 *
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByDepartment(Department department);
}
