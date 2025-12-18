package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Tag(name = "Exam Rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    @Operation(summary = "Add exam room")
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return examRoomService.addRoom(room);
    }

    @GetMapping
    @Operation(summary = "Get all rooms")
    public List<ExamRoom> getAllRooms() {
        return examRoomService.getAllRooms();
    }
}
