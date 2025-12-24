package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository repo;

    public SeatingPlanServiceImpl(SeatingPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public SeatingPlan save(SeatingPlan plan) {
        return repo.save(plan);
    }

    @Override
    public List<SeatingPlan> findAll() {
        return repo.findAll();
    }
}
