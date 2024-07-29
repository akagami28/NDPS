package com.application.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ndps.ots.controller.EmployeeController;
import com.ndps.ots.dto.EmployeeDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.entity.Employee;
import com.ndps.ots.repository.EmployeeRepository;
import com.ndps.ots.service.employee.EmployeeServiceImpl;

class EmployeeServiceImplTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private AutoCloseable autoCloseable;

    Department department,department2;
    Employee employee,employee2;
    EmployeeDTO employeeDTO,employeeDTO2;
    String tracerId;

    List<Employee> employees = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

//		employeeServiceImpl = new EmployeeServiceImpl(employeeRepository);

        department = new Department(1, "IT");
        employee = new Employee(1, "Akhil", department);
        employeeDTO = new EmployeeDTO(1, "Akhil", department);

        department2 = new Department(2, "Finance");
        employee2 = new Employee(2, "Den", department2);
        employeeDTO2 = new EmployeeDTO(2, "Den", department2);
        
        employees.add(employee);
        employees.add(employee2);
        
        tracerId = EmployeeController.tracerId;

    }

    @Test
    void testSaveEmployee()
    {
        //	mock(Employee.class);
        //	mock(EmployeeRepository.class);
        
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        assertThat(employeeServiceImpl.saveEmployee(employeeDTO,tracerId)).isNotNull();
    }

	
	@Test 
	void testGetAllEmployees() 
	{
		when(employeeRepository.findAll()).thenReturn(employees);
		assertThat(employeeServiceImpl.getAllEmployees(tracerId).get(0).getName()).isEqualTo(employee.getName());
		  //assertThat(employeeServiceImpl.getAllEmployees()).isNotNull(); 
	}
	 

    @Test
    void testGetEmployeeById()
    {

        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employee));
       // employeeServiceImpl.getEmployeeById(1);
        //assertThat(employeeServiceImpl.getEmployeeById(1).getId()).isEqualTo(employeeDTO.getId());
        assertThat(employeeServiceImpl.getEmployeeById(1,tracerId).getName()).isEqualTo(employee.getName());
        //assertThat(employeeServiceImpl.getEmployeeById(1).getDept().getId()).isEqualTo(employee.getDepartment().getId());
    }
    
    @Test
    void testGetEmployeeByDepartment()
    {
    	when(employeeRepository.findByDepartment(department)).thenReturn(employee);
    	assertThat(employeeServiceImpl.getEmployeeByDepartment(department, tracerId).getName()).isEqualTo(employeeDTO.getName());
    }

    @Test
    void testUpdateEmployeeById()
    {
        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employee));
        employee.setDepartment(department2);
        employee.setId(2);
        employee.setName("Den");
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        assertThat(employeeServiceImpl.updateEmployeeById(employeeDTO,tracerId)).isNotNull();

    }

    @Test
    void testdeleteById()
    {
        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employee));
        doNothing().when(employeeRepository).deleteById(employee.getId());

        assertAll(() -> employeeServiceImpl.deleteEmployeeById(1,tracerId));
    }

    @Test
    void testdeleteAll()
    {
        doNothing().when(employeeRepository).deleteAll();
        assertAll(() -> employeeServiceImpl.deleteAll(tracerId));
    }
    
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        department = null;
        employee = null;
        employeeDTO = null;
        tracerId = null;
    }
}
