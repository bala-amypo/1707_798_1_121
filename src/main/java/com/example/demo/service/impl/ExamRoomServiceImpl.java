package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    // ✅ Constructor injection only
    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRows() == null || room.getColumns() == null
                || room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid rows or columns");
        }

        Optional<ExamRoom> existing =
                examRoomRepository.findByRoomNumber(room.getRoomNumber());

        if (existing.isPresent()) {
            throw new ApiException("Room already exists");
        }

        // ✅ Capacity enforcement
        room.setCapacity(room.getRows() * room.getColumns());

        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
