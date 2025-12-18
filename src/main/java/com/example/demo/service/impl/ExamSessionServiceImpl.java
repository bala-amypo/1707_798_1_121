package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    @Autowired
    private ExamSessionRepository examSessionRepository;

    @Override
    public ExamSession addSession(ExamSession session) {
        return examSessionRepository.save(session);
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return examSessionRepository.findAll();
    }

    @Override
    public ExamSession getSessionById(Long id) {
        Optional<ExamSession> session = examSessionRepository.findById(id);
        return session.orElse(null);
    }
}
