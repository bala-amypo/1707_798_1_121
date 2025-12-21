package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    public ExamRoom save(ExamRoom r) {
        r.setCapacity(r.getRows() * r.getColumns());
        return repo.save(r);
    }

    public ExamRoom get(Long id) {
        return repo.findById(id).orElseThrow(() -> new ApiException("Room not found"));
    }

    public List<ExamRoom> getAll() {
        return repo.findAll();
    }

    public int availableSeats(Long roomId) {
        return get(roomId).getCapacity();
    }
}
