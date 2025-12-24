package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import com.example.demo.exception.ApiException;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan generatePlan(Long sessionId) throws ApiException;
    SeatingPlan getPlan(Long planId) throws ApiException;
    List<SeatingPlan> getPlansBySession(Long sessionId);
}
