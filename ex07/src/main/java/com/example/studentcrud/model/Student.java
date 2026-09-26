package com.example.studentcrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name is required")
    private String name;

    @Email 
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String course;

    @Pattern(regexp="\\d{10}", message="10-digit phone")
    private String phone;

    // getters & setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    
    public String getCourse(){ return course; }
    public void setCourse(String course){ this.course = course; }
    
    public String getPhone(){ return phone; }
    public void setPhone(String phone){ this.phone = phone; }
}