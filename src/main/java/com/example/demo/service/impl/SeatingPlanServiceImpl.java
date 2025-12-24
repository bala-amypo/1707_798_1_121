package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.example.demo.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo,
                                  SeatingPlanRepository planRepo,
                                  ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            throw new ApiException("No rooms available for seating plan");
        }

        Set<Student> students = session.getStudents();
        ExamRoom selectedRoom = null;
        for (ExamRoom r : rooms) {
            if (r.getCapacity() >= students.size()) {
                selectedRoom = r;
                break;
            }
        }

        if (selectedRoom == null) {
            throw new ApiException("No room with sufficient capacity");
        }

        // generate simple JSON arrangement
        Map<String, Object> arrangement = new HashMap<>();
        List<Map<String, Object>> seats = new ArrayList<>();
        int seatIndex = 0;
        for (Student s : students) {
            Map<String, Object> seat = new HashMap<>();
            seat.put("studentId", s.getId());
            seat.put("rollNumber", s.getRollNumber());
            seat.put("row", seatIndex / selectedRoom.getColumns() + 1);
            seat.put("col", seatIndex % selectedRoom.getColumns() + 1);
            seats.add(seat);
            seatIndex++;
        }
        arrangement.put("roomNumber", selectedRoom.getRoomNumber());
        arrangement.put("seats", seats);

        String json;
        try {
            json = mapper.writeValueAsString(arrangement);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize arrangement JSON");
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(selectedRoom);
        plan.setArrangementJson(json);
        plan.setGeneratedAt(LocalDateTime.now());

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
