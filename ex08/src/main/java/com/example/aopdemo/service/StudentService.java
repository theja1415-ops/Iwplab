package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String getStudentById(int id) {
        return "Student{id=" + id + ", name='Aswathi E'}";
    }

    public void registerStudent(String name) {
        System.out.println("Registering student: " + name);
    }
}