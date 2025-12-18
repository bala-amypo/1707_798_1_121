package com.example.demo.service;

import com.example.demo.model.ExamSession;
import java.util.List;

public interface ExamSessionService {
    ExamSession saveSession(ExamSession session);
    List<ExamSession> getAllSessions();
}
