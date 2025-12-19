package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SeatingPlan;

public interface SeatingPlanService {

    SeatingPlan generatePlan(Long examSessionId, Long roomId, String arrangementJson);

    SeatingPlan getPlanById(Long id);

    List<SeatingPlan> getPlansBySession(Long sessionId);

    String getSeatByRollNumber(Long planId, String rollNumber);
}
