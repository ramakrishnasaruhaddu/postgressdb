package com.example.postgressdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgressdb.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	
	
	
    
}
