package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ExamRoom save(@RequestBody ExamRoom r) {
        return service.save(r);
    }

    @GetMapping("/{id}")
    public ExamRoom get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<ExamRoom> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}/available-seats")
    public int seats(@PathVariable Long id) {
        return service.availableSeats(id);
    }
}
