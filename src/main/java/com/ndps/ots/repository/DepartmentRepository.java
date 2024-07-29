package com.ndps.ots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndps.ots.entity.Department;

/**
 * This repository extends the JPA Repository which is used for CRUD operations.
 * 
 * @author AkhilK
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
