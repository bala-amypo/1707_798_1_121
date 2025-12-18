package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan generatePlan(SeatingPlan plan);
    List<SeatingPlan> getAllPlans();
}
