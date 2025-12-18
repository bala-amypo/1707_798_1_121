package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    @Autowired
    private ExamRoomService examRoomService;

    @PostMapping("/add")
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return examRoomService.addRoom(room);
    }

    @GetMapping("/all")
    public List<ExamRoom> getAllRooms() {
        return examRoomService.getAllRooms();
    }
}
