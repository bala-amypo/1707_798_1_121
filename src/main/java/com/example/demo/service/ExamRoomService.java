package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import com.example.demo.exception.ApiException;
import java.util.List;

public interface ExamRoomService {
    ExamRoom addRoom(ExamRoom room) throws ApiException;
    List<ExamRoom> getAllRooms();
}
