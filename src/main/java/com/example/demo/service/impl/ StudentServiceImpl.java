package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // REQUIRED constructor order
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Invalid year");
        }

        studentRepository.findByRollNumber(student.getRollNumber())
                .ifPresent(s -> {
                    throw new ApiException("roll number exists");
                });

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
