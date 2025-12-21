package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import java.util.List;

public interface ExamRoomService {
    ExamRoom save(ExamRoom room);
    ExamRoom get(Long id);
    List<ExamRoom> getAll();
    int availableSeats(Long roomId);
}
    