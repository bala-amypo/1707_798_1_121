package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.List;

public interface StudentService {
    Student save(Student student);
    Student get(Long id);
    List<Student> getAll();
}
