package com.example.employeecrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email 
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String department;

    @Positive(message = "Salary must be positive")
    private Double salary;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}