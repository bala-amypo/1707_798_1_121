package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;

@RestController
@RequestMapping("/api/rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return examRoomService.addRoom(room);
    }

    @GetMapping("/{id}")
    public ExamRoom getRoom(@PathVariable Long id) {
        return examRoomService.getRoomById(id);
    }

    @GetMapping
    public List<ExamRoom> getAllRooms() {
        return examRoomService.getAllRooms();
    }

    @GetMapping("/{roomId}/available-seats")
    public Integer availableSeats(@PathVariable Long roomId) {
        return examRoomService.getAvailableSeats(roomId);
    }
}
