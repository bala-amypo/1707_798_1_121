package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generateSeatingPlan(Long examSessionId) {

        ExamSession session = examSessionRepository.findById(examSessionId)
                .orElseThrow(() ->
                        new ApiException("session not found"));

        int studentCount = session.getStudents().size();

        List<ExamRoom> rooms =
                examRoomRepository.findByCapacityGreaterThanEqual(studentCount);

        if (rooms.isEmpty()) {
            throw new ApiException("no room available");
        }

        ExamRoom room = rooms.get(0);

        Map<String, String> allocation = new LinkedHashMap<>();
        int seat = 1;

        for (Student s : session.getStudents()) {
            allocation.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            String json =
                    new ObjectMapper().writeValueAsString(allocation);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);
            plan.setGeneratedAt(LocalDateTime.now());

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            throw new ApiException("invalid seating json");
        }
    }

    @Override
    public SeatingPlan getPlanById(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException("plan not found"));
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
                new ObjectMapper().readValue(
                    plan.getArrangementJson(), Map.class);

            return map.entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(rollNumber))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            throw new ApiException("invalid seating json");
        }
    }
}
