package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import java.util.List;

public interface ExamRoomService {
    ExamRoom saveRoom(ExamRoom room);
    List<ExamRoom> getAllRooms();
}
