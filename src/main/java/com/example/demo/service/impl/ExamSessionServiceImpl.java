package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(ExamSessionRepository sessionRepo, StudentRepository studentRepo) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession s) {
        if (s.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }
        if (s.getStudents() == null || s.getStudents().isEmpty()) {
            throw new ApiException("Session must have at least 1 student");
        }
        return sessionRepo.save(s);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepo.findById(id).orElseThrow(() -> new ApiException("Session not found"));
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return sessionRepo.findAll();
    }
}
