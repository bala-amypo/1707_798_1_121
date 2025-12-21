package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_rooms", uniqueConstraints = @UniqueConstraint(columnNames = "roomNumber"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private Integer rows;
    private Integer columns;
    private Integer capacity;
}
