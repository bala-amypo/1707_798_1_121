// ExamSessionController.java
package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class ExamSessionController {
    private final ExamSessionService sessionService;

    @PostMapping
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession session) {
        return ResponseEntity.ok(sessionService.createSession(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSession(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@PathVariable String date) {
        return ResponseEntity.ok(sessionService.getSessionsByDate(LocalDate.parse(date)));
    }
}