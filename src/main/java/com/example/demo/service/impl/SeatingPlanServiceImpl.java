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
        plan.setExamRoom(room);
        plan.setSeatAllocation(arrangementJson);

        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlanById(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() -> new ApiException("Seating plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }

    @Override
    public String getSeatByRollNumber(Long planId, String rollNumber) {

        SeatingPlan plan = getPlanById(planId);

        try {
            Map<String, String> allocation =
                    objectMapper.readValue(
                            plan.getSeatAllocation(),
                            new TypeReference<Map<String, String>>() {});

            String seat = allocation.get(rollNumber);

            if (seat == null) {
                throw new ApiException("Seat not found for roll number");
            }

            return seat;

        } catch (Exception e) {
            throw new ApiException("Invalid seating plan data");
        }
    }
}
