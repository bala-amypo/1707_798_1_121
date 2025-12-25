package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionServiceImpl(ExamSessionRepository repo, StudentRepository ignored) {
        this.repo = repo;
    }

    @Override
    public ExamSession createSession(ExamSession s) {
        if (s.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("past");
        }
        if (s.getStudents() == null || s.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student");
        }
        return repo.save(s);
    }

    @Override
    public ExamSession getSession(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
