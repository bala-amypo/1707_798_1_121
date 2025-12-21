package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seating-plans")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public SeatingPlan generate(@RequestParam Long sessionId,
                                @RequestParam Long roomId) {
        return service.generate(sessionId, roomId);
    }

    @GetMapping("/{id}")
    public SeatingPlan get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> bySession(@PathVariable Long sessionId) {
        return service.getBySession(sessionId);
    }

    @GetMapping("/{planId}/seat/{roll}")
    public String seat(@PathVariable Long planId,
                       @PathVariable String roll) {
        return service.getSeat(planId, roll);
    }
}
