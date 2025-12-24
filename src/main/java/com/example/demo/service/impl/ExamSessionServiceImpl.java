package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import com.example.demo.exception.ApiException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {
    
    private final ExamSessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    
    public ExamSessionServiceImpl(ExamSessionRepository sessionRepository, 
                                  StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }
    
    @Override
    public ExamSession createSession(ExamSession session) {
        // Validate exam date is not in the past
        if (session.getExamDate() != null && session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }
        
        // Validate at least one student
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Session must have at least 1 student");
        }
        
        // Ensure all students exist in database
        Set<Student> managedStudents = new HashSet<>();
        for (Student student : session.getStudents()) {
            if (student.getId() != null) {
                Student existingStudent = studentRepository.findById(student.getId())
                    .orElseThrow(() -> new ApiException("Student not found with id: " + student.getId()));
                managedStudents.add(existingStudent);
            } else if (student.getRollNumber() != null) {
                Student existingStudent = studentRepository.findByRollNumber(student.getRollNumber())
                    .orElseThrow(() -> new ApiException("Student not found with roll number: " + student.getRollNumber()));
                managedStudents.add(existingStudent);
            } else {
                throw new ApiException("Student must have id or roll number");
            }
        }
        
        session.setStudents(managedStudents);
        return sessionRepository.save(session);
    }
    
    @Override
    public ExamSession getSession(Long id) {
        return sessionRepository.findById(id)
            .orElseThrow(() -> new ApiException("Session not found with id: " + id));
    }
    
    @Override
    public List<ExamSession> getAllSessions() {
        return sessionRepository.findAll();
    }
    
    @Override
    public ExamSession updateSession(Long id, ExamSession sessionDetails) {
        ExamSession existingSession = getSession(id);
        
        if (sessionDetails.getCourseCode() != null) {
            existingSession.setCourseCode(sessionDetails.getCourseCode());
        }
        
        if (sessionDetails.getExamDate() != null) {
            if (sessionDetails.getExamDate().isBefore(LocalDate.now())) {
                throw new ApiException("Exam date cannot be in the past");
            }
            existingSession.setExamDate(sessionDetails.getExamDate());
        }
        
        if (sessionDetails.getExamTime() != null) {
            existingSession.setExamTime(sessionDetails.getExamTime());
        }
        
        if (sessionDetails.getStudents() != null && !sessionDetails.getStudents().isEmpty()) {
            Set<Student> managedStudents = new HashSet<>();
            for (Student student : sessionDetails.getStudents()) {
                if (student.getId() != null) {
                    Student existingStudent = studentRepository.findById(student.getId())
                        .orElseThrow(() -> new ApiException("Student not found with id: " + student.getId()));
                    managedStudents.add(existingStudent);
                }
            }
            existingSession.setStudents(managedStudents);
        }
        
        return sessionRepository.save(existingSession);
    }
}