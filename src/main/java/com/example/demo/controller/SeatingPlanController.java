package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;

@RestController
@RequestMapping("/api/seating-plans")
public class SeatingPlanController {

    private final SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    @PostMapping("/generate")
    public SeatingPlan generate(@RequestParam Long examSessionId) {
        return seatingPlanService.generateSeatingPlan(examSessionId);
    }

    @GetMapping("/{id}")
    public SeatingPlan getPlan(@PathVariable Long id) {
        return seatingPlanService.getPlanById(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> getBySession(@PathVariable Long sessionId) {
        return seatingPlanService.getPlansBySession(sessionId);
    }

    @GetMapping("/{planId}/seat/{rollNumber}")
    public String getSeat(@PathVariable Long planId,
                          @PathVariable String rollNumber) {
        return seatingPlanService.getSeatByRollNumber(planId, rollNumber);
    }
}
