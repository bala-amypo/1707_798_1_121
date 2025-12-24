package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamRoomServiceImpl implements ExamRoomService {
    private final ExamRoomRepository roomRepository;

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        validateRoom(room);
        
        if (roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room with room number already exists");
        }
        
        room.ensureCapacityMatches();
        return roomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public ExamRoom getRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found with id: " + id));
    }

    private void validateRoom(ExamRoom room) {
        if (room.getRoomNumber() == null || room.getRoomNumber().isEmpty()) {
            throw new ApiException("Room number is required");
        }
        
        if (room.getRows() != null && room.getRows() <= 0) {
            throw new ApiException("Rows must be positive");
        }
        
        if (room.getColumns() != null && room.getColumns() <= 0) {
            throw new ApiException("Columns must be positive");
        }
    }
}