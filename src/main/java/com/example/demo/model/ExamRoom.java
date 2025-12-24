package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private Integer rows;
    private Integer columns;
    private Integer capacity;

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        }
    }
}
