package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionServiceImpl(ExamSessionRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamSession save(ExamSession session) {
        return repo.save(session);
    }

    @Override
    public List<ExamSession> findAll() {
        return repo.findAll();
    }
}
