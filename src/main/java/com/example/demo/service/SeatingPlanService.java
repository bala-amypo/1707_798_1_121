package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan generatePlan(SeatingPlan plan);
    SeatingPlan getPlan(Long id);
    List<SeatingPlan> getPlansBySession(Long sessionId);
    List<SeatingPlan> getAllPlans();
}
