package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "exam_rooms",
    uniqueConstraints = @UniqueConstraint(columnNames = "room_number")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Column(name = "room_rows")
    private int rows;

    @Column(name = "room_columns")
    private int columns;

    private int capacity;
}
