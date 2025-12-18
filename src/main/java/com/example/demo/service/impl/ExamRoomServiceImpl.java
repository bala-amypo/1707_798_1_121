package com.example.demo.service.impl;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    @Autowired
    private ExamRoomRepository repository;

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        return repository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repository.findAll();
    }
}
