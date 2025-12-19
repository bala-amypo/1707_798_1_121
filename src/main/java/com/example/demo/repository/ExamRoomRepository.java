package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExamRoom;

public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {

    Optional<ExamRoom> findByRoomNumber(String roomNumber);

    List<ExamRoom> findByCapacityGreaterThanEqual(Integer capacity);
}
