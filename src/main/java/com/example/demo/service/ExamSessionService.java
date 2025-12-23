package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ExamSession;

public interface ExamSessionService {

    ExamSession save(ExamSession examSession);

    ExamSession get(Long id);

    List<ExamSession> getAll();
}
