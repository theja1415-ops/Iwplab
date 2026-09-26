package com.example.aopdemo.controller;

import com.example.aopdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public String getStudent(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/register")
    public String registerStudent(@RequestParam String name) {
        studentService.registerStudent(name);
        return "Student registered successfully!";
    }
}