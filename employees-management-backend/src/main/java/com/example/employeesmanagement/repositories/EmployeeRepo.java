/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employeesmanagement.repositories;

import com.example.employeesmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aatif
 */
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    Employee findByName(String name);
}
