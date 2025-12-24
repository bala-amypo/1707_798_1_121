package com.example.demo.service.impl;

import com.example.demo.model.SeatingPlan;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo, SeatingPlanRepository planRepo, ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId).orElseThrow(() -> new ApiException("Session not found"));
        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) throw new ApiException("No rooms available");

        // pick first room with sufficient capacity
        ExamRoom chosen = rooms.stream()
                .filter(r -> r.getCapacity() >= session.getStudents().size())
                .findFirst()
                .orElseThrow(() -> new ApiException("No room large enough"));

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(chosen);
        plan.setArrangementJson("{}"); // simple placeholder
        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id).orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
