package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository,
                                  StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("exam date is in the past");
        }

        if (session.getStudents() == null ||
            session.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student required");
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSessionById(Long id) {
        return examSessionRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException("session not found"));
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return examSessionRepository.findAll();
    }

    @Override
    public ExamSession addStudentToSession(Long sessionId, Long studentId) {
        ExamSession session = getSessionById(sessionId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ApiException("student not found"));

        if (session.getStudents() == null) {
            session.setStudents(new HashSet<>());
        }

        session.getStudents().add(student);
        return examSessionRepository.save(session);
    }

    @Override
    public Set<Student> getStudentsForSession(Long sessionId) {
        return getSessionById(sessionId).getStudents();
    }
}
