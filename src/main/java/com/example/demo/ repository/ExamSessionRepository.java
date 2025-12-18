package com.example.demo.repository;

import com.example.demo.model.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    Optional<ExamSession> findByExamDate(LocalDate date);
}
