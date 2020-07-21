/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employeesmanagement.controller;

import com.example.employeesmanagement.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employeesmanagement.repositories.EmployeeRepo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aatif
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepo empRepo;
    
    @GetMapping("/employees")
    Collection<Employee> employees(){
        return empRepo.findAll();
    }
    
    @GetMapping("/employee/{id}")
    ResponseEntity<?> getEmployee(@PathVariable Long id){
        Optional<Employee> emp = empRepo.findById(id);
        System.out.println(emp.get());
        return emp.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } 
    
    @PostMapping("/employee")
    ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee emp) throws URISyntaxException{
        logger.info("Request to create a new employee: {}", emp);
        Employee result = empRepo.save(emp);
        System.out.println(result);
        return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
    } 
    
    @PutMapping("/employee/{id}")
    Employee updateEmployee(@Validated @RequestBody Employee newEmployee, @PathVariable Long id) {
        logger.info("Request to update employee:{}",newEmployee);
        
        return empRepo.findById(id)
            .map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setEmail(newEmployee.getEmail());
            employee.setBranch(newEmployee.getBranch());
            employee.setAddress(newEmployee.getAddress());
            employee.setDob(newEmployee.getDob());
            employee.setJob(newEmployee.getJob());
            employee.setPhoneNo(newEmployee.getPhoneNo());
            return empRepo.save(employee);
          }).orElseGet(()->{
              newEmployee.setId(id);
              return empRepo.save(newEmployee);
          });

  }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        logger.info("Request to delete employee: {}", id);
        empRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
