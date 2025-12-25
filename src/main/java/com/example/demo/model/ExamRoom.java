package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "exam_rooms",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "roomNumber")
    }
)
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String roomNumber;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer rows;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer columns;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer capacity;

    /* =========================
       Constructors
       ========================= */

    public ExamRoom() {
    }

    public ExamRoom(String roomNumber, Integer rows, Integer columns) {
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        ensureCapacityMatches();
    }

    /* =========================
       Business Logic
       ========================= */

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    /* =========================
       Getters & Setters
       ========================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
        ensureCapacityMatches();
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
        ensureCapacityMatches();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
