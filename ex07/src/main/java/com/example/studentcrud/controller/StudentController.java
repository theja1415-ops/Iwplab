package com.example.studentcrud.controller;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.repo.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo){ 
        this.repo = repo; 
    }

    // READ (list)
    @GetMapping
    public String list(Model model){
        model.addAttribute("students", repo.findAll());
        return "list";
    }

    // CREATE (form)
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Add Student");
        return "form";
    }

    // CREATE + UPDATE (save)
    @PostMapping
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("title", student.getId() == null ? "Add Student" : "Edit Student");
            return "form";
        }
        Objects.requireNonNull(student, "Student must not be null");
        repo.save(student);
        return "redirect:/students";
    }

    // UPDATE (edit form)
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Objects.requireNonNull(id, "id must not be null");
        Student s = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("student", s);
        model.addAttribute("title", "Edit Student");
        return "form";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        Objects.requireNonNull(id, "id must not be null");
        repo.deleteById(id);
        return "redirect:/students";
    }
}