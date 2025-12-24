package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms")
public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String roomNumber;
    
    private Integer rows;
    private Integer columns;
    private Integer capacity;
    
    // Constructors
    public ExamRoom() {}
    
    public ExamRoom(Long id, String roomNumber, Integer rows, Integer columns, Integer capacity) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        this.capacity = capacity;
        ensureCapacityMatches();
    }
    
    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        } else if (capacity == null) {
            capacity = 1;
        }
    }
    
    // Getters
    public Long getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public Integer getRows() { return rows; }
    public Integer getColumns() { return columns; }
    public Integer getCapacity() { return capacity; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setRows(Integer rows) { 
        this.rows = rows; 
        ensureCapacityMatches();
    }
    public void setColumns(Integer columns) { 
        this.columns = columns; 
        ensureCapacityMatches();
    }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    
    // Builder pattern
    public static ExamRoomBuilder builder() {
        return new ExamRoomBuilder();
    }
    
    public static class ExamRoomBuilder {
        private Long id;
        private String roomNumber;
        private Integer rows;
        private Integer columns;
        private Integer capacity;
        
        public ExamRoomBuilder id(Long id) { this.id = id; return this; }
        public ExamRoomBuilder roomNumber(String roomNumber) { this.roomNumber = roomNumber; return this; }
        public ExamRoomBuilder rows(Integer rows) { this.rows = rows; return this; }
        public ExamRoomBuilder columns(Integer columns) { this.columns = columns; return this; }
        public ExamRoomBuilder capacity(Integer capacity) { this.capacity = capacity; return this; }
        
        public ExamRoom build() {
            return new ExamRoom(id, roomNumber, rows, columns, capacity);
        }
    }
}