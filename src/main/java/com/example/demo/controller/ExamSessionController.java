package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    public ExamSession save(@RequestBody ExamSession s) {
        return service.save(s);
    }

    @GetMapping("/{id}")
    public ExamSession get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<ExamSession> getAll() {
        return service.getAll();
    }

    @PostMapping("/{sessionId}/students/{studentId}")
    public void add(@PathVariable Long sessionId, @PathVariable Long studentId) {
        service.addStudent(sessionId, studentId);
    }
}
 