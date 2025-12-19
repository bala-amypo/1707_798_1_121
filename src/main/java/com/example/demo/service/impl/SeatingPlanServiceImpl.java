package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamSessionRepository examSessionRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(
            SeatingPlanRepository seatingPlanRepository,
            ExamSessionRepository examSessionRepository,
            ExamRoomRepository examRoomRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.examSessionRepository = examSessionRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long examSessionId, Long roomId, String arrangementJson) {

        ExamSession session = examSessionRepository.findById(examSessionId)
                .orElseThrow(() -> new ApiException("Exam session not found"));

        ExamRoom room = examRoomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException("Exam room not found"));

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(room);
        plan.setArrangementJson(arrangementJson);

        // ❌ DO NOT call setGeneratedAt(LocalDateTime)
        // ✔ @PrePersist in entity will handle it

        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlanByExamSessionId(Long examSessionId) {

        Optional<SeatingPlan> plan =
                seatingPlanRepository.findByExamSessionId(examSessionId);

        return plan.orElseThrow(() ->
                new ApiException("Seating plan not found"));
    }
}
