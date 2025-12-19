package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "exam_rooms",
    uniqueConstraints = @UniqueConstraint(columnNames = "roomNumber")
)
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    private Integer rows;
    private Integer columns;
    private Integer capacity;

    // ---------- Constructors ----------
    public ExamRoom() {}

    public ExamRoom(String roomNumber, Integer rows, Integer columns) {
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        this.capacity = rows * columns;
    }

    // ---------- Auto capacity ----------
    @PrePersist
    @PreUpdate
    public void calculateCapacity() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getRows() { return rows; }
    public void setRows(Integer rows) { this.rows = rows; }

    public Integer getColumns() { return columns; }
    public void setColumns(Integer columns) { this.columns = columns; }

    public Integer getCapacity() { return capacity; }
}
