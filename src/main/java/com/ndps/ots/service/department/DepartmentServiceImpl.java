package com.ndps.ots.service.department;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndps.ots.dto.DepartmentDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.repository.DepartmentRepository;
import com.ndps.ots.utility.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class implements the DepartmentService interface and all the CRUD logic
 * is implemented here.
 * 
 * @author AkhilK
 *
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public DepartmentDTO departmentToDtoMapper(Department department)
	{
		DepartmentDTO departmentDTO = DepartmentDTO.builder()
				.id(department.getId())
				.name(department.getName())
				.build();
		log.info(departmentDTO.toString());
		
		return departmentDTO;
	}
	
	public Department DtoToDepartmentMapper(DepartmentDTO departmentDTO)
	{
		Department department = Department.builder()
				.id(departmentDTO.getId())
				.name(departmentDTO.getName())
				.build();
		log.info(department.toString());
		
		return department;
	}

	/**
	 * This method saves the department into the database.
	 * 
	 * @param departmentDTO
	 * @return DepartmentDTO
	 */
	@Override
	public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO,String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside saveDepartment() - Request >> {} ",Constant.SERVICE_2,tracerId,departmentDTO);
		Department department = DtoToDepartmentMapper(departmentDTO);
		departmentRepository.save(department);
		DepartmentDTO dto = departmentToDtoMapper(department);
		log.info("{}_{} Inside saveDepartment() - Reponse >> {} ",Constant.SERVICE_2,tracerId,dto);
		
		return dto;
	}

	/**
	 * This method retrieves all the department details from the database.
	 * 
	 * @return List<DepartmentDTO>
	 */
	@Override
	public List<DepartmentDTO> getAllDepartment(String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside getAllDepartment()",Constant.SERVICE_2,tracerId);
		List<Department> list = departmentRepository.findAll();
		List<DepartmentDTO> departments = list.stream()
				.map(x -> departmentToDtoMapper(x))
				.collect(Collectors.toList());
		log.info("{}_{} Inside getAllDepartment() - Response >> {} ",Constant.SERVICE_2,tracerId,departments);

		return departments;
	}

	/**
	 * This method retrieves department based on department ID.
	 * 
	 * @param deptId
	 * @return DepartmentDTO
	 */
	@Override
	public DepartmentDTO getDepartmentById(Integer deptId,String tracerId) {
		// TODO Auto-generated method stub
//		Department department = departmentRepository.findById(deptId)
//				.orElseThrow(() -> new DepartmentNotFound("Not Found!!!"));
		log.info("{}_{} Inside getDepartmentById() - Request >> {} ",Constant.SERVICE_2,tracerId,deptId);
		Optional<Department> optional = departmentRepository.findById(deptId);
		Department department = optional.get();
		DepartmentDTO departmentDTO = departmentToDtoMapper(department);
		log.info("{}_{} Inside getDepartmentById() - Response >> {} ",Constant.SERVICE_2,tracerId,departmentDTO);

		return departmentDTO;
	}

	/**
	 * This method update the department details in the database.
	 * 
	 * @param departmentDTO
	 * @param deptId
	 * @return DepartmentDTO
	 */
	@Override
	public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO,String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside updateDepartmentById() - Request >> {} ",Constant.SERVICE_2,tracerId,departmentDTO);
		Department department = DtoToDepartmentMapper(departmentDTO);
//		Department existingDepartment = departmentRepository.findById(department.getId())
//				.orElseThrow(() -> new DepartmentNotFound("Not Found!!!"));
		Optional<Department> optional = departmentRepository.findById(department.getId());
		Department existingDepartment = optional.get();
		
		existingDepartment.setName(department.getName());
		existingDepartment.setId(department.getId());
		departmentRepository.save(existingDepartment);
		DepartmentDTO dto = departmentToDtoMapper(existingDepartment);
		log.info("{}_{} Inside updateDepartmentById() - Response >> {} ",Constant.SERVICE_2,tracerId,departmentDTO);

		return dto;
	}

	/**
	 * This method deletes the department in the database by id.
	 * 
	 * @param deptId
	 */
	@Override
	public void deleteDepartmentById(Integer deptId,String tracerId) {
		// TODO Auto-generated method stub
//		Department department = departmentRepository.findById(deptId)
//				.orElseThrow(() -> new DepartmentNotFound("Not Found!!!"));
		log.info("{}_{} Inside deleteDepartmentById() - Request >> {} ",Constant.SERVICE_2,tracerId,deptId);
		Optional<Department> optional = departmentRepository.findById(deptId);
		Department department = optional.get();
		log.info("{}_{} Inside deleteDepartmentById() - Response >> {} ",Constant.SERVICE_2,tracerId,department);
		
		departmentRepository.delete(department);
	}

	/**
	 * This method deletes all the departments present in the database.
	 */
	@Override
	public void deleteAll(String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside deleteAll()",Constant.SERVICE_2,tracerId);
		departmentRepository.deleteAll();
	}
}
