package com.example.demo.service.impl;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import com.example.demo.exception.ApiException;

import java.util.List;
import java.util.Optional;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository roomRepo;

    public ExamRoomServiceImpl(ExamRoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        if (room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Rows and Columns must be positive");
        }

        Optional<ExamRoom> existing = roomRepo.findByRoomNumber(room.getRoomNumber());
        if (existing.isPresent()) {
            throw new ApiException("Room already exists");
        }

        room.ensureCapacityMatches(); // method sets capacity = rows * columns
        return roomRepo.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return roomRepo.findAll();
    }
}
