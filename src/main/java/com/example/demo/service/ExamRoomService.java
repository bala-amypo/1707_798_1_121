package com.example.demo.service;

import com.example.demo.entity.ExamRoom;
import java.util.List;

public interface ExamRoomService {

    ExamRoom save(ExamRoom room);

    List<ExamRoom> findAll();
}
