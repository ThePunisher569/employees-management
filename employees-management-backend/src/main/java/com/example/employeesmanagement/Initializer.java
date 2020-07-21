/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employeesmanagement;

import com.example.employeesmanagement.model.Employee;
import com.example.employeesmanagement.model.Job;
import com.example.employeesmanagement.repositories.EmployeeRepo;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Momin Aatif
 */
@Component
public class Initializer implements CommandLineRunner{
    
    @Autowired
    private EmployeeRepo empRepo;

    @Override
    public void run(String... args) throws Exception {
        Stream.of(new Employee("johnson","johnson@gmail.com","comp","956855465"),
                new Employee("john","johnvin","entc","956555565"),
                new Employee("mark","markzucc","civil","95455466"),
                new Employee("satya","satyanadela","mech","95664656"))
                .forEach(employee -> empRepo.save(employee));
        empRepo.findAll().forEach(System.out::println);
        
        Employee emp = empRepo.findByName("johnson");
        Job job = new Job();
        job.setTitle("Computer Engineer");
        job.setDescription("working with computer");
        emp.setJob(job);
        empRepo.save(emp);
        empRepo.findAll().forEach(System.out::println);
        
    }
    
}
