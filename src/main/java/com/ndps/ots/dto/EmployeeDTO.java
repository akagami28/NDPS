package com.ndps.ots.dto;

import com.ndps.ots.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO class which is used to map with entity class.
 * 
 * @author AkhilK
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private int id;
	private String name;
	private Department department;
}
