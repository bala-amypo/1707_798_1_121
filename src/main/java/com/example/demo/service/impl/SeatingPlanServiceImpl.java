package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamSessionRepository examSessionRepository;
    private final ExamRoomRepository examRoomRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

        // ❌ DO NOT set generatedAt manually
        // ✔ @PrePersist handles it

        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlanByExamSessionId(Long examSessionId) {

        List<SeatingPlan> plans =
                seatingPlanRepository.findByExamSessionId(examSessionId);

        if (plans == null || plans.isEmpty()) {
            throw new ApiException("Seating plan not found");
        }

        return plans.get(0);
    }

    @Override
    public String getSeatByRollNumber(Long examSessionId, String rollNumber) {

        SeatingPlan plan = getPlanByExamSessionId(examSessionId);

        try {
            Map<String, String> seatingMap =
                    objectMapper.readValue(
                            plan.getArrangementJson(),
                            new TypeReference<Map<String, String>>() {});

            return seatingMap.get(rollNumber);

        } catch (Exception e) {
            throw new ApiException("Error parsing seating arrangement");
        }
    }
}
