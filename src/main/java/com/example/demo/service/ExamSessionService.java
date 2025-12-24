package com.example.demo.service;

import com.example.demo.entity.ExamSession;
import java.util.List;

public interface ExamSessionService {

    ExamSession save(ExamSession session);

    List<ExamSession> findAll();
}
