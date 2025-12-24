package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService roomService;

    public ExamRoomController(ExamRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom room) {
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
}
