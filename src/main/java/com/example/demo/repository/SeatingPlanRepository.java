package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;

public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {

    Optional<SeatingPlan> findByExamSessionAndExamRoom(
            ExamSession examSession,
            ExamRoom examRoom
    );

    List<SeatingPlan> findByExamSession(ExamSession examSession);
}
