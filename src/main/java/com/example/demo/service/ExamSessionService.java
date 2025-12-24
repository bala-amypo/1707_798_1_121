package com.example.demo.service;

import com.example.demo.model.ExamSession;
import com.example.demo.exception.ApiException;
import java.util.List;

public interface ExamSessionService {
    ExamSession createSession(ExamSession session) throws ApiException;
    ExamSession getSession(Long id) throws ApiException;
    List<ExamSession> getAllSessions();
}
