package com.example.demo.service.impl;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.exception.ApiException;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Student addStudent(Student student) {
        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            throw new ApiException("Student roll number is required");
        }

        if (student.getYear() < 1 || student.getYear() > 4) {
            throw new ApiException("Student year must be between 1 and 4");
        }

        Optional<Student> existing = studentRepo.findByRollNumber(student.getRollNumber());
        if (existing.isPresent()) {
            throw new ApiException("Student with this roll number already exists");
        }

        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
