package com.example.employeecrud.controller;

import com.example.employeecrud.model.Employee;
import com.example.employeecrud.repo.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) { 
        this.repo = repo; 
    }

    // READ (list)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", repo.findAll());
        return "employees";
    }

    // CREATE form
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Add Employee");
        return "employee-form";
    }

    // CREATE/UPDATE save
    @PostMapping
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", employee.getId() == null ? "Add Employee" : "Edit Employee");
            return "employee-form";
        }
        repo.save(employee);
        return "redirect:/employees";
    }

    // UPDATE form
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Employee e = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));
        model.addAttribute("employee", e);
        model.addAttribute("title", "Edit Employee");
        return "employee-form";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/employees";
    }
}