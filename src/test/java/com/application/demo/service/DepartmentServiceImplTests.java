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

import com.ndps.ots.controller.DepartmentController;
import com.ndps.ots.dto.DepartmentDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.repository.DepartmentRepository;
import com.ndps.ots.service.department.DepartmentServiceImpl;

class DepartmentServiceImplTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    private AutoCloseable autoCloseable;

    Department department,department2;
    DepartmentDTO departmentDTO,departmentDTO2;
    String tracerId;
    
    List<Department> departments = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);

        department = new Department(1, "IT");
        departmentDTO = new DepartmentDTO(1, "IT");

        department2 = new Department(2, "Finance");
        departmentDTO2 = new DepartmentDTO(2, "Finance");
        
        departments.add(department);
        departments.add(department2);
        
        tracerId = DepartmentController.tracerId;
    
    }

    @Test
    void testSaveDepartment()
    {
        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);
        assertThat(departmentServiceImpl.saveDepartment(departmentDTO,tracerId)).isNotNull();
    }

	
	@Test 
	void testGetAllDepartments() 
	{
		when(departmentRepository.findAll()).thenReturn(departments);
		assertThat(department.getName()).isEqualTo(departmentServiceImpl	
				.getAllDepartment(tracerId).get(0).getName()); 
	}
	 

    @Test
    void testGetDepartmentByID()
    {
        when(departmentRepository.findById(1)).thenReturn(Optional.ofNullable(department));
        assertThat(departmentServiceImpl.getDepartmentById(1,tracerId).getName()).isEqualTo(department.getName());
    }

    @Test
    void testUpdateDepartmentByID()
    {
        when(departmentRepository.findById(1)).thenReturn(Optional.ofNullable(department));
        department.setId(3);
        department.setName("Operations");
        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        assertThat(departmentServiceImpl.updateDepartmentById(departmentDTO,tracerId)).isNotNull();
    }

    @Test
    void testDeleteDepartmentByID()
    {
        when(departmentRepository.findById(1)).thenReturn(Optional.ofNullable(department));
        doNothing().when(departmentRepository).deleteById(department.getId());
        assertAll(() -> departmentServiceImpl.deleteDepartmentById(1,tracerId));
    }

    @Test
    void testDeleteAll()
    {
        doNothing().when(departmentRepository).deleteAll();
        assertAll(() -> departmentServiceImpl.deleteAll(tracerId));
    }

    @AfterEach
    void tearDown() throws Exception
    {
        autoCloseable.close();
        department = null;
        departmentDTO = null;
        tracerId = null;
    }
}
