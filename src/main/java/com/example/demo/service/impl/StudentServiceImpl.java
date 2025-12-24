package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        validateStudent(student);
        
        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Student with roll number already exists");
        }
        
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found with id: " + id));
    }

    private void validateStudent(Student student) {
        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            throw new ApiException("Roll number is required");
        }
        
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new ApiException("Name is required");
        }
        
        if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Year must be between 1 and 5");
        }
    }
}