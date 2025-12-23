package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExamSession;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {
}
