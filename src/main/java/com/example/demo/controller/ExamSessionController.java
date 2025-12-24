package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import com.example.demo.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {
    
    private final ExamSessionService sessionService;
    
    @Autowired
    public ExamSessionController(ExamSessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExamSession session) {
        try {
            ExamSession created = sessionService.createSession(session);
            return ResponseEntity.ok(created);
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            ExamSession session = sessionService.getSession(id);
            return ResponseEntity.ok(session);
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ExamSession>> getAll() {
        List<ExamSession> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExamSession session) {
        try {
            ExamSession updated = sessionService.updateSession(id, session);
            return ResponseEntity.ok(updated);
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}