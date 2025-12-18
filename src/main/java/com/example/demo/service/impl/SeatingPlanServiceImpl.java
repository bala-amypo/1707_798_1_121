package com.example.demo.service.impl;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    @Autowired
    private SeatingPlanRepository repository;

    @Override
    public SeatingPlan generatePlan(SeatingPlan plan) {
        return repository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return repository.findByExamSessionId(sessionId);
    }

    @Override
    public List<SeatingPlan> getAllPlans() {
        return repository.findAll();
    }
}
