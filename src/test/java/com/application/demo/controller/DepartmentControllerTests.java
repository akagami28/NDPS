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
import org.springframework.http.HttpStatus;

import com.ndps.ots.controller.DepartmentController;
import com.ndps.ots.dto.DepartmentDTO;
import com.ndps.ots.entity.Department;
import com.ndps.ots.service.department.DepartmentService;


class DepartmentControllerTests {

	@InjectMocks
	private DepartmentController departmentController;
	
	@Mock
	private DepartmentService departmentService;
	
	Department department1;
	Department department2;
	DepartmentDTO departmentDTO;
	DepartmentDTO departmentDTO2;
	String tracerId;
	List<DepartmentDTO> list = new ArrayList<>();
	
    @BeforeEach
    void setUp()
    {
    	departmentService = Mockito.mock(DepartmentService.class);
    	departmentController = Mockito.mock(DepartmentController.class);
    	departmentController = new DepartmentController(departmentService);
    	
    	department1 = new Department(1, "Finance");
    	department2 = new Department(2, "Operations");
    	departmentDTO = new DepartmentDTO(1, "Finance");
    	departmentDTO2 = new DepartmentDTO(2, "Operations");
    	
    	tracerId = DepartmentController.tracerId;
    	
    	list.add(departmentDTO);
    	list.add(departmentDTO2);
    }

    @Test
    void testSaveDepartment()
    {	
    	when(departmentService.saveDepartment(departmentDTO,tracerId)).thenReturn(departmentDTO);
    	
    	assertThat(HttpStatus.CREATED)
    		.isEqualTo(departmentController.saveDepartment(departmentDTO).getStatusCode());
    	assertEquals(departmentDTO, departmentController.saveDepartment(departmentDTO).getBody());
    }

    @Test
    void testgetAllDepartments()
    {
    	when(departmentService.getAllDepartment(tracerId)).thenReturn(list);
    	
    	assertThat(HttpStatus.OK)
    	.isEqualTo(departmentController.getAllDepartments()
    			.getStatusCode());
    	
    	assertEquals(list, departmentController.getAllDepartments().getBody());
    }

    @Test
    void testgetDepartmentByID()
    {
    	when(departmentService.getDepartmentById(1,tracerId)).thenReturn(departmentDTO);
    	
    	assertThat(HttpStatus.OK)
    	.isEqualTo(departmentController.getDepartmentById(1)
    			.getStatusCode());
    	
    	assertEquals(departmentDTO, departmentController.getDepartmentById(1).getBody());
    }

    @Test
    void testupdateDepartmentByID()
    {
    	when(departmentService.updateDepartmentById(departmentDTO,tracerId)).thenReturn(departmentDTO);
    	
    	assertThat(HttpStatus.ACCEPTED)
    	.isEqualTo(departmentController.updateDepartmentById(departmentDTO)
    			.getStatusCode());
    	
    	assertEquals(departmentDTO, departmentController.updateDepartmentById(departmentDTO).getBody());
    }

    @Test
    void testdeleteDepartmentByID()
    {
    	doNothing().when(departmentService).deleteDepartmentById(1,tracerId);
    	
    	assertThat(HttpStatus.OK)
    	.isEqualTo(departmentController.deleteDepartmentById(1)
    			.getStatusCode());
    	
    	assertThat(departmentController.deleteDepartmentById(1).getBody()).isExactlyInstanceOf(String.class);
    }

    @Test
    void testdeleteAllDepartments()
    {
    	doNothing().when(departmentService).deleteAll(tracerId);
    	
    	assertThat(HttpStatus.OK)
    	.isEqualTo(departmentController.deleteAllDepartments()
    			.getStatusCode());
    	
    	assertThat(departmentController.deleteAllDepartments().getBody()).isExactlyInstanceOf(String.class);
    }

    @AfterEach
    void tearDown()
    {
    	department1 = null;
    	department2 = null;
    	departmentDTO = null;
    	departmentDTO2 = null;
    	tracerId = null;
    }
}
