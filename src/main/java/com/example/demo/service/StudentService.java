package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {

    Student addStudent(Student student);

    Student getStudentById(Long id);

    Student getStudentByRollNumber(String rollNumber);

    List<Student> getAllStudents();

    Student updateStudent(Long id, Student student);
}
