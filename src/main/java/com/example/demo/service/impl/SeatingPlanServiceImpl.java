package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int count = session.getStudents().size();

        ExamRoom room = roomRepo.findAll().stream()
                .filter(r -> r.getCapacity() >= count)
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        Map<String, String> map = new LinkedHashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            map.put("seat-" + seat++, s.getRollNumber());
        }

        String json;
        try {
            json = new ObjectMapper().writeValueAsString(map);
        } catch (Exception e) {
            throw new ApiException("json error");
        }

        SeatingPlan plan = SeatingPlan.builder()
                .examSession(session)
                .room(room)
                .arrangementJson(json)
                .build();

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
