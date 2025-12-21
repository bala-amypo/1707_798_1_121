package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student save(@RequestBody Student s) {
        return service.save(s);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
}
