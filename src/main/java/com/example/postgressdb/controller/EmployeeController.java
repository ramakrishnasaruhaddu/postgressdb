package com.example.postgressdb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgressdb.exception.ResourceNotFoundException;
import com.example.postgressdb.model.Employee;
import com.example.postgressdb.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	//getemployee
	//get employye by id
	//save employee
	//update employee
	//delete employee
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//get employee
	@GetMapping("employees")
	public List<Employee> getAllEmployee(){
		  return this.employeeRepository.findAll();
	}
	
     
	//get employee by id
	 @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		
		     Employee employee = employeeRepository.findById(employeeId).
		    		 orElseThrow(()->new ResourceNotFoundException("employee not found xception for this id::"+employeeId));
		
		
		 return ResponseEntity.ok().body(employee);
	}
	 
	 
	//save employee 
	 @PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return this.employeeRepository.save(employee);
		
	}
	
	
	//update employee
	 @PutMapping("employees/{id}")
	 public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			 @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		
		 Employee employee = employeeRepository.findById(employeeId).
	    		 orElseThrow(()->new ResourceNotFoundException("employee not found xception for this id::"+employeeId));
		 
		 
		 employee.setFirstName(employeeDetails.getFirstName());
		 employee.setLastName(employeeDetails.getLastName());
		 employee.setEmail(employeeDetails.getEmail());
		 
		 return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	 
	 //delete employee
	 @DeleteMapping("employees/{id}")
	 public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		 Employee employee = employeeRepository.findById(employeeId).
	    		 orElseThrow(()->new ResourceNotFoundException("employee not found xception for this id::"+employeeId));
		 this.employeeRepository.delete(employee);
		 
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("deleted", Boolean.TRUE);
		 
		 return response;
				 
	 }
}
