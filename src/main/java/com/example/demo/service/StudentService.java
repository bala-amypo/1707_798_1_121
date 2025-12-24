package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.exception.ApiException;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws ApiException;
    List<Student> getAllStudents();
}
