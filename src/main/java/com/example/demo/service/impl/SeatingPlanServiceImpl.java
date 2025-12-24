package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final ObjectMapper mapper = new ObjectMapper();

    // ✅ Constructor injection only
    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        int studentCount = session.getStudents().size();

        // ✅ Find first room that fits
        ExamRoom selectedRoom = examRoomRepository.findAll().stream()
                .filter(r -> r.getCapacity() >= studentCount)
                .findFirst()
                .orElseThrow(() -> new ApiException("No room available"));

        try {
            Map<String, String> arrangement = new HashMap<>();
            int seat = 1;

            for (Student s : session.getStudents()) {
                arrangement.put("Seat-" + seat, s.getRollNumber());
                seat++;
            }

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(selectedRoom);
            plan.setArrangementJson(mapper.writeValueAsString(arrangement));
            plan.setGeneratedAt(LocalDateTime.now());

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            throw new ApiException("Failed to generate seating plan");
        }
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
