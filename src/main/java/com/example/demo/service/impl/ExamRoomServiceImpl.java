package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

import java.util.List;

public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom r) {
        if (r.getRows() <= 0 || r.getColumns() <= 0) {
            throw new ApiException("invalid");
        }
        if (repo.findByRoomNumber(r.getRoomNumber()).isPresent()) {
            throw new ApiException("exists");
        }
        r.ensureCapacityMatches();
        return repo.save(r);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }
}
