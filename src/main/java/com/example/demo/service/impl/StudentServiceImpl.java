package com.example.demo.service.impl;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Student addStudent(Student s) {
        if (s.getRollNumber() == null || s.getName() == null || s.getYear() < 1 || s.getYear() > 5) {
            throw new ApiException("Invalid student data");
        }
        studentRepo.findByRollNumber(s.getRollNumber()).ifPresent(existing -> {
            throw new ApiException("Student with roll number already exists");
        });
        return studentRepo.save(s);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
