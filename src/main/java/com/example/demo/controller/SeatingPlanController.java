package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/seating")
public class SeatingPlanController {

    @Autowired
    private SeatingPlanService seatingPlanService;

    @PostMapping("/generate")
    public SeatingPlan generatePlan(@RequestBody SeatingPlan plan) {
        return seatingPlanService.generatePlan(plan);
    }

    @GetMapping("/{id}")
    public SeatingPlan getPlan(@PathVariable Long id) {
        return seatingPlanService.getPlan(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> getPlansBySession(@PathVariable Long sessionId) {
        return seatingPlanService.getPlansBySession(sessionId);
    }

    @GetMapping("/all")
    public List<SeatingPlan> getAllPlans() {
        return seatingPlanService.getAllPlans();
    }
}
