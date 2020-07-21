/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employeesmanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;

/**
 *
 * @author Aatif
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NonNull
    private String name, email, branch, phoneNo;
    
    private String address;
    private String dob;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Job job;
}
