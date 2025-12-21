package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo,
                                  SeatingPlanRepository planRepo,
                                  ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    public SeatingPlan generate(Long sessionId, Long roomId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));
        ExamRoom room = roomRepo.findById(roomId)
                .orElseThrow(() -> new ApiException("Room not found"));

        if (session.getStudents().size() > room.getCapacity())
            throw new ApiException("Not enough seats");

        Map<String, String> allocation = new LinkedHashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            allocation.put(s.getRollNumber(), "Seat-" + seat++);
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setExamRoom(room);
        plan.setSeatAllocation(allocation.toString());
        plan.setGeneratedAt(LocalDateTime.now());

        return planRepo.save(plan);
    }

    public SeatingPlan get(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    public List<SeatingPlan> getBySession(Long sessionId) {
        ExamSession s = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));
        return planRepo.findByExamSession(s);
    }

    public String getSeat(Long planId, String roll) {
        SeatingPlan p = get(planId);
        if (!p.getSeatAllocation().contains(roll))
            throw new ApiException("Seat not found");
        return p.getSeatAllocation();
    }
}
