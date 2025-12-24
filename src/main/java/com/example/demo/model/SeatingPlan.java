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
    @JoinColumn(name = "session_id")
    private ExamSession examSession;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;
    
    @Column(columnDefinition = "TEXT")
    private String arrangementJson;
    
    private LocalDateTime generatedAt;
    
    // Constructors
    public SeatingPlan() {}
    
    public SeatingPlan(Long id, ExamSession examSession, ExamRoom room, String arrangementJson, LocalDateTime generatedAt) {
        this.id = id;
        this.examSession = examSession;
        this.room = room;
        this.arrangementJson = arrangementJson;
        this.generatedAt = generatedAt;
    }
    
    // Getters
    public Long getId() { return id; }
    public ExamSession getExamSession() { return examSession; }
    public ExamRoom getRoom() { return room; }
    public String getArrangementJson() { return arrangementJson; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setExamSession(ExamSession examSession) { this.examSession = examSession; }
    public void setRoom(ExamRoom room) { this.room = room; }
    public void setArrangementJson(String arrangementJson) { this.arrangementJson = arrangementJson; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    
    // Builder pattern
    public static SeatingPlanBuilder builder() {
        return new SeatingPlanBuilder();
    }
    
    public static class SeatingPlanBuilder {
        private Long id;
        private ExamSession examSession;
        private ExamRoom room;
        private String arrangementJson;
        private LocalDateTime generatedAt;
        
        public SeatingPlanBuilder id(Long id) { this.id = id; return this; }
        public SeatingPlanBuilder examSession(ExamSession examSession) { this.examSession = examSession; return this; }
        public SeatingPlanBuilder room(ExamRoom room) { this.room = room; return this; }
        public SeatingPlanBuilder arrangementJson(String arrangementJson) { this.arrangementJson = arrangementJson; return this; }
        public SeatingPlanBuilder generatedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; return this; }
        
        public SeatingPlan build() {
            return new SeatingPlan(id, examSession, room, arrangementJson, generatedAt);
        }
    }
}