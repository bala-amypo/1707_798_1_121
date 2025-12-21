package com.example.demo.service;

import com.example.demo.model.ExamSession;
import java.util.List;

public interface ExamSessionService {
    ExamSession save(ExamSession session);
    ExamSession get(Long id);
    List<ExamSession> getAll();
    void addStudent(Long sessionId, Long studentId);
}
