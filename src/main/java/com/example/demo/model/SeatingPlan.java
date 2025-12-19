package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_session_id", nullable = false)
    private ExamSession examSession;

    @ManyToOne
    @JoinColumn(name = "exam_room_id", nullable = false)
    private ExamRoom examRoom;

    @Lob
    @Column(nullable = false)
    private String seatAllocation;

    private LocalDateTime generatedAt;

    // ---------- Constructors ----------
    public SeatingPlan() {}

    public SeatingPlan(ExamSession examSession, ExamRoom examRoom, String seatAllocation) {
        this.examSession = examSession;
        this.examRoom = examRoom;
        this.seatAllocation = seatAllocation;
    }

    // ---------- Auto timestamp ----------
    @PrePersist
    public void setGeneratedAt() {
        this.generatedAt = LocalDateTime.now();
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }

    public ExamSession getExamSession() { return examSession; }
    public void setExamSession(ExamSession examSession) { this.examSession = examSession; }

    public ExamRoom getExamRoom() { return examRoom; }
    public void setExamRoom(ExamRoom examRoom) { this.examRoom = examRoom; }

    public String getSeatAllocation() { return seatAllocation; }
    public void setSeatAllocation(String seatAllocation) { this.seatAllocation = seatAllocation; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
}
