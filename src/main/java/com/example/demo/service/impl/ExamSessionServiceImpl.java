package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(ExamSessionRepository sessionRepo,
                                  StudentRepository studentRepo) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    public ExamSession save(ExamSession s) {
        return sessionRepo.save(s);
    }

    public ExamSession get(Long id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }

    public List<ExamSession> getAll() {
        return sessionRepo.findAll();
    }

    public void addStudent(Long sessionId, Long studentId) {
        ExamSession session = get(sessionId);
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ApiException("Student not found"));
        session.getStudents().add(student);
        sessionRepo.save(session);
    }
}
