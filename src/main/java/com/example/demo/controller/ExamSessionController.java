package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
@Tag(name = "Exam Sessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    @Operation(summary = "Create exam session")
    public ExamSession createSession(@RequestBody ExamSession session) {
        return examSessionService.createSession(session);
    }

    @GetMapping("/{sessionId}")
    @Operation(summary = "Get exam session")
    public ExamSession getSession(@PathVariable Long sessionId) {
        return examSessionService.getSession(sessionId);
    }
}
