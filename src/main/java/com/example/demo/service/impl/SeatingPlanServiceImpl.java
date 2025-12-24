// SeatingPlanServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatingPlanServiceImpl implements SeatingPlanService {
    private final ExamSessionRepository sessionRepository;
    private final SeatingPlanRepository planRepository;
    private final ExamRoomRepository roomRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found with id: " + sessionId));
        
        List<ExamRoom> allRooms = roomRepository.findAll();
        if (allRooms.isEmpty()) {
            throw new ApiException("No rooms available");
        }
        
        // Find suitable room
        ExamRoom suitableRoom = null;
        for (ExamRoom room : allRooms) {
            if (room.getCapacity() >= session.getStudents().size()) {
                suitableRoom = room;
                break;
            }
        }
        
        if (suitableRoom == null) {
            throw new ApiException("No room has sufficient capacity for " + 
                                 session.getStudents().size() + " students");
        }
        
        // Generate arrangement
        String arrangementJson = generateArrangementJson(session, suitableRoom);
        
        SeatingPlan plan = SeatingPlan.builder()
                .examSession(session)
                .room(suitableRoom)
                .arrangementJson(arrangementJson)
                .generatedAt(LocalDateTime.now())
                .build();
        
        return planRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found with id: " + id));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepository.findByExamSessionId(sessionId);
    }

    private String generateArrangementJson(ExamSession session, ExamRoom room) {
        try {
            ObjectNode root = objectMapper.createObjectNode();
            root.put("sessionId", session.getId());
            root.put("courseCode", session.getCourseCode());
            root.put("roomId", room.getId());
            root.put("roomNumber", room.getRoomNumber());
            
            ArrayNode arrangement = objectMapper.createArrayNode();
            Set<Student> students = session.getStudents();
            int seatIndex = 0;
            
            for (Student student : students) {
                ObjectNode seat = objectMapper.createObjectNode();
                seat.put("studentId", student.getId());
                seat.put("rollNumber", student.getRollNumber());
                seat.put("name", student.getName());
                
                int row = seatIndex / room.getColumns() + 1;
                int col = seatIndex % room.getColumns() + 1;
                seat.put("row", row);
                seat.put("column", col);
                
                arrangement.add(seat);
                seatIndex++;
                
                if (seatIndex >= room.getCapacity()) {
                    break;
                }
            }
            
            root.set("seats", arrangement);
            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            throw new ApiException("Failed to generate arrangement: " + e.getMessage());
        }
    }
}