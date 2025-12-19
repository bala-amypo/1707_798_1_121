package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;

public interface ExamSessionService {

    ExamSession createSession(ExamSession session);

    ExamSession getSessionById(Long id);

    List<ExamSession> getAllSessions();

    ExamSession addStudentToSession(Long sessionId, Long studentId);

    Set<Student> getStudentsForSession(Long sessionId);
}
