package com.example.demo.service.impl;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository roomRepo;

    public ExamRoomServiceImpl(ExamRoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom r) {
        if (r.getRows() == null || r.getColumns() == null || r.getRows() <= 0 || r.getColumns() <= 0) {
            throw new ApiException("Invalid room dimensions");
        }
        roomRepo.findByRoomNumber(r.getRoomNumber()).ifPresent(existing -> {
            throw new ApiException("Room with this number already exists");
        });
        r.ensureCapacityMatches();
        return roomRepo.save(r);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return roomRepo.findAll();
    }
}
