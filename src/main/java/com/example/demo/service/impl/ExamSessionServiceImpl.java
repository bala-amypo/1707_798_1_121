package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final List<ExamSession> sessions = new ArrayList<>();

    @Override
    public ExamSession saveSession(ExamSession session) {
        sessions.add(session);
        return session;
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return sessions;
    }
}
