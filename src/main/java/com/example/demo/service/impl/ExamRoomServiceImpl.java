package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid room layout");
        }

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room already exists");
        }

        room.ensureCapacityMatches();
        return examRoomRepository.save(room);
    }

    @Override
    public ExamRoom getRoomById(Long id) {
        return examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }

    @Override
    public Integer getAvailableSeats(Long roomId) {
        ExamRoom room = getRoomById(roomId);
        return room.getCapacity();
    }
}
