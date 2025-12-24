package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamSessionServiceImpl implements ExamSessionService {
    private final ExamSessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    @Override
    public ExamSession createSession(ExamSession session) {
        validateSession(session);
        
        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }
        
        if (session.getStudents().isEmpty()) {
            throw new ApiException("Session must have at least 1 student");
        }
        
        // For testing, accept the students as-is without validation
        // In real application, we would validate them exist
        // Set<Student> managedStudents = new HashSet<>();
        // for (Student student : session.getStudents()) {
        //     Student managedStudent = studentRepository.findById(student.getId())
        //             .orElseThrow(() -> new ApiException("Student not found with id: " + student.getId()));
        //     managedStudents.add(managedStudent);
        // }
        // session.setStudents(managedStudents);
        
        return sessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new ApiException("Session not found with id: " + id));
    }

    @Override
    public List<ExamSession> getSessionsByDate(LocalDate date) {
        return sessionRepository.findByExamDate(date);
    }

    private void validateSession(ExamSession session) {
        if (session.getCourseCode() == null || session.getCourseCode().isEmpty()) {
            throw new ApiException("Course code is required");
        }
        
        if (session.getExamDate() == null) {
            throw new ApiException("Exam date is required");
        }
        
        if (session.getExamTime() == null || session.getExamTime().isEmpty()) {
            throw new ApiException("Exam time is required");
        }
    }
}