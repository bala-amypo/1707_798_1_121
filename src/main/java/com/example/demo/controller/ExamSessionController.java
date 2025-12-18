package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    @Autowired
    private ExamSessionService examSessionService;

    @PostMapping("/add")
    public ExamSession addSession(@RequestBody ExamSession session) {
        return examSessionService.addSession(session);
    }

    @GetMapping("/all")
    public List<ExamSession> getAllSessions() {
        return examSessionService.getAllSessions();
    }
}
