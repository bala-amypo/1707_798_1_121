package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan generate(Long sessionId, Long roomId);
    SeatingPlan get(Long id);
    List<SeatingPlan> getBySession(Long sessionId);
    String getSeat(Long planId, String rollNumber);
}
