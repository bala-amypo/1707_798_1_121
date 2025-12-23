package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student save(Student student) {
        return repo.save(student);
    }

    @Override
    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }
}
