package com.ndps.ots.service.employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndps.ots.dto.EmployeeDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.entity.Employee;
import com.ndps.ots.repository.EmployeeRepository;
import com.ndps.ots.utility.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class implements the EmployeeService interface and all the CRUD logic is
 * implemented here.
 * 
 * @author AkhilK
 *
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	/**
	 * Converts an employee into its corresponding DTO.
	 * 
	 * @param employee
	 * @return EmployeeDTO
	 */
	public EmployeeDTO employeeToDTOMapper(Employee employee) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setDepartment(employee.getDepartment());
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		
		log.info(employeeDTO.toString());
		
		return employeeDTO;
	}

	/**
	 * Converts a DTO class into its corresponding employee class.
	 * 
	 * @param employeeDTO
	 * @return Employee
	 */
	public Employee dtoToEmployeeMapper(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		
		log.info(employee.toString());

		return employee;

	}

	/**
	 * This method saves the employee into the database.
	 * 
	 * @param employeeDTO
	 * @return EmployeeDTO
	 */
	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDto,String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside saveEmployee() - Request >> {} ",Constant.SERVICE_1,tracerId,employeeDto);
		Employee employee = dtoToEmployeeMapper(employeeDto);
		employeeRepository.save(employee);
		EmployeeDTO employeeDTO2 = employeeToDTOMapper(employee);
		log.info("{}_{} Inside saveEmployee() - Response >> {} ",Constant.SERVICE_1,tracerId,employeeDTO2);
		
		return employeeDTO2;
	}

	/**
	 * This method retrieves all the employee details from the database.
	 * 
	 * @return List<EmployeeDTO>
	 */
	@Override
	public List<EmployeeDTO> getAllEmployees(String tracerId) {

		log.info("{}_{} Inside getAllEmployees()",Constant.SERVICE_1,tracerId);
		List<EmployeeDTO> employeeDTOs = employeeRepository.findAll()
											.stream()
											.map(x-> employeeToDTOMapper(x))
											.collect(Collectors.toList());
		log.info("{}_{} Inside getAllEmployees() - Response >> {} ",Constant.SERVICE_1,tracerId,employeeDTOs);
		
		return employeeDTOs;
	}

	/**
	 * This method retrieves employee details by id.
	 * 
	 * @param id
	 * @return EmployeeDTO
	 */
	@Override
	public EmployeeDTO getEmployeeById(Integer id,String tracerId) {
		// TODO Auto-generated method stub
//		Employee employee= employeeRepository.findById(id)
//				.orElseThrow(()-> new EmployeeNotFound("Employee Not Found"));
		log.info("{}_{} Inside getEmployeeById() - Request >> {} ",Constant.SERVICE_1,tracerId,id);
		Optional<Employee> emp = employeeRepository.findById(id);
		Employee employee = emp.get();
		log.info("{}_{} Inside getEmployeeById() - Response >> {} ",Constant.SERVICE_1,tracerId,employee);
		
		return employeeToDTOMapper(employee);

	}
	
	/**
	 * This method retrieves employee details by department.
	 * @param department
	 * @return EmployeeDTO
	 */
	@Override
	public EmployeeDTO getEmployeeByDepartment(Department department,String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside getEmployeeByDepartment() - Request >> {}",Constant.SERVICE_1,tracerId,department);
		Employee employee = employeeRepository.findByDepartment(department);
		EmployeeDTO employeeDTO = employeeToDTOMapper(employee);
		log.info("{}_{} Inside getEmployeeByDepartment() - Response >> {}",Constant.SERVICE_1,tracerId,employeeDTO);
		
		return employeeDTO;
	}

	/**
	 * This method update the employee details in the database.
	 * 
	 * @param EmployeeDTO
	 * @param id
	 * @return EmployeeDTO
	 */
	@Override
	public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO,String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside updateEmployeeById() - Request >> {} ",Constant.SERVICE_1,tracerId,employeeDTO);
		Employee employee = dtoToEmployeeMapper(employeeDTO);
//		Employee existingEmployee = employeeRepository.findById(employee.getId())
//				.orElseThrow(()-> new EmployeeNotFound("Not Found!!!"));
		Optional<Employee> optional = employeeRepository.findById(employee.getId());
		Employee existingEmployee = optional.get();
		existingEmployee.setId(employee.getId());
		existingEmployee.setName(employee.getName());
		existingEmployee.setDepartment(employee.getDepartment());
		employeeRepository.save(existingEmployee);
		EmployeeDTO employeeDTO2 = employeeToDTOMapper(existingEmployee);
		log.info("{}_{} Inside updateEmployeeById() - Response >> {} ",Constant.SERVICE_1,tracerId,employeeDTO2);

		return employeeDTO2;
	}

	/**
	 * This method deletes the employee in the database by id.
	 * 
	 * @param id
	 */
	@Override
	public void deleteEmployeeById(Integer id,String tracerId) {
		// TODO Auto-generated method stub
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(()-> new EmployeeNotFound("Not Found"));
		log.info("{}_{} Inside deleteEmployeeById() - Request >> {} ",Constant.SERVICE_1,tracerId,id);
		Optional<Employee> optional = employeeRepository.findById(id);
		//optional.get().setDepartment(null);
		Employee employee = optional.get();
		log.info("{}_{} Inside deleteEmployeeById() - Response >> {} ",Constant.SERVICE_1,tracerId,employee);
		employeeRepository.delete(employee);
	}

	/**
	 * This method deletes all the employees present in the database.
	 */
	@Override
	public void deleteAll(String tracerId) {
		// TODO Auto-generated method stub
		log.info("{}_{} Inside deletAll() ",Constant.SERVICE_1,tracerId);
		employeeRepository.deleteAll();

	}
}
