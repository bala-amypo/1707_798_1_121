package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    // ✅ Constructor injection only
    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository,
                                  StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date is in the past");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("At least 1 student required");
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));
    }
}
