package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_rooms")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer capacity;
    private Integer rows;
    private Integer columns;

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        }
    }
}
