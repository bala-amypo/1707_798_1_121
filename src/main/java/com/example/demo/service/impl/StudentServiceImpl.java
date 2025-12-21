package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student save(Student student) {
        if (student.getYear() < 1 || student.getYear() > 4) {
            throw new ApiException("invalid year");
        }
        return repo.save(student);
    }

    @Override
    public Student get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Student not found"));
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }
}
