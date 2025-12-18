package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rollNumber;

    private String name;
    private String department;
    private Integer year;

    // getters & setters
}
