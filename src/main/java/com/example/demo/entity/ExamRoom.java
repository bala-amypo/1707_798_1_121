package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer capacity;
    private Integer rows;
    private Integer columns;

    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        this.capacity = rows * columns;
    }

    // getters & setters
}
