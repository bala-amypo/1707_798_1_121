package com.example.demo.service;

import com.example.demo.model.ExamSession;
import java.util.List;

public interface ExamSessionService {
    ExamSession addSession(ExamSession session);
    List<ExamSession> getAllSessions();
    ExamSession getSessionById(Long id);
}
