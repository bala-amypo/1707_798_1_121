package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping
    public Student add(@RequestBody Student student) {
        return student;
    }
}
