package com.ndps.ots.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * A DTO class which is used to map with entity class.
 * 
 * @author AkhilK
 *
 */

@Getter
@ToString
@AllArgsConstructor
@Builder
public class DepartmentDTO {

	private int id;
	private String name;
}
