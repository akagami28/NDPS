package com.ndps.ots.service.department;

import java.util.List;

import com.ndps.ots.dto.DepartmentDTO;

/**
 * This interface is the Service for the entity department.
 * @author AkhilK
 *
 */
public interface DepartmentService {

	DepartmentDTO saveDepartment(DepartmentDTO departmentDTO, String tracerId);
	List<DepartmentDTO> getAllDepartment(String tracerId);
	DepartmentDTO getDepartmentById(Integer deptId, String tracerid);
	DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, String tracerid);
	void deleteDepartmentById(Integer deptId, String tracerid);
	void deleteAll(String tracerid);
	
	
	
}
