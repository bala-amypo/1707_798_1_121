package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // ✅ Constructor injection ONLY
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        // ✅ Missing fields validation
        if (student.getRollNumber() == null || student.getName() == null) {
            throw new ApiException("Invalid student data");
        }

        // ✅ Year validation
        if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Invalid year");
        }

        // ✅ Unique roll number
        Optional<Student> existing =
                studentRepository.findByRollNumber(student.getRollNumber());

        if (existing.isPresent()) {
            throw new ApiException("Student already exists");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
