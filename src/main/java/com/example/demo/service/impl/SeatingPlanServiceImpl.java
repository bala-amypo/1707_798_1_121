package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
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
                .orElseThrow(() -> new ApiException("Session not found"));

        int studentCount = session.getStudents().size();

        List<ExamRoom> rooms = examRoomRepository.findAll();
        ExamRoom selectedRoom = rooms.stream()
                .filter(r -> r.getCapacity() >= studentCount)
                .findFirst()
                .orElseThrow(() -> new ApiException("No room available"));

        Map<String, String> allocation = new HashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            allocation.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(allocation);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setExamRoom(selectedRoom);
            plan.setSeatAllocation(json);
            plan.setGeneratedAt(LocalDateTime.now());

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            throw new ApiException("Invalid seating JSON");
        }
    }

    @Override
    public SeatingPlan getPlanById(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }

    @Override
    public String getSeatByRollNumber(Long planId, String rollNumber) {
        SeatingPlan plan = getPlanById(planId);
        return plan.getSeatAllocation();
    }
}
