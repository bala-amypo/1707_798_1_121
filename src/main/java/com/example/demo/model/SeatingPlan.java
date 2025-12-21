package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom examRoom;

    @Lob
    private String seatAllocation;

    private LocalDateTime generatedAt;
}
