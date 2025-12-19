package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.service.ExamSessionService;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    public ExamSession create(@RequestBody ExamSession session) {
        return examSessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public ExamSession get(@PathVariable Long id) {
        return examSessionService.getSessionById(id);
    }

    @GetMapping
    public List<ExamSession> getAll() {
        return examSessionService.getAllSessions();
    }

    @PostMapping("/{sessionId}/students/{studentId}")
    public ExamSession addStudent(@PathVariable Long sessionId,
                                  @PathVariable Long studentId) {
        return examSessionService.addStudentToSession(sessionId, studentId);
    }

    @GetMapping("/{sessionId}/students")
    public Set<Student> getStudents(@PathVariable Long sessionId) {
        return examSessionService.getStudentsForSession(sessionId);
    }
}
