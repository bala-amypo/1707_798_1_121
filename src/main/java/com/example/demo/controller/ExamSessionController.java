package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;

@RestController
@RequestMapping("/exam-sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    public ExamSession create(@RequestBody ExamSession examSession) {
        return service.save(examSession);
    }

    @GetMapping("/{id}")
    public ExamSession getById(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<ExamSession> getAll() {
        return service.getAll();
    }
}
