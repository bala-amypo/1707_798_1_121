package com.example.demo.service;

import java.util.List;
import com.example.demo.model.SeatingPlan;

public interface SeatingPlanService {

    SeatingPlan generateSeatingPlan(Long sessionId);

    SeatingPlan getPlanById(Long id);

    List<SeatingPlan> getPlansBySession(Long sessionId);

    String getSeatByRollNumber(Long planId, String rollNumber);
}
