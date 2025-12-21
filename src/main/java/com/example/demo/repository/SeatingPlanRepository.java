package com.example.demo.repository;

import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {

    Optional<SeatingPlan> findByExamSessionAndExamRoom(
            ExamSession examSession,
            ExamRoom examRoom
    );

    // 🔥 THIS IS WHAT WAS MISSING
    List<SeatingPlan> findByExamSession(ExamSession examSession);
}
