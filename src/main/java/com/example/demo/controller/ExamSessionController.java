package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam-session")
public class ExamSessionController {

    @Autowired
    private ExamSessionService examSessionService;

    @PostMapping("/add")
    public ExamSession addSession(@RequestBody ExamSession session) {
        return examSessionService.saveSession(session);
    }

    @GetMapping("/all")
    public List<ExamSession> getAllSessions() {
        return examSessionService.getAllSessions();
    }
}
