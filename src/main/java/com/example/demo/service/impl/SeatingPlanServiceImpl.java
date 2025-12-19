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
    private final ObjectMapper mapper = new ObjectMapper();

    public SeatingPlanServiceImpl(
            SeatingPlanRepository seatingPlanRepository,
            ExamSessionRepository examSessionRepository,
            ExamRoomRepository examRoomRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.examSessionRepository = examSessionRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generateSeatingPlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Exam session not found"));

        ExamRoom room = examRoomRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiException("No exam room available"));

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setExamRoom(room);
        plan.setSeatAllocation("{}"); // default empty JSON

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
            Map<String, String> map =
                    mapper.readValue(
                            plan.getSeatAllocation(),
                            new TypeReference<Map<String, String>>() {});

            String seat = map.get(rollNumber);

            if (seat == null) {
                throw new ApiException("Seat not found");
            }

            return seat;

        } catch (Exception e) {
            throw new ApiException("Invalid seating data");
        }
    }
}
